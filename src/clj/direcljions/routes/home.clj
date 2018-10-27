(ns direcljions.routes.home
  (:require [direcljions.layout :as layout]
            [compojure.core :refer [defroutes GET POST]]
            [ring.util.http-response :as response]
            [clojure.java.io :as io]))

(defn home-page []
  (layout/render
    "home.html" {:docs (-> "docs/docs.md" io/resource slurp)}))

(defn about-page []
  (layout/render "about.html"))

(defn directions-page []
  (layout/render "directions.html"))

(defn get-directions [{:keys [params]}]
  (layout/render "directions.html" {:start (:start params), :dest (:dest params)}))

(defroutes home-routes
  (GET "/" [] (home-page))
  (GET "/about" [] (about-page))
  (GET "/directions" [] (directions-page))
  (POST "/directions" request (get-directions request)))

