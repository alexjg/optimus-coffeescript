(defproject optimus-coffeescript "0.1.0"
  :description "A coffeescript compiler for optimus"
  :url "http://example.com/FIXM://github.com/alexjg/optimus-coffeescript"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [optimus "0.14.3"]]
  :profiles {:dev {:dependencies [[lein-midje "3.1.1"]
                                  [midje "1.6.3"]
                                  [org.clojure/tools.nrepl "0.2.3"]]}})

