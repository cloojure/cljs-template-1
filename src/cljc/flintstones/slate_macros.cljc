(ns flintstones.slate-macros)

(defmacro logr-slate
  [& body]
  `(do
     (println "logr-enter")
     (let [result# (do ~@body)]
       (println "logr-leave" result#)
       result#)))

