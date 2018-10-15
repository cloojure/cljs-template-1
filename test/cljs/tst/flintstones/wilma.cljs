(ns tst.flintstones.wilma
  (:require-macros
    [flintstones.test :refer  [dotest is isnt is= isnt= testing use-fixtures]])
    ; #todo #awt If forget to list a macro here get an error like:
    ;     FAIL in   (dotest-line-13) (TypeError:NaN:NaN)
    ;     failed with TypeError: Cannot read property 'call' of undefined
    ;     message: Uncaught exception, not in assertion.
  (:require
    [wilmaPhony]
  ))

(use-fixtures :each
  {:before (fn [] (newline) (println "Beginning wilma tests..."))
   :after  (fn [] (println "Finished wilma tests..."))})

(dotest
  (is= 3 (+ 2 1)))

(dotest
  (println "wilmaPhony/stats:   " wilmaPhony/stats)
  (isnt= wilmaPhony/stats wilmaPhony/stats2) ; JS objs are not=

  (is= (js->clj wilmaPhony/stats)  ; must convert to clojure maps for value equality to work
       (js->clj wilmaPhony/stats2))

  (let [wilma (wilmaPhony/makeWilma)]
    (println "wilma =>" wilma)
    (is= (.-desc wilma) "patient housewife")
    (is= (.says wilma "Fred") "Hello, Fred")))
