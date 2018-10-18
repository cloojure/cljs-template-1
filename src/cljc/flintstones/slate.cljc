(ns flintstones.slate)

(defn add2 [x y] (+ x y))

(defmacro logr-slate
  [& body]
  `(do
     (println "logr-enter")
     (let [result# (do ~@body)]
       (println "logr-leave" result#)
       result#)))
