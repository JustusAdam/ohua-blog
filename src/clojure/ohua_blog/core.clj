(ns ohua-blog.core
  (:require [ohua-blog.data-source :refer [fetch]])
  (:require [ohua-blog.render :refer :all]))

(defmacro get-post-ids []
  `(fetch :post-ids))

(defmacro get-post-info [id]
  `(fetch :post-info ~id))

(defmacro get-post-content [id]
  `(fetch :post-content ~id))

(defmacro get-post-views [id]
  `(fetch :post-views ~id))

(defmacro get-post-details [id]
  `[(get-post-info ~id) (get-post-content ~id)])

(defmacro get-all-posts-info []
  `(map #(get-post-info %) (get-post-ids)))

(defmacro topics []
  `(let [posts# (get-all-posts-info)
         topic-counts# (frequencies (map :topic posts#))]
    (render-topics topic-counts#)))

(defmacro popular-posts []
  `(let [pids# (map #([% (get-post-views %)]) (get-post-ids))
         ordered# (->> pids#
                    (sort-by last >)
                    (take 5))
         content# (map (fn [[a# b#]] [(get-post-details a#) b#]) ordered#)]
    (render-popular-posts content#)))

(defmacro left-pane []
  `(render-side-pane (popular-posts) (topics)))

(defmacro main-pane []
  `(let [posts# (get-all-posts-info)
         ordered# (->> posts#
                    (sort-by :date)
                    (take 5))
         content# (map
                    #(->> %
                      :id
                      get-post-content)
                    ordered#)]
    (render-main-panel (map vector ordered# content#))))

(defmacro blog []
  "Main entry point"
  `(render-page (left-pane) (main-pane)))

(use 'clojure.pprint)
(use 'clojure.walk)

(def -main (pprint (macroexpand-all '(blog))))
