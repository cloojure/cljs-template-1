(ns tst.flintstones.slate
  (:require [flintstones.test :refer [dotest is isnt is= isnt= testing use-fixtures]])
)

(use-fixtures :once
  {:before (fn [] (println "Testing slate - enter"))
   :after  (fn [] (println "Testing slate - leave"))})

(dotest
  (is= 2 (+ 1 1)) ; this works
  (is true)
  (isnt false)
  (isnt= 42 (* 6 6)))
