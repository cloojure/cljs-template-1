(ns flintstones.test ; cljc/**/*.clj file defines macros
  (:require [cljs.test :as ct]))

(defmacro use-fixtures  [& forms] `(ct/use-fixtures ~@forms))
(defmacro deftest       [& forms] `(ct/deftest ~@forms))
(defmacro testing       [& forms] `(ct/testing ~@forms))
(defmacro is            [& forms] `(ct/is ~@forms))

(defmacro dotest [& body] ; #todo README & tests
  (let [test-name-sym (symbol (str "dotest-line-" (:line (meta &form))))]
    `(ct/deftest ~test-name-sym ~@body)))

(defmacro isnt      ; #todo readme/test
  "Use (isnt ...) instead of (is (not ...)) for clojure.test"
  [& body]
  `(ct/is (not ~@body)))

(defmacro is=  ; #todo readme/test
  "Use (is= ...) instead of (is (= ...)) for clojure.test"
  [& forms]
  `(ct/is (= ~@forms)))

(defmacro isnt=  ; #todo readme/test
  "Use (isnt= ...) instead of (is (not= ...)) for clojure.test"
  [& body]
  `(ct/is (not (= ~@body))))

