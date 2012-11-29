(ns clj-amazon-awis.auth
  (:require ring.util.codec)
  (:import (org.apache.commons.codec.binary Base64)))

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

(defn sign
  "Signs the request"
  [host params secret-key]
  (hmac
    (str "GET\n" host "\n/\n" (ring.util.codec/form-encode (into (sorted-map) params)))
    secret-key))
