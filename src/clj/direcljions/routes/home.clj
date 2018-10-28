(ns direcljions.routes.home
  (:require [direcljions.layout :as layout]
            [compojure.core :refer [defroutes GET POST]]
            [ring.util.http-response :as response]
            [clojure.java.io :as io]
            [clj-http.client :as client]
            [cheshire.core :refer :all]
            [struct.core :as st]))

(defn home-page []
  (response/found "/directions"))

(defn about-page []
  (layout/render "about.html"))

(defn directions-page [{:keys [flash]}]
  (layout/render
   "directions.html"
   (merge {:start (:start flash) :dest (:dest flash)}
          (select-keys flash [:name :message :errors]))))

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

(def route-req-schema
  [[:start [st/required
            :message "Please enter an origin"]]
   [:dest [st/required
           :message "Please enter a destination"]]])

(defn validate-route-request [params]
  (first (st/validate params route-req-schema)))

(defn get-directions [{:keys [params]}]
  (if-let [errors (validate-route-request params)]
    (-> (response/found "directions")
        (assoc :flash (assoc params :errors errors)))
    (layout/render "directions.html" {:start (:start params)
                                      :dest (:dest params)
                                      :directions (parse-google-maps-response
                                                   (client/get
                                                    (build-maps-url
                                                     (:start params)
                                                     (:dest params)
                                                     "API_KEY")))})))

(defroutes home-routes
  (GET "/" [] (home-page))
  (GET "/about" [] (about-page))
  (GET "/directions" request (directions-page request))
  (POST "/directions" request (get-directions request)))
