(ns optimus-coffeescript.core
  (:import [javax.script ScriptEngine ScriptEngineManager])
  (:require [clojure.java.io :as io]
            [clojure.string :as str]
            [optimus.assets.creation :as creation]))

(let [factory (ScriptEngineManager.)
      engine (.getEngineByName factory "javascript")
      cs-compiler (slurp (io/resource "coffee-script-1.7.1.js"))
      compile-func (slurp (io/resource "compiler.js"))]
  (.eval engine cs-compiler)
  (.eval engine compile-func)
  (def js-engine engine))


(defn- compile-coffeescript
  [coffee-source filename]
  (let [callstring (format "compile(%s, %s);" coffee-source, (pr-str filename))
        result (into {} (.eval js-engine callstring))]
    (if (result "success")
      (.toString (result "code"))
      (let [ex (Exception. (.toString (result "errorDescription")))]
        (throw ex)))))


(defn- load-coffeescript-asset
  [public-dir path]
  (let [resource (creation/existing-resource public-dir path)
        coffee-source (slurp resource)
        escaped-source (pr-str coffee-source)
        compiled-js (compile-coffeescript escaped-source path)
        asset (creation/create-asset path compiled-js)]
    (merge asset {:last-modified (creation/last-modified resource)
                  :original-path path})))

(defmethod optimus.assets.creation/load-asset "coffee"
  [public-dir path]
  (load-coffeescript-asset public-dir path))
