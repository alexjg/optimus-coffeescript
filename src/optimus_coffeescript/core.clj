(ns optimus-coffeescript.core
  (:import [javax.script ScriptEngine ScriptEngineManager])
  (:require [clojure.java.io :as io]
            [clojure.string :as str]
            [optimus.assets.creation :as creation]))

(let [factory (ScriptEngineManager.)
      engine (.getEngineByName factory "javascript")
      cs-compiler (slurp (io/resource "coffee-script-1.7.1.js"))]
  (.eval engine cs-compiler)
  (def js-engine engine))


(defn- compile-coffeescript
  [coffee-source]
  (let [callstring (format "CoffeeScript.compile(\"%s\");" coffee-source)]
    (.eval js-engine callstring)))

(defn- load-coffeescript-asset
  [public-dir path]
  (let [resource (creation/existing-resource public-dir path)
        coffee-source (slurp resource)
        escaped-source (str/replace coffee-source #"\n" " ")
        compiled-js (compile-coffeescript escaped-source)
        asset (creation/create-asset path compiled-js)]
    (merge asset {:last-modified (creation/last-modified resource)
                  :original-path path})))

(defmethod optimus.assets.creation/load-asset "coffee"
  [public-dir path]
  (load-coffeescript-asset public-dir path))
