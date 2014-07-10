(ns optimus-coffeescript.core_test
  (:require [optimus-coffeescript.core]
            [optimus.assets.creation :as creation]
            [clojure.java.io :as io])
  (:use [midje.sweet]))


(fact "the load assets coffee method"
        (fact "should compile coffeescript to javascript"
            (let [result-asset (creation/load-asset "test" "/test.coffee")]
              (:contents result-asset) => "(function() {\n  (function(a) {\n    return a + 2;\n  });\n\n}).call(this);\n"))
        (fact "should set the last-modified header"
              (:last-modified (creation/load-asset "test" "/test.coffee")) => 3000
              (provided
                (creation/last-modified anything) => 3000))
        (fact "should set the original path attribute"
              (let [result-asset (creation/load-asset "test" "/test.coffee")]
                (:original-path result-asset) => "/test.coffee")))

