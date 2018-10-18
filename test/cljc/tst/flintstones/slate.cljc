(ns tst.flintstones.slate
  (:require
    [flintstones.test-cljs :refer [dotest is isnt is= isnt= testing use-fixtures ]]
    [flintstones.slate :as slate :include-macros true]
  ) )

(use-fixtures :once
  {:before (fn [] (newline) (println "test once - enter"))
   :after  (fn [] (println "test once - leave"))})

(dotest
  (is= 2 (+ 1 1))   ; this works
  (is= 5 (slate/add2 2 3)) ; this works

  (is= 3 (slate/logr-slate
           (inc 0)
           (inc 1)
           (inc 2)))

  (is true)
  (isnt false)
  (isnt= 42 (* 6 6)))
