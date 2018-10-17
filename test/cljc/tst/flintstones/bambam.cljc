(ns tst.flintstones.bambam
  (:require
    [flintstones.test :refer [dotest is isnt is= isnt= testing use-fixtures]]
    [flintstones.bambam :as bam])
  (:require-macros 
    [flintstones.bambam :as bam]))

(dotest
  (is= 2 (+ 1 1))   ; this works
  (is= 5 (bam/add2 2 3)) ; this works
  (is= 3 (bam/logr-bambam
           (inc 0)
           (inc 1)
           (inc 2))) )
