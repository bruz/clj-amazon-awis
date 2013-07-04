(defproject clj-amazon-awis "0.1.3"
  :description "A thin wrapper for the Amazon AWIS API"
  :url "https://github.com/bruz/clj-amazon-awis"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.3.0"]
                 [clj-http "0.5.8"]
                 [clj-time "0.4.4"]
                 [camel-snake-kebab "0.1.0"]]
  :profiles {:dev {:dependencies [[midje "1.5.1"]
                                 [lein-marginalia "0.7.1"]
                                 [lein-midje "3.0.1"]]}})