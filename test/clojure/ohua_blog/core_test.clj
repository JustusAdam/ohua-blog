(ns ohua-blog.core-test
  (:require [clojure.test :refer :all]
            [ohua-blog.core :refer :all]))
(com.ohua.lang.operators/capture
  (com.ohua.lang/smap
    (fn*
      ([id__1437__auto__]
       (ohua-blog.render/render-page
         (ohua-blog.render/render-side-pane
           (let*
             [pids__1405__auto__
              (clojure.core/seq
                (clojure.core/concat
                  (clojure.core/list 'com.ohua_blog.functions/fetch)
                  (clojure.core/list
                    'com.ohua_blog.types.FetchType/PostIds)
                  (clojure.core/list id)))
              views__1406__auto__
              (com.ohua.lang/smap
                (fn*
                  ([post__1407__auto__]
                   [post__1407__auto__
                    (clojure.core/seq
                      (clojure.core/concat
                        (clojure.core/list
                          'com.ohua_blog.functions/fetch)
                        (clojure.core/list
                          'com.ohua_blog.types.FetchType/PostViews)
                        (clojure.core/list id)))]))
                pids__1405__auto__)
              ordered__1408__auto__
              (clojure.core/take
                5
                (clojure.core/sort-by
                  clojure.core/last
                  clojure.core/>
                  views__1406__auto__))
              content__1409__auto__
              (clojure.core/map
                (fn*
                  ([p__1439]
                   (let*
                     [vec__1440
                      (com.ohua.lang.operators/destructure p__1439)
                      a__1410__auto__
                      (clojure.core/nth vec__1440 0 nil)
                      b__1411__auto__
                      (clojure.core/nth vec__1440 1 nil)]
                     [[(clojure.core/seq
                         (clojure.core/concat
                           (clojure.core/list
                             'com.ohua_blog.functions/fetch)
                           (clojure.core/list
                             'com.ohua_blog.types.FetchType/PostContent)
                           (clojure.core/list id)))
                       (clojure.core/seq
                         (clojure.core/concat
                           (clojure.core/list
                             'com.ohua_blog.functions/fetch)
                           (clojure.core/list
                             'com.ohua_blog.types.FetchType/PostContent)
                           (clojure.core/list id)))]
                      b__1411__auto__])))
                ordered__1408__auto__)]
             (ohua-blog.render/render-popular-posts
               content__1409__auto__))
           (let*
             [posts__1397__auto__
              (com.ohua.lang/smap
                (fn*
                  ([a__1390__auto__]
                   (clojure.core/seq
                     (clojure.core/concat
                       (clojure.core/list
                         'com.ohua_blog.functions/fetch)
                       (clojure.core/list
                         'com.ohua_blog.types.FetchType/PostContent)
                       (clojure.core/list id)))))
                x__1391__auto__)
              topic-counts__1398__auto__
              (clojure.core/frequencies
                (clojure.core/map
                  (fn*
                    ([a__1399__auto__] (. a__1399__auto__ getTopic)))
                  posts__1397__auto__))]
             (ohua-blog.render/render-topics
               topic-counts__1398__auto__)))
         (let*
           [posts__1422__auto__
            (com.ohua.lang/smap
              (fn*
                ([a__1390__auto__]
                 (clojure.core/seq
                   (clojure.core/concat
                     (clojure.core/list
                       'com.ohua_blog.functions/fetch)
                     (clojure.core/list
                       'com.ohua_blog.types.FetchType/PostContent)
                     (clojure.core/list id)))))
              x__1391__auto__)
            ordered__1423__auto__
            (clojure.core/take
              5
              (clojure.core/sort-by
                (fn*
                  ([post__1424__auto__]
                   (. post__1424__auto__ getDate)))
                posts__1422__auto__))
            content__1425__auto__
            (com.ohua.lang/smap
              (fn*
                ([a__1426__auto__]
                 (clojure.core/seq
                   (clojure.core/concat
                     (clojure.core/list
                       'com.ohua_blog.functions/fetch)
                     (clojure.core/list
                       'com.ohua_blog.types.FetchType/PostContent)
                     (clojure.core/list id)))))
              ordered__1423__auto__)]
           (ohua-blog.render/render-main-panel
             (clojure.core/map
               clojure.core/vector
               ordered__1423__auto__
               content__1425__auto__))))))
    [2])
  result__1265__auto__)
