(ns ohua-blog.process
  (:require [com.ohua.util.gen :refer [function aot-function]]))

(aot-function processTopics ^Iterable [^Iterable posts]
  (frequencies (map (fn [a] (.getTopic a)) posts)))

(aot-function orderPosts [^Iterable views]
  (into [] (take 5 (sort-by last > views))))

(aot-function zip ^Iterable [^Iterable a ^Iterable b]
  (map vector a b))

(aot-function unzipFirst [l]
  (map (fn [[a b]] a) l))

(aot-function takeLatest [^Iterable posts]
  (let [res (take 5 (sort-by (fn [post] (.getDate post)) posts))]
    res))

(aot-function getId [^Object o]
  (.getId o))

(aot-function zip3 [^Iterable a ^Iterable b ^Iterable c]
  (into [] (map vector a b c)))

(aot-function getIds [^Iterable a]
  (map #(.getId %) a))

(aot-function p [a]
  (println a)
  a)
