(ns ohua-blog.core
  (:require [com.ohua.logging :as l]
            [ohua-blog.render]
            [com.ohua.transform.clojure-code :as cc])
  (:use [com.ohua.compile]))

(ohua :import [ohua_blog.render
               ohua_blog.process
               com.ohua_blog.functions])

(use 'clojure.pprint)
(use 'clojure.walk)

;(set! (. com.ohua.engine.utils.GraphVisualizer PRINT_FLOW_GRAPH) (str "nested-smap-exec-flow1"))

; TODO: should just be a normal functions. see issue #99 in Ohua
(defmacro popular-posts [id]
  `(let [pids# (com.ohua_blog.functions/fetch ~id "PostIds")
        views# (com.ohua.lang/smap (fn [post#] (com.ohua_blog.functions/fetch post# "PostViews")) pids#)
        ; TODO collapse into a single stateful function
        views-and-posts# (ohua_blog.process/zip pids# views#)
        ordered# (ohua_blog.process/order-posts views-and-posts#)
        ordered-ids# (ohua_blog.process/unzip-first ordered#)

        content# (com.ohua.lang/smap (fn [a#] (com.ohua_blog.functions/fetch a# "PostContent")) ordered-ids#)
        info# (com.ohua.lang/smap (fn [a#] (com.ohua_blog.functions/fetch a# "PostInfo")) ordered-ids#)
        package# (ohua_blog.process/zip3 views# info# content#)]
    (ohua_blog.render/render-popular-posts package#)))

(defmacro topics [id]
  `(let [pids# (com.ohua_blog.functions/fetch ~id "PostIds")
         posts# (com.ohua.lang/smap (fn [a#] (com.ohua_blog.functions/fetch a# "PostInfo")) pids#)
         topic-counts# (ohua_blog.process/process-topics posts#)]
     (ohua_blog.render/render-topics topic-counts#)))

(defmacro main-pane [id]
  `(let [pids# (com.ohua_blog.functions/fetch ~id "PostIds")
        posts# (com.ohua.lang/smap (fn [a#] (com.ohua_blog.functions/fetch a# "PostInfo")) pids#)
        ordered# (ohua_blog.process/take-latest posts#)
        ordered-ids# (ohua_blog.process/get-ids ordered#)
        content# (com.ohua.lang/smap (fn [a#] (com.ohua_blog.functions/fetch a# "PostContent")) ordered-ids#)]
    (ohua_blog.render/render-main-panel (ohua_blog.process/zip ordered# content#))))

(defmacro m []
  '(let [input [(int 2)]]
         (ohua
            (smap
              (fn [id]
                (render-page
                  (render-side-pane (popular-posts id) (topics id))
                  (main-pane id)))
              input))
     ))

(defn -main []
  (do
    ;(l/enable-compilation-logging)
    (let [result (m)]
      (println "Result:" result)
      )))
