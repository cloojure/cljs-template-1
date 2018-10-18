(ns tst.flintstones.bambam
  (:require
    #?@(:clj [[flintstones.test-clj   :refer [dotest is isnt is= isnt= testing use-fixtures]]
              [flintstones.bambam :as bam]])
    #?@(:cljs [[flintstones.test-cljs :refer [dotest is isnt is= isnt= testing use-fixtures]]
               [flintstones.bambam :as bam :include-macros true]])
  ))

(dotest
  (is= 2 (+ 1 1))   ; this works
  (is= 5 (bam/add2 2 3)) ; this works
  (is= 3 (bam/logr-bambam
           (inc 0)
           (inc 1)
           (inc 2))))
