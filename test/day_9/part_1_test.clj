(ns day-9.part-1-test
  (:require [clojure.string :as string]
            [clojure.test :refer [deftest is]]
            [day-9.part-1 :as part-1]))

(deftest returns-114-for-example-input
  (let [histories (string/join "\n" ["0 3 6 9 12 15"
                                     "1 3 6 10 15 21"
                                     "10 13 16 21 30 45"])]
    (is (= 114 (part-1/predict histories)))))

(deftest returns-18-for-first-example-input-history
  (let [histories "0 3 6 9 12 15"]
    (is (= 18 (part-1/predict histories)))))

10  13  16  21  30  45