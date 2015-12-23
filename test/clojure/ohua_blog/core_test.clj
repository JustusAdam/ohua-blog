(ns ohua-blog.core-test
  (:require [clojure.test :refer :all]
            [ohua-blog.core :refer :all]))

;

(ohua-blog.render/render-page
 (ohua-blog.render/render-side-pane
  (let*
   [pids__161__auto__
    (clojure.core/map
     (fn*
      [p1__160__162__auto__]
      ([p1__160__162__auto__
        (ohua-blog.data-source/fetch
         :post-views
         p1__160__162__auto__)]))
     (ohua-blog.data-source/fetch :post-ids))
    ordered__163__auto__
    (clojure.core/take
     5
     (clojure.core/sort-by
      clojure.core/last
      clojure.core/>
      pids__161__auto__))
    content__164__auto__
    (clojure.core/map
     (fn*
      ([p__196]
       (let*
        [vec__197
         p__196
         a__165__auto__
         (clojure.core/nth vec__197 0 nil)
         b__166__auto__
         (clojure.core/nth vec__197 1 nil)]
        [[(ohua-blog.data-source/fetch :post-info a__165__auto__)
          (ohua-blog.data-source/fetch :post-content a__165__auto__)]
         b__166__auto__])))
     ordered__163__auto__)]
   (ohua-blog.render/render-popular-posts content__164__auto__))
  (let*
   [posts__153__auto__
    (clojure.core/map
     (fn*
      [p1__146__147__auto__]
      (ohua-blog.data-source/fetch :post-info p1__146__147__auto__))
     (ohua-blog.data-source/fetch :post-ids))
    topic-counts__154__auto__
    (clojure.core/frequencies
     (clojure.core/map :topic posts__153__auto__))]
   (ohua-blog.render/render-topics topic-counts__154__auto__)))
 (let*
  [posts__178__auto__
   (clojure.core/map
    (fn*
     [p1__146__147__auto__]
     (ohua-blog.data-source/fetch :post-info p1__146__147__auto__))
    (ohua-blog.data-source/fetch :post-ids))
   ordered__179__auto__
   (clojure.core/take
    5
    (clojure.core/sort-by :date posts__178__auto__))
   content__180__auto__
   (clojure.core/map
    (fn*
     [p1__177__181__auto__]
     (ohua-blog.data-source/fetch
      :post-content
      (:id p1__177__181__auto__)))
    ordered__179__auto__)]
  (ohua-blog.render/render-main-panel
   (clojure.core/map
    clojure.core/vector
    ordered__179__auto__
    content__180__auto__))))
