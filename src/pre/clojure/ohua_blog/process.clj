(ns ohua-blog.process
  (:require [clojure.string :refer [capitalize]])
  (:import (com.ohua.lang Function)))


(defmacro function [name args & body]
  `(do
     (gen-class
       :name ~(str "ohua_blog.process." (capitalize name))
       :prefix "__mk_generated_"
       :methods
       [[~(with-meta name `{Function {}})
         [~@(map #(get (meta %) :tag Object) (rest args))]
         "[Ljava.lang.Object;"]])
     (defn ~name ~args ~@body)
     (defn ~(symbol (str "__mk_generated_" name)) ~args
       (into-array Object ~@body))))


(function processTopics ^Iterable [this ^Iterable posts]
  (frequencies (map (fn [a] (.getTopic a)) posts)))

(function orderPosts [this ^Iterable views]
  (take 5 (sort-by last > views)))

(function zip ^Iterable [this ^Iterable a ^Iterable b]
  (into [] (map vector a b)))

(function unzipFirst [this l]
  (into [] (map (fn [[a b]] a) l)))

(function takeLatest [this ^Iterable posts]
  (take 5 (sort-by (fn [post] (.getDate post)) posts)))

(function getId [this ^Object o]
  (.getId o))