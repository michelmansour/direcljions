(ns direcljions.routes.home
  (:require [direcljions.layout :as layout]
            [compojure.core :refer [defroutes GET POST]]
            [ring.util.http-response :as response]
            [clojure.java.io :as io]
            [clj-http.client :as client]
            [cheshire.core :refer :all]))

(defn home-page []
  (layout/render
   "home.html" {:docs (-> "docs/docs.md" io/resource slurp)}))

(defn about-page []
  (layout/render "about.html"))

(defn directions-page []
  (layout/render "directions.html"))

(defn parse-google-maps-response [json]
  (->>
   (-> (parse-string (:body json) true)
       (get :routes)
       first
       (get :legs)
       first
       (get :steps))
   (map #(get % :html_instructions))))

(defn build-maps-url [start dest api-key]
  (format
   "https://maps.googleapis.com/maps/api/directions/json?origin=%s&destination=%s&key=%s"
   start
   dest
   api-key))

(defn get-directions [{:keys [params]}]
  (layout/render "directions.html" {:start (:start params)
                                    :dest (:dest params)
                                    :directions (parse-google-maps-response
                                                 (client/get
                                                  (build-maps-url
                                                   (:start params)
                                                   (:dest params)
                                                   "API_KEY")))}))

(defroutes home-routes
  (GET "/" [] (home-page))
  (GET "/about" [] (about-page))
  (GET "/directions" [] (directions-page))
  (POST "/directions" request (get-directions request)))

