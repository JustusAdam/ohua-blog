(ns ohua-blog.render
  (:gen-class
    :name ohua_blog.render.Render
    :methods
      [
       ;[^{com.ohua.lang.Function {}} renderTopics [Iterable] String]
       ;[^{com.ohua.lang.Function {}} renderPostList [Iterable] String]
       ;[^{com.ohua.lang.Function {}} renderSidePane [Iterable Iterable] String]
       ;[^{com.ohua.lang.Function {}} renderMainPane [Iterable] String]
       ;[^{com.ohua.lang.Function {}} renderPosts [Iterable] String]
       [^{com.ohua.lang.Function {}} renderPage [String String] String]
       ;[^{com.ohua.lang.Function {}} renderPopularPosts [Iterable] String]
       ]
      )
  (:require [clojure.string :refer [join]])
  (:import
    (com.ohua.lang Function)))


(defn -renderTopics [this topics]
  (str "Topics:\n" (join "\n" (map str topics))))

(defn -renderPostList [this posts]
  (join "\n" (map #(str "Post " (.getId (first %))) posts)))

(defn -renderSidePane [this posts topics]
  (str "Side panel\n" posts "\n" topics))

(defn -renderMainPanel [this posts]
  (str "Main panel\n" (.renderPostList this posts)))

(defn -renderPosts [this posts]
  (.renderPostList this posts))

(defn -renderPage [this pane content]
  (str pane "\n" content))

(defn -renderPopularPosts [this posts]
  (join "\n" (map (fn [[[info content] views]] (str (.getId info) " Views: " views)) posts)))
