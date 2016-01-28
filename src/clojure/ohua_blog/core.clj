(ns ohua-blog.core
  (:require [com.ohua.logging :as l]
            [ohua-blog.render]
            [com.ohua.transform.clojure-code :as cc])
  (:use [com.ohua.compile]))

; (cc/disable-capture)

(ohua :import [ohua_blog.render
               ohua_blog.process
               com.ohua_blog.functions])

(use 'clojure.pprint)
(use 'clojure.walk)

(set! (. com.ohua.engine.utils.GraphVisualizer PRINT_FLOW_GRAPH) (str "nested-smap-exec-flow1"))

(defmacro m []
  '(let [x [(int 2)]]
     (com.ohua.compile/ohua
        (com.ohua.lang/smap
          (fn [id]
             (render-page
               (render-side-pane
                 (let [pids (fetch id "PostIds")
                       views (com.ohua.lang/smap
                               (fn [post] (fetch post "PostViews"))
                               pids)
                       views-and-posts (zip pids views)
                       ordered (order-posts views-and-posts)
                       ordered-ids (unzip-first ordered)
                       content-only (com.ohua.lang/smap
                                      (fn [a] (fetch a "PostContent"))
                                      ordered-ids)
                       content (zip content-only ordered-ids)]
                   (render-popular-posts content))
                 (let [pids (fetch id "PostIds")
                       posts (com.ohua.lang/smap
                               (fn [a] (fetch a "PostContent"))
                               pids)
                       topic-counts (process-topics posts)]
                   (render-topics topic-counts)))
               (let [pids (fetch id "PostIds")
                     posts (com.ohua.lang/smap
                             (fn [a] (fetch a "PostContent"))
                             pids)
                     ordered (take-latest posts)
                     content (com.ohua.lang/smap
                               (fn [a] (fetch a "PostContent"))
                               ordered)]
                 (render-main-panel
                   (zip ordered content)))))
          x))))

(defn -main []
  (do
    (l/enable-compilation-logging)
    (m)))
