(defproject clj-amazon-awis "0.1.2"
  :description "A thin wrapper for the Amazon AWIS API"
  :url "https://github.com/bruz/clj-amazon-awis"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.3.0"]
                 [clj-http "0.5.8"]
                 [camel-snake-kebab "0.1.0"]
                 [ring "1.1.6"]]
  :dev-dependencies [[midje "1.4.0"]
                     [com.stuartsierra/lazytest "1.2.3"]
                     [lein-marginalia "0.7.1"]
                     [lein-midje "1.0.10"]]
  :repositories {"stuart" "http://stuartsierra.com/maven2"})