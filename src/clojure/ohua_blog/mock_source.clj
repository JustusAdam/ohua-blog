(ns ohua-blog.mock-source
  (:use [ohua-blog.types]))

(def posts
  [[(->PostInfo 1 "A" "") ""]
   [(->PostInfo 2 "B" "") ""]
   [(->PostInfo 3 "B" "") ""]])

(def post-ids
  (into [] (map (comp :id first) posts)))

(def post-contents
  (reduce (fn [m [info content]] (assoc m (:id info) content)) {} posts))

(def post-infos
  (reduce (fn [m [info content]] (assoc m (:id info) info)) {} posts))

(def post-views
  (reduce (fn [m [info c]] (assoc m (:id info) (rand-int 100))) {} posts))

(defn fetch
  [type id]
  (case type
    :post-content (get post-contents id)
    :post-info (get post-infos id)
    :post-views (get post-views id)))
