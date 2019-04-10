(defproject cljs-template "0.1.0-SNAPSHOT"
  :min-lein-version "2.8.1"
  :exclusions [
               org.clojure/clojure
               org.clojure/clojurescript
               ]
  :dependencies [
                 [binaryage/oops "0.7.0"]
                 [org.clojure/clojure "1.10.0"]
                 [org.clojure/clojurescript "1.10.520"]
                 [reagent "0.8.1"]
                 [reagent-utils "0.3.2"]
                 [tupelo "0.9.138"]
                 ]
  :plugins [ [lein-cljsbuild "1.1.7" ]
            [lein-doo "0.1.10"]
            [lein-figwheel "0.5.18"] ]

  :doo {:karma {:config {"plugins"       ["karma-junit-reporter"]
                         "reporters"     ["progress" "junit"]
                         "junitReporter" {"outputDir" "target/test-results"}}}
        :paths {:karma   "node_modules/karma/bin/karma"
                :phantom "node_modules/phantomjs/bin/phantomjs"}}

  :source-paths ["src/cljc" "src/clj"]
  :test-paths ["test/cljc" "test/clj"] ; NOTE:  :test-paths doesn't work for cljs

  :cljsbuild {:builds
              [
               {:id           "dev"
                :source-paths ["src/cljc" "src/cljs"]
                ; The presence of a :figwheel configuration here will cause figwheel to inject the
                ; figwheel client into your build
                :figwheel     {:on-jsload "flintstones.core/figwheel-reload"
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
                               :output-dir           "resources/public/js/compiled/flintstones-out.d" ; #todo delete this?
                               :asset-path           "js/compiled/flintstones-out.d" ; rel to figwheel default of `resources/public`
                               ;                       ^^^^^ must match :output-dir

                               :source-map-timestamp true
                               :parallel-build       true ; #todo #awt test this
                               }
                }
               {:id           "test"
                :source-paths ["src/cljc" "test/cljc"
                               "src/cljs" "test/cljs"] ; NOTE:  :test-paths doesnt work for cljs
                :compiler     {:main                 tst.flintstones.doorunner
                               :optimizations        :none ; :advanced
                               :libs                 ["resources/public/libs"] ; recursively includes all children

                               ; tests run w/o figwheel server, so need to explicitely add path `resources/public` here
                               :foreign-libs         [{:file     "resources/public/dino.js"
                                                       :provides ["dinoPhony"]}]
                               :externs              ["resources/public/dino-externs.js"]

                               :output-to            "resources/public/js/compiled/bedrock.js"
                               :output-dir           "resources/public/js/compiled/bedrock-out.d"
                               ; :asset-path           "who-cares.d"  ; not used for testing
                               ;                       ^^^^^ must match :output-dir

                               :source-map-timestamp true
                               :parallel-build       true ; #todo #awt test this
                               }}]}

  ; need to add the compliled assets to the :clean-targets
  :clean-targets ^{:protect false} ["resources/public/js/compiled"
                                    "out"
                                    :target-path]

  :jvm-opts ["-Xms1g" "-Xmx4g"]
)
