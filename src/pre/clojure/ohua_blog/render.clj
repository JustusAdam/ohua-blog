(ns ohua-blog.render
  (:require [clojure.string :refer [join capitalize]])
  (:import [com.ohua.lang Function]))

(defmacro function [name args & body]
  `(do
     (gen-class
       :name ~(str "ohua_blog.render." (capitalize name))
       :prefix "__mk_generated_"
       :methods
       [[~(with-meta name `{Function {}})
         [~@(map #(get (meta %) :tag Object) (rest args))]
         "[Ljava.lang.Object;"]])
     (defn ~name ~args ~@body)
     (defn ~(symbol (str "__mk_generated_" name)) ~args
       (into-array Object ~@body))))


(function renderTopics ^String  [this ^Iterable topics]
  (str "Topics:\n" (join "\n" (map str topics))))

(function renderPostList ^String [this ^Iterable posts]
  (join "\n" (map #(str "Post " (.getId (first %))) posts)))

(function renderSidePane ^String [this ^Iterable posts ^Iterable topics]
  (str "Side panel\n" posts "\n" topics))

(function renderMainPanel ^String [this ^Iterable posts]
  (str "Main panel\n" (renderPostList posts)))

(function renderPosts ^String [this ^Iterable posts]
  (renderPostList posts))

(function renderPage ^String [this ^Object pane ^Object content]
  (str pane "\n" content))

(function renderPopularPosts ^String [this ^Iterable posts]
  (join "\n" (map (fn [[[info content] views]] (str (.getId info) " Views: " views)) posts)))

;(use 'clojure.pprint)
;(pprint (macroexpand '(function statefulFunction ^String [^String a ^Iterable b ^Object c]
;  nil)))