(ns direcljions.env
  (:require [clojure.tools.logging :as log]))

(def defaults
  {:init
   (fn []
     (log/info "\n-=[direcljions started successfully]=-"))
   :stop
   (fn []
     (log/info "\n-=[direcljions has shut down successfully]=-"))
   :middleware identity})
