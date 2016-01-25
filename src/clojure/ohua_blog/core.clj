(ns ohua-blog.core
  (:require [com.ohua.logging :as l]
            [ohua-blog.render]
            [com.ohua.transform.clojure-code :as cc])
  (:use [com.ohua.compile]))

(import com.ohua_blog.types.FetchType)
(import com.ohua_blog.types.PostInfo)

; (cc/disable-capture)

(ohua :import [ohua_blog.render
               com.ohua_blog.functions])

(defmacro get-post-ids [id]
  '`(com.ohua_blog.functions/fetch FetchType/PostIds ~id))

(defmacro get-post-info [id]
  '`(com.ohua_blog.functions/fetch FetchType/PostContent ~id))

(defmacro get-post-content [id]
  '`(com.ohua_blog.functions/fetch FetchType/PostContent ~id))

(defmacro get-post-views [id]
  `(com.ohua_blog.functions/fetch FetchType/PostViews ~id))

(defmacro get-post-details [id]
  `[(get-post-info ~id) (get-post-content ~id)])

(defmacro get-all-posts-info [id]
  (let [x# (get-post-ids ~id)] `(com.ohua.lang/smap (fn [a#] (get-post-info a#)) x#)))

(defmacro topics [id]
  `(let [posts# (get-all-posts-info ~id)
         topic-counts# (frequencies (map (fn [a#] (.getTopic a#)) posts#))]
    (ohua_blog.render/render-topics topic-counts#)))

(defmacro popular-posts [id]
  `(let [pids# (get-post-ids ~id)
         views# (com.ohua.lang/smap (fn [post#] [post# (get-post-views post#)]) pids#)
         ordered# (take 5 (sort-by last > views#))
         content# (com.ohua.lang/smap (fn [[a# b#]] [(get-post-details a#) b#]) ordered#)]
    (ohua_blog.render/render-popular-posts content#)))

(defmacro left-pane [id]
  `(ohua_blog.render/render-side-pane (popular-posts ~id) (topics ~id)))

(defmacro main-pane [id]
  `(let [posts# (get-all-posts-info ~id)
         ordered# (take 5 (sort-by (fn [post#] (.getDate post#)) posts#))
         content# (com.ohua.lang/smap
                    (fn [a#] (->> a#
                                (fn [b#] (.getId b#))
                                get-post-content))
                    ordered#)]
    (ohua_blog.render/render-main-panel (map vector ordered# content#))))

(use 'clojure.pprint)
(use 'clojure.walk)

(defn -main []
  (do
    (l/enable-compilation-logging)
    (pprint
      (macroexpand-all
        `(com.ohua.compile/ohua
           (com.ohua.lang/smap (fn [id#]
            (ohua_blog.render/render-page (left-pane id#) (main-pane id#))) [2]))))))
  ;(print (blog 3) )
  ;(ohua
  ;  (smap blog [1 2 3]))
