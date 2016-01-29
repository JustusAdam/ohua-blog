(ns ohua-blog.process
  (:import (com.ohua.lang Function))
  (:require [com.ohua.util.gen :refer [function aot-function]]))

(aot-function processTopics ^Iterable [this ^Iterable posts]
  (frequencies (map (fn [a] (.getTopic a)) posts)))

(aot-function orderPosts [this ^Iterable views]
  (into [] (take 5 (sort-by last > views))))

(aot-function zip ^Iterable [this ^Iterable a ^Iterable b]
  (into [] (map vector a b)))

(aot-function unzipFirst [this l]
  (into [] (map (fn [[a b]] a) l)))

(aot-function takeLatest [this ^Iterable posts]
  (let [res (take 5 (sort-by (fn [post] (.getDate post)) posts))]
    res))

(aot-function getId [this ^Object o]
  (.getId o))

(aot-function zip3 [this ^Iterable a ^Iterable b ^Iterable c]
  (into [] (map vector a b c)))

(aot-function getIds [this ^Iterable a]
  (map #(.getId %) a))

(aot-function p [this a]
  (println a)
  a)
