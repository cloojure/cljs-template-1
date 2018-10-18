(ns tst.flintstones.wilma
  (:require
    [clojure.string :as str]
    #?(:cljs [flintstones.test-cljs :refer [dotest is isnt is= isnt= testing use-fixtures]])
    #?(:cljs [wilmaPhony]) ; This one must be first or get;
         ; "WARNING: Use of undeclared Var cljs.test/test-var at line 15 test/cljs/tst/flintstones/wilma.cljs"
    ))

#?(:cljs
   (use-fixtures :each
     {:before (fn [] (newline) (println "test each - enter"))
      :after  (fn [] (println "test each - leave"))}))
#?(:cljs
   (do
     (dotest
       (is= 3 (+ 2 1)))

     (dotest
       (println "wilmaPhony/stats:   " wilmaPhony/stats)
       (isnt= wilmaPhony/stats wilmaPhony/stats2) ; JS objs are not=

       (is= (js->clj wilmaPhony/stats) ; must convert to clojure maps for value equality to work
         (js->clj wilmaPhony/stats2))

       (let [wilma (wilmaPhony/makeWilma)]
         (println "wilma =>" wilma)
         (is= (.-desc wilma) "patient housewife")
         (is= (.says wilma "Fred") "Hello, Fred")))
     ))
