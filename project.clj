(defproject lambda-email-sender "0.1.0-SNAPSHOT"
  :cljsbuild
  {:builds {:prod {:compiler {:language-in   :ecmascript5
                              :optimizations :advanced
                              :output-dir    "target/prod"
                              :output-to     "target/prod/index.js"
                              :source-map    "target/prod/index.js.map"
                              :target        :nodejs}}
            :dev  {:figwheel true
                   :compiler {:language-in :ecmascript5
                              :main        lambda-email-sender.core
                              :output-dir  "target/dev"
                              :output-to   "target/dev/index.js"
                              :target      :nodejs}}
            :test {:compiler     {:language-in :ecmascript5
                                  :main        lambda-email-sender.test-runner
                                  :output-dir  "target/test"
                                  :output-to   "target/test/index.js"
                                  :target      :nodejs}
                   :source-paths ["src" "test"]}}}

  :cljs-lambda
  {:defaults  {:role "arn:aws:iam::635195531909:role/cljs-lambda-default"}
   :functions [{:name   "email-sender"
                :invoke lambda-email-sender.core/lambda-email-sender}]}

  :dependencies
  [[cljsjs/aws-sdk-js "2.2.41-0"]
   [cljsjs/nodejs-externs "1.0.4-1"]
   [io.nervous/cljs-lambda "0.3.0"]
   [io.nervous/cljs-nodejs-externs "0.2.0"]
   [org.clojure/clojure "1.8.0"]
   [org.clojure/clojurescript "1.8.51"]
   [org.clojure/core.async "0.2.374"]]

  :description "FIXME"

  :npm
  {:dependencies [[source-map-support "0.4.0"]
                  [ws "1.1.0"]]}

  :plugins
  [[lein-cljsbuild "1.1.3"]
   [lein-npm "0.6.2"]
   [lein-doo "0.1.7-SNAPSHOT"]
   [io.nervous/lein-cljs-lambda "0.5.1"]]

  :profiles
  {:dev {:dependencies [[com.cemerick/piggieback "0.2.1"]
                        [figwheel-sidecar "0.5.3-1"]]}}

  :source-paths
  ["src"]

  :url
  "http://please.FIXME")
