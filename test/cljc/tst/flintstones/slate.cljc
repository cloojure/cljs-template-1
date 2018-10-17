(ns tst.flintstones.slate
  (:require
    [flintstones.test :refer [dotest is isnt is= isnt= testing use-fixtures] ]
    [flintstones.slate :as fs] ) )

(use-fixtures :once
  {:before (fn [] (println "Testing slate cljc - enter"))
   :after  (fn [] (println "Testing slate cljc - leave"))})

(dotest
  (is= 2 (+ 1 1)) ; this works
  (is= 5 (fs/add2 2 3)) ; this works
  (is true)
  (isnt false)
  (isnt= 42 (* 6 6)))
