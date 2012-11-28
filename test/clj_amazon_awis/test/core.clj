(ns clj-amazon-awis.test.core
  (:require [clj-http.client :as http]
            [clj-time.core :as time])
  (:use [clj-amazon-awis.core]
        midje.sweet))

(fact
  (url-info
    {:url "http://clojure.org/"
     :response-group "UsageStats"}
    {:aws-access-key "myaccesskey"
     :aws-secret-key "shhhdonttell"}) => {:tag :friends
                                          :attrs nil
                                          :content [{:tag :name
                                                     :attrs nil
                                                     :content ["Bob"]}]}

  (provided (time/now) => (time/date-time 2012 10 14 4 3 27 456))
  (provided
    (http/get "http://awis.amazonaws.com/"
      {:query-params {:Signature "9QejMePfJdUG2zhlEslezD+yRaA=",
                      :SignatureVersion "2",
                      :SignatureMethod "HmacSHA1",
                      :AWSAccessKeyId "myaccesskey",
                      :Action "UrlInfo",
                      :ResponseGroup "UsageStats",
                      :Url "http://clojure.org/"
                      :Timestamp "2012-10-14T04:03:27.456Z"}
       :throw-entire-message? true}) => {:body "<friends>
                                                  <name>Bob</name>
                                                </friends>
                                                "}))