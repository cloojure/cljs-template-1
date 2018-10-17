(ns flintstones.slate 
)

(defmacro logr [& forms]
  `(do
    (println "logr-enter")
    ~@forms
    (println "logr-leave")) )

(defn add2 [x y] (+ x y))

