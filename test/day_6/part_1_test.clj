(ns day-6.part-1-test
  (:require [clojure.test :refer [deftest is]]
            [day-6.part-1 :as part-1]))

(deftest returns-288-for-example-input
  (let [races "Time:      7  15   30\nDistance:  9  40  200"]
    (is (= 288 (part-1/product-of-win-counts races)))))
