(ns clj-amazon-awis.core
  "Wraps the [Amazon AWIS API](http://docs.amazonwebservices.com/AlexaWebInfoService/latest/)"
  (:require [clj-amazon-awis.auth :as auth]
            [clj-http.client :as http]
            [clj-time.core :as time]
            [clojure.xml :as xml])
  (:use camel-snake-kebab)
  (:import (java.io ByteArrayInputStream)))

(def host "awis.amazonaws.com")

(def base-url (str "http://" host "/"))

(defn camelize
  "Camelize the kebab-case parameter keys"
  [params]
  (zipmap
    (map #(->CamelCase %) (keys params))
    (vals params)))

(defn get-struct-map
  "Parse xml to a struct-map"
  [^String xml]
  (let [stream (ByteArrayInputStream. (.getBytes (.trim xml)))]
    (xml/parse stream)))

(defn make-request
  "Combines the user-provided parameters with standard parameters for the
  request, including request signing"
  [action params auth]
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

(defn url-info
  "Wraps the [UrlInfo](http://docs.amazonwebservices.com/AlexaWebInfoService/latest/ApiReference_UrlInfoAction.html)
  action"
  [params auth]
  (make-request "UrlInfo" params auth))

(defn traffic-history
  "Wraps the [TrafficHistory](http://docs.amazonwebservices.com/AlexaWebInfoService/latest/ApiReference_TrafficHistoryAction.html)
  action"
  [params auth]
  (make-request "TrafficHistory" params auth))

(defn category-browse
  "Wraps the [CategoryBrowse](http://docs.amazonwebservices.com/AlexaWebInfoService/latest/ApiReference_CategoryBrowseAction.html)
  action"
  [params auth]
  (make-request "CategoryBrowse" params auth))

(defn category-listings
  "Wraps the [CategoryListings](http://docs.amazonwebservices.com/AlexaWebInfoService/latest/ApiReference_CategoryListingsAction.html)
  action"
  [params auth]
  (make-request "CategoryListings" params auth))

(defn sites-linking-in
  "Wraps the [SitesLinkingIn](http://docs.amazonwebservices.com/AlexaWebInfoService/latest/ApiReference_SitesLinkingInAction.html)
  action"
  [params auth]
  (make-request "SitesLinkingIn" params auth))