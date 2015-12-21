(ns ohua-blog.core
  (:require [ohua-blog.data-source :as ds]))

(defn get-post-ids []
  (fetch :post-id))

(defn get-post-details [id]
  (fetch :posts-info id))

(defn get-post-content [id]
  (fetch :post-content id))

(defn get-all-posts-info []
  (map get-post-info (get-post-ids)))

(defn blog []
  (render-page (left-pane) (right-pane)))

(defn left-pane []
  (render-side-pane (popular-posts) (topics)))

(defn topics []
  (let [posts (get-all-posts-info)
        topic-counts (frequencies (map :topic posts))]))

(defn popular-posts []
  (let [pids (get-post-ids)
        views (map get-post-views posts)
        ordered (->> (map vector pids views)
                  (sort-by last >)
                  (map head)
                  (take 5))
        content (map get-post-details pids)]
    (render-post-list content)))

(defn main-pane []
  (let [posts (get-all-posts-info)
        ordered (->> posts
                  (sort-by :date)
                  (take 5))
        content (->> ordered
                  (map :id)
                  (map get-post-content))]
    (render-posts (map vector ordered content))))
