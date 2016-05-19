(ns lambda-email-sender.core
  (:require [cljs-lambda.macros :refer-macros [deflambda]]
            [cljs.nodejs :as nodejs]
            [cljs.core.async :as async])
  (:require-macros [cljs.core.async.macros :refer [go]]))

(def AWS (nodejs/require "aws-sdk"))

(def ses (AWS.SES. #js {:region "us-west-2"}))

(defn get-send-quota []
  (let [result-chan (async/chan 1 (map js->clj))]
    (.getSendQuota ses (fn [error data]
                         (if error
                           (throw error)
                           (async/put! result-chan data))))
    result-chan))

(deflambda work-magic [{:keys [datomic]} context]
  (go true))

;; For optimizations :advanced
(set! *main-cli-fn* identity)
