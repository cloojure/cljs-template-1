(ns tst.flintstones.slate
  (:require
    [flintstones.test :refer [dotest is isnt is= isnt= testing use-fixtures] ]
    [flintstones.slate :as fs] ) )

(use-fixtures :once
  {:before (fn [] (newline) (println "test - enter") )
   :after  (fn [] (println "test - leave"))})

(dotest
  (println :ns *ns*)
  (println :curr-ns curr-ns)
  (println :ns-name (str (ns-name (the-ns *ns*))))

  (is= 2 (+ 1 1)) ; this works
  (is= 5 (fs/add2 2 3)) ; this works
  (is true)
  (isnt false)
  (isnt= 42 (* 6 6)))
