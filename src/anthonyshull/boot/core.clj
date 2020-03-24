(ns anthonyshull.boot.core
  (:require [clj-http.client :as client]
            [clojure.data.json :as json])
  (:gen-class))

(defn construct-url
  "Given a username, return a URL for followers."
  [username]
  (format "https://acl-github-interview.herokuapp.com/users/%s/followers" username))

(defn get-followers
  "Given a `username` return a list of followers."
  [username]
  (->> username
       construct-url
       client/get
       :body
       json/read-str
       (map #(get % "login"))
       set))

(defn get-common-followers
  "Given two users `user-1` and `user-2` return the followers they have in common."
  [user-1 user-2]
  (let [user-1-followers (get-followers user-1)
        user-2-followers (get-followers user-2)]
    (clojure.set/intersection user-1-followers user-2-followers)))
