(ns day-9.part-2-test
  (:require [clojure.string :as string]
            [clojure.test :refer [deftest is]]
            [day-9.part-2 :as part-2]))

(deftest returns-114-for-example-input
  (let [histories (string/join "\n" ["0 3 6 9 12 15"
                                     "1 3 6 10 15 21"
                                     "10 13 16 21 30 45"])]
    (is (= 2 (part-2/predict histories)))))

(deftest returns-5-for-third-example-input-history
  (let [histories "10 13 16 21 30 45"]
    (is (= 5 (part-2/predict histories)))))
