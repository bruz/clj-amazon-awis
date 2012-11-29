(ns clj-amazon-awis.auth
  (:require [clojure.string :as str])
  (:import (org.apache.commons.codec.binary Base64)
           (java.net URLEncoder)))

(defn hmac
  "Performs HMAC-SHA1"
  [^String msg ^String key]
  (let [algorithm "HmacSHA1"
        key (javax.crypto.spec.SecretKeySpec. (.getBytes key "UTF8") algorithm)
        mac (doto (javax.crypto.Mac/getInstance algorithm)
      (.init key))]

    (new String
      (Base64/encodeBase64
        (.doFinal mac (.getBytes msg "UTF8"))))))

(defn form-encode
  [params]
  (str/join
    "&"
    (map (fn[[k v]]
           (str (name k) "=" (URLEncoder/encode v))) params)))

(defn sign
  "Signs the request"
  [host params secret-key]
  (hmac
    (str "GET\n" host "\n/\n" (form-encode (into (sorted-map) params)))
    secret-key))
