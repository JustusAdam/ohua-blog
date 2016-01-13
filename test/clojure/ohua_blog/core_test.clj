(ns ohua-blog.core-test
  (:require [clojure.test :refer :all]
            [ohua-blog.core :refer :all]))

;
(print
  (ohua-blog.render/render-page
    (ohua-blog.render/render-side-pane
      (let*
        [pids__160__auto__
         (clojure.core/map
           (fn*
             ([post__161__auto__]
              [post__161__auto__
               (ohua-blog.data-source/fetch :post-views post__161__auto__)]))
           (ohua-blog.data-source/fetch :post-ids))
         ordered__162__auto__
         (clojure.core/take
           5
           (clojure.core/sort-by
             clojure.core/last
             clojure.core/>
             pids__160__auto__))
         content__163__auto__
         (clojure.core/map
           (fn*
             ([p__195]
              (let*
                [vec__196
                 p__195
                 a__164__auto__
                 (clojure.core/nth vec__196 0 nil)
                 b__165__auto__
                 (clojure.core/nth vec__196 1 nil)]
                [[(ohua-blog.data-source/fetch :post-info a__164__auto__)
                  (ohua-blog.data-source/fetch :post-content a__164__auto__)]
                 b__165__auto__])))
           ordered__162__auto__)]
        (ohua-blog.render/render-popular-posts content__163__auto__))
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
      [posts__177__auto__
       (clojure.core/map
         (fn*
           [p1__146__147__auto__]
           (ohua-blog.data-source/fetch :post-info p1__146__147__auto__))
         (ohua-blog.data-source/fetch :post-ids))
       ordered__178__auto__
       (clojure.core/take
         5
         (clojure.core/sort-by :date posts__177__auto__))
       content__179__auto__
       (clojure.core/map
         (fn*
           [p1__176__180__auto__]
           (ohua-blog.data-source/fetch
             :post-content
             (:id p1__176__180__auto__)))
         ordered__178__auto__)]
      (ohua-blog.render/render-main-panel
        (clojure.core/map
          clojure.core/vector
          ordered__178__auto__
          content__179__auto__)))))