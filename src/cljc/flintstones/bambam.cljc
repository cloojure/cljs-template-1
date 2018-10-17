(ns flintstones.bambam)

(defmacro logr-bambam
  [& body]
  `(do
     (println "logr-enter-bambam")
     (let [result# (do ~@body)]
       (println "logr-leave-bambam" result#)
       result#)))

(defn add2 [x y] (+ x y))

