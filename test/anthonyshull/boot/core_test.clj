(ns anthonyshull.boot.core-test
  (:require [clojure.test :refer :all]
            [anthonyshull.boot.core :refer :all]))

(def users ["snoe" "manicolosi"])

(deftest test-common-followers
  (is (= #{"chownation" "joelash"} (get-common-followers "snoe" "manicolosi")))
  (is (= #{} (get-common-followers "skuttleman" "manicolosi"))))
