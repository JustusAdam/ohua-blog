(ns ohua-blog.render
  (:require [clojure.string :refer [join capitalize]])
  (require [com.ohua.util.gen :refer [aot-function]]))


(aot-function renderTopics ^String  [this ^Iterable topics]
  (str "Topics:\n" (join "\n" (map str topics))))

(aot-function renderPostList ^String [this ^Iterable posts]
  (join "\n" (map #(str "Post " (.getId (first %))) posts)))

(aot-function renderSidePane ^String [this ^Object posts ^Object topics]
  (str "Side panel\n" posts "\n" topics))

(aot-function renderMainPanel ^String [this ^Iterable posts]
  (str "Main panel\n" (renderPostList this posts)))

(aot-function renderPosts ^String [this ^Iterable posts]
  (renderPostList this posts))

(aot-function renderPage ^String [this ^Object pane ^Object content]
  (str pane "\n" content))

(aot-function renderPopularPosts ^String [this ^Iterable posts]
  (join "\n" (map (fn [[views info content]] (str " Views: " views "Content:" content)) posts)))
;
; (use 'clojure.pprint)
; (use 'clojure.walk)
;
; (pprint (macroexpand '(function testGen [this a b] a)))
