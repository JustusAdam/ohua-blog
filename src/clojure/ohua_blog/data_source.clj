(ns ohua-blog.data-source
  (:require [ohua-blog.mock-source :as ms]))

; (defn fetch [target & args]
;   (case target
;     :post-ids __
;     :posts-info __
;     :post-content __))

(def fetch ms/fetch)
