(ns flintstones.test-clj
  (:require
    [clojure.string :as str]
    [clojure.test :as ct] ))

(defn normalize-str
  "Returns a 'normalized' version of str-in, stripped of leading/trailing
   blanks, and with all non-alphanumeric chars converted to hyphens."
  [str-in]
  (-> str-in
    str/trim
    (str/replace #"[^a-zA-Z0-9]" "-")))

(defn use-fixtures [& args] (apply ct/use-fixtures args))
(defmacro testing [& forms] `(ct/testing ~@forms))

(defmacro is
  "Equivalent to clojure.test/is."
  [& forms]
  (if (not= (count forms) 1)
    (let [line-str (str "[source line=" (:line (meta &form)) "]")]
      `(throw (IllegalArgumentException.
                (str "tupelo.test/is requires exactly 1 form " ~line-str))))
    `(ct/is ~@forms)))

(defmacro isnt      ; #todo readme/test
  "Use (isnt ...) instead of (is (not ...)) for clojure.test"
  [& forms]
  (if (not= (count forms) 1)
    (let [line-str (str "[source line=" (:line (meta &form)) "]")]
      `(throw (IllegalArgumentException.
                (str "tupelo.test/isnt requires exactly 1 form " ~line-str))))
    `(ct/is (not ~@forms))))

(defmacro is=       ; #todo readme/test
  "Use (is= ...) instead of (is (= ...)) for clojure.test"
  [& forms]
  (if (<= (count forms) 1)
    (let [line-str (str "[source line=" (:line (meta &form)) "]")]
      `(throw (IllegalArgumentException.
                (str "tupelo.test/is= requires at least 2 forms " ~line-str))))
    `(is (= ~@forms))))

(defmacro isnt=     ; #todo readme/test
  "Use (isnt= ...) instead of (is (not= ...)) for clojure.test"
  [& forms]
  (if (<= (count forms) 1)
    (let [line-str (str "[source line=" (:line (meta &form)) "]")]
      `(throw (IllegalArgumentException.
                (str "tupelo.test/isnt= requires at least 2 forms " ~line-str))))
    `(isnt (= ~@forms))))

(defmacro dotest    ; #todo README & tests
  "Alias for tupelo.test/deftest "
  [& items]
  (let [item-1 (clojure.core/first items)
        suffix (str "-line-" (:line (meta &form)))
        [label forms] (cond
                        (symbol? item-1) [(symbol (str (clojure.core/name item-1) suffix)) (vec (clojure.core/rest items))]
                        (string? item-1) [(symbol (str (normalize-str item-1) suffix)) (vec (clojure.core/rest items))]
                        :else [(symbol (str "dotest-block" suffix)) (vec items)])]
    `(def ~(vary-meta label assoc
             :test `(fn [] ~@forms))
       (fn [] (clojure.test/test-var (var ~label))))))

