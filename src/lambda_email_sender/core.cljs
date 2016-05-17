(ns lambda-email-sender.core
  (:require [cljs-lambda.macros :refer-macros [deflambda]]
            [cljs.nodejs :as nodejs]
            [cljs.core.async :as async]
            cljsjs.aws-sdk-js)
  (:require-macros [cljs.core.async.macros :refer [go]]))

(def ses (.SES. js/AWS))

(def max-send-rate-chan (async/chan))
(.getSendQuota ses (fn [error data]
                     (if error
                       (throw error)
                       (async/put! max-send-rate-chan (aget data "MaxSendRate")))))
(def max-send-rate true)

(deflambda work-magic [{:keys [datomic]} context]
  (go true))

;; For optimizations :advanced
(set! *main-cli-fn* identity)
