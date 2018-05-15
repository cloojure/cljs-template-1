(defproject flintstones "0.1.0-SNAPSHOT"
  :min-lein-version "2.7.1"
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.clojure/clojurescript "1.10.238"]
                 [reagent "0.8.1"]
                 [tupelo "0.9.76"]]
  :plugins [[lein-cljsbuild "1.1.7" :exclusions [[org.clojure/clojure]]]
            [lein-figwheel "0.5.15"]
            [lein-doo "0.1.10"]]

  :doo {:karma {:config {"plugins"       ["karma-junit-reporter"]
                         "reporters"     ["progress" "junit"]
                         "junitReporter" {"outputDir" "target/test-results"}}}
        :paths {:karma   "node_modules/karma/bin/karma"
                :phantom "node_modules/phantomjs/bin/phantomjs"}}
  :source-paths ["src"]
  :cljsbuild {:builds
              [{:id           "dev"
                :source-paths ["src"]
                ; The presence of a :figwheel configuration here will cause figwheel to inject the
                ; figwheel client into your build
                :figwheel     {:on-jsload "flintstones.core/on-js-reload"
                               ; :open-urls will pop open your application in the default browser once
                               ; Figwheel has started and compiled your application.  Comment this out
                               ; once it no longer serves you.
                               :open-urls ["http://localhost:3449/index.html"]}
                :compiler     {:main                 flintstones.core
                               :optimizations        :none
                               :libs                 ["resources/public/libs"] ; recursive includes all children

                               ; figwheel server has implicit path `resources/public`, leave off here
                               :foreign-libs         [{:file     "dino.js"
                                                       :provides ["dinoPhony"]}]
                               :externs              ["dino-externs.js"]

                               :output-to            "resources/public/js/compiled/flintstones.js"
                               :output-dir           "resources/public/js/compiled/flintstones-dev"
                               :asset-path           "js/compiled/flintstones-dev" ; rel to figwheel default of `resources/public`
                               ;                       ^^^^^ must match :output-dir
                               :source-map-timestamp true}}
               {:id           "test"
                :source-paths ["src" "test"]
                :compiler     {:main                 tst.flintstones.doorunner
                               :optimizations        :none ; :advanced
                               :libs                 ["resources/public/libs"] ; recursive includes all children

                               ; tests run w/o figwheel server, so need to explicitely add path `resources/public` here
                               :foreign-libs         [{:file     "resources/public/dino.js"
                                                       :provides ["dinoPhony"]}]
                               :externs              ["resources/public/dino-externs.js"]

                               :output-to            "resources/public/js/compiled/bedrock.js"
                               :output-dir           "resources/public/js/compiled/bedrock-tst"
                               ; :asset-path           "js/compiled/bedrock-tst"  ; not used for testing
                               ; ^^^ rel to figwheel default of `resources/public`

                               :source-map-timestamp true}}]}

  ; need to add the compliled assets to the :clean-targets
  :clean-targets ^{:protect false} ["resources/public/js/compiled"
                                    "out"
                                    :target-path]

  ; automatically handle `--add-modules` stuff req'd for Java 9 & Java 10
  :jvm-opts #=(eval (into ["-Xmx1g"]
                      (let [version-str (System/getProperty "java.version")]
                        (if (or (= "10" version-str) (re-find #"^9\." version-str))
                          ["--add-modules" "java.xml.bind"] ; needed for java 9 or 10
                          [])))) ; java 8 or below
  )
