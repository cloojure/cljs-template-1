(ns tst.flintstones.doorunner
  (:require
    [tst.flintstones.dino]
    [tst.flintstones.wilma]
    [tst.flintstones.pebbles]
    [doo.runner :refer-macros [doo-tests]] ))
(enable-console-print!)
(println "doorunner - beginning")

(doo-tests
  'tst.flintstones.dino
  'tst.flintstones.wilma
  'tst.flintstones.pebbles
)
(println "doorunner - end")
