(ns flintstones.slate-macros)

(defmacro logr
  [& body]
  `(do
     (println "logr-enter")
     (let [result# (do ~@body)]
       (println "logr-leave" result#)
       result#)))

