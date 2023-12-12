(ns day-8.part-1-test
  (:require [clojure.string :as string]
            [clojure.test :refer [deftest is]]
            [day-8.part-1 :as part-1]))


(deftest take-2-steps-for-first-example-input
  (let [map-documents (string/join "\n" ["RL" ""
                               "AAA = (BBB, CCC)"
                               "BBB = (DDD, EEE)"
                               "CCC = (ZZZ, GGG)"
                               "DDD = (DDD, DDD)"
                               "EEE = (EEE, EEE)"
                               "GGG = (GGG, GGG)"
                               " ZZZ = (ZZZ, ZZZ)"])]

    (is (= 2 (part-1/navigation-steps map-documents)))))