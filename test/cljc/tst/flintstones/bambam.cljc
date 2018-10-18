(ns tst.flintstones.bambam
  (:require
    #?@(:clj [[flintstones.test-clj   :refer [dotest is isnt is= isnt= testing use-fixtures]]
              [flintstones.bambam :as bam]])
    #?@(:cljs [[flintstones.test-cljs :refer [dotest is isnt is= isnt= testing use-fixtures]]
               [flintstones.bambam :as bam :include-macros true]])
  ))

;****************************************************************
;***** NOTE that fixtures are totally different clj vs cljs *****
;****************************************************************

#?(:clj
   (do
     (defn my-clj-fixture [tst-fn]
       (newline) (println "test each - enter")
       (tst-fn)
       (println "test each - leave"))

     (use-fixtures :each my-clj-fixture)
))

;****************************************************************
;***** NOTE that fixtures are totally different clj vs cljs *****
;****************************************************************
#?(:cljs
   (use-fixtures :each
     {:before (fn [] (newline) (println "test each - enter"))
      :after  (fn [] (println "test each - leave"))}))

;--------------------------------------------------------------------------------------------------

(dotest
  (is= 2 (+ 1 1)))

(dotest
  (is= 5 (bam/add2 2 3)) ; this works
  (is= 3 (bam/logr-bambam
           (inc 0)
           (inc 1)
           (inc 2))))
