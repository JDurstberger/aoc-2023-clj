(ns day-8.part-2-test
  (:require [clojure.string :as string]
            [clojure.test :refer [deftest is]]
            [day-8.part-2 :as part-2]))


(deftest take-2-steps-for-first-example-input
  (let [map-documents (string/join "\n" ["LR" ""
                                         "11A = (11B, XXX)"
                                         "11B = (XXX, 11Z)"
                                         "11Z = (11B, XXX)"
                                         "22A = (22B, XXX)"
                                         "22B = (22C, 22C)"
                                         "22C = (22Z, 22Z)"
                                         "22Z = (22B, 22B)"
                                         "XXX = (XXX, XXX)"])]

    (is (= 6 (part-2/navigation-steps map-documents)))))