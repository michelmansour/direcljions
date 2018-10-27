(ns user
  (:require [direcljions.config :refer [env]]
            [clojure.spec.alpha :as s]
            [expound.alpha :as expound]
            [mount.core :as mount]
            [direcljions.core :refer [start-app]]))

(alter-var-root #'s/*explain-out* (constantly expound/printer))

(defn start []
  (mount/start-without #'direcljions.core/repl-server))

(defn stop []
  (mount/stop-except #'direcljions.core/repl-server))

(defn restart []
  (stop)
  (start))


