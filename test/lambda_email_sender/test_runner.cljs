(ns lambda-email-sender.test-runner
 (:require [doo.runner :refer-macros [doo-tests]]
           [lambda-email-sender.core-test]
           [cljs.nodejs :as nodejs]))

(try
  (.install (nodejs/require "source-map-support"))
  (catch :default _))

(doo-tests
 'lambda-email-sender.core-test)
