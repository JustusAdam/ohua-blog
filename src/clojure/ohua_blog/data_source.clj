(ns ohua-blog.data-source)

(defrecord PostInfo [id topic author])

(defn fetch [target & args]
  (case target
    :post-ids __
    :posts-info __
    :post-content __))
