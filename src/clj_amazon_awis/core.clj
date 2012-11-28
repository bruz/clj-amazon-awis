(ns clj-amazon-awis.core
  (:require [clj-amazon-awis.auth :as auth]
            [clj-http.client :as http]
            [clj-time.core :as time]
            [clojure.xml :as xml])
  (:use camel-snake-kebab)
  (:import (java.io ByteArrayInputStream)))

(def host "awis.amazonaws.com")

(def base-url (str "http://" host "/"))

(defn camelize [params]
  (zipmap
    (map #(->CamelCase %) (keys params))
    (vals params)))

(defn get-struct-map [xml]
  (let [stream (ByteArrayInputStream. (.getBytes (.trim xml)))]
    (xml/parse stream)))

(defn make-request [action params auth]
  (let [camelized-params (camelize params)
        aws-access-key (:aws-access-key auth)
        aws-secret-key (:aws-secret-key auth)
        query-params (assoc camelized-params
      :Action action
      :AWSAccessKeyId aws-access-key
      :SignatureMethod "HmacSHA1"
      :SignatureVersion "2"
      :Timestamp (str (time/now)))]
    (get-struct-map
      (:body (http/get base-url
        {:query-params (assoc query-params :Signature (auth/sign host query-params aws-secret-key))
         :throw-entire-message? true})))))

(defn url-info [params auth]
  (make-request "UrlInfo" params auth))

(defn traffic-history [params auth]
  (make-request "TrafficHistory" params auth))

(defn category-browse [params auth]
  (make-request "CategoryBrowse" params auth))

(defn category-listings [params auth]
  (make-request "CategoryListings" params auth))

(defn sites-linking-in [params auth]
  (make-request "SitesLinkingIn" params auth))