(ns flintstones.wilma
  (:require [wilmaPhony] ))

(defn stats []
  (println "wilmaPhony/stats:   " wilmaPhony/stats)
  wilmaPhony/stats)

(defn stats2 []
  wilmaPhony/stats2)

(defn make-wilma []
  (let [wilma (wilmaPhony/makeWilma)]
    (println "wilma =>" wilma)
    {:desc (.-desc wilma)
     :says (.says wilma "Fred")}))
