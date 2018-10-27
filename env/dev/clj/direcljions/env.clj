(ns direcljions.env
  (:require [selmer.parser :as parser]
            [clojure.tools.logging :as log]
            [direcljions.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init
   (fn []
     (parser/cache-off!)
     (log/info "\n-=[direcljions started successfully using the development profile]=-"))
   :stop
   (fn []
     (log/info "\n-=[direcljions has shut down successfully]=-"))
   :middleware wrap-dev})
