(ns ohua-blog.core
  (:require [ohua-blog.data-source :as ds])
  (:require [ohua-blog.render :refer :all]))

(defmacro get-post-ids []
  (fetch :post-id))

(defmacro get-post-info [id]
  (fetch :posts-info id))

(defmacro get-post-content [id]
  (fetch :post-content id))

(defmacro get-post-details [id]
  [(get-post-info id) (get-post-content id)])

(defmacro get-all-posts-info []
  (map get-post-info (get-post-ids)))

(defmacro topics []
  (let [posts (get-all-posts-info)
        topic-counts (frequencies (map (comp :topic head) posts))]
    (render-topics topic-counts)))

(defmacro popular-posts []
  (let [pids (get-post-ids)
        views (map get-post-views posts)
        ordered (->> (map vector pids views)
                  (sort-by last >)
                  (map head)
                  (take 5))
        content (map get-post-details pids)]
    (render-post-list content)))

(defmacro left-pane []
  (render-side-pane (popular-posts) (topics)))

(defmacro main-pane []
  (let [posts (get-all-posts-info)
        ordered (->> posts
                  (sort-by :date)
                  (take 5))
        content (map ordered
                  #(->> %
                    :id
                    get-post-content))]
    (render-posts (map vector ordered content))))

(defn blog []
  "Main entry point"
  (render-page (left-pane) (right-pane)))
