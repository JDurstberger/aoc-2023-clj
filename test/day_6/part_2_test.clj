(ns day-6.part-2-test
  (:require [clojure.test :refer [deftest is]]
            [day-6.part-2 :as part-2]))

(deftest returns-288-for-example-input
  (let [races "Time:      7  15   30\nDistance:  9  40  200"]
    (is (= 71503 (part-2/win-counts races)))))
