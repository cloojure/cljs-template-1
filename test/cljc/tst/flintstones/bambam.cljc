(ns tst.flintstones.bambam
  (:require
    [flintstones.test :refer [dotest is isnt is= isnt= testing use-fixtures]]
    #?(:clj  [flintstones.bambam :as bam])
    #?(:cljs [flintstones.bambam :as bam :include-macros true]))
)

(dotest
  (is= 2 (+ 1 1))   ; this works
  (is= 5 (bam/add2 2 3)) ; this works
  (is= 3 (bam/logr-bambam
           (inc 0)
           (inc 1)
           (inc 2))) )
