(ns ohua-blog.process
  (:import (com.ohua.lang Function))
  (:require [com.ohua.util.gen :refer [function aot-function]]))

(aot-function processTopics ^Iterable [this ^Iterable posts]
  (frequencies (map (fn [a] (.getTopic a)) posts)))

(aot-function orderPosts [this ^Iterable views]
  (take 5 (sort-by last > views)))

(aot-function zip ^Iterable [this ^Iterable a ^Iterable b]
  (into [] (map vector a b)))

(aot-function unzipFirst [this l]
  (into [] (map (fn [[a b]] a) l)))

(aot-function takeLatest [this ^Iterable posts]
  (take 5 (sort-by (fn [post] (.getDate post)) posts)))

(function getId [this ^Object o]
  (.getId o))
