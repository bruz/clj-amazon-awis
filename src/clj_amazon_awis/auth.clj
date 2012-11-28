(ns clj-amazon-awis.auth
  (:require ring.util.codec)
  (:import (org.apache.commons.codec.binary Base64)))

(defn hmac [msg key]
  (let [algorithm "HmacSHA1"
        key (javax.crypto.spec.SecretKeySpec. (.getBytes key "UTF8") algorithm)
        mac (doto (javax.crypto.Mac/getInstance algorithm)
      (.init key))]

    (new String
      (Base64/encodeBase64
        (.doFinal mac (.getBytes msg "UTF8"))))))

(defn sign [host params secret-key]
  (hmac
    (str "GET\n" host "\n/\n" (ring.util.codec/form-encode (into (sorted-map) params)))
    secret-key))
