(ns ohua-blog.render
  (:require [clojure.string :refer [join]]))

(defn render-topics [topics]
  (str "Topics:\n" (join "\n" (map str topics))))

(defn render-post-list [posts]
  (join "\n" (map #(str "Post " (:id (first %))) posts)))

(defn render-side-pane [posts topics]
  (str "Side panel\n" posts "\n" topics))

(defn render-main-panel [posts]
  (str "Main panel\n" (render-post-list posts)))

(def render-posts render-post-list)

(defn render-page [pane content]
  (str pane "\n" content))

(defn render-popular-posts [posts]
  (join "\n" (map (fn [[[info content] views]] (str (:id info) " Views: " views)))))
