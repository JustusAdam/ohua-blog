(ns ohua-blog.render
  (:require [clojure.string :refer [join capitalize]])
  (require [com.ohua.util.gen :refer [aot-function]]))


(aot-function renderTopics ^String  [^Iterable topics]
  (str "Topics:\n" (join "\n" (map str topics))))

(aot-function renderPostList ^String [^Iterable posts]
  (join "\n" (map #(str "Post " (.getId (first %))) posts)))

(aot-function renderSidePane ^String [^Object posts ^Object topics]
  (str "Side panel\n" posts "\n" topics))

(aot-function renderMainPanel ^String [^Iterable posts]
  (str "Main panel\n" (renderPostList posts)))

(aot-function renderPosts ^String [^Iterable posts]
  (renderPostList posts))

(aot-function renderPage ^String [^Object pane ^Object content]
  (str pane "\n" content))

(aot-function renderPopularPosts ^String [^Iterable posts]
  (join "\n" (map (fn [[views info content]] (str " Views: " views "Content:" content)) posts)))
;
; (use 'clojure.pprint)
; (use 'clojure.walk)
;
; (pprint (macroexpand '(aot-function renderPage ^String [^Object pane ^Object content]
;                                     (str pane "\n" content))))
