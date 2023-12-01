(ns day-1.core-2-test
  (:require
    [clojure.test :refer [deftest is]]
    [day-1.core-2 :as core]
    [clojure.string :as string]))

(deftest returns-281-for-example-input
  (let [calibration-document (string/join "\n" ["two1nine"
                                                "eightwothree"
                                                "abcone2threexyz"
                                                "xtwone3four"
                                                "4nineeightseven2"
                                                "zoneight234"
                                                "7pqrstsixteen"])]
    (is (= 281 (core/sum-calibration-values calibration-document)))))

(deftest returns-29-for-two1nine
  (let [calibration-document "two1nine"]
    (is (= 29 (core/sum-calibration-values calibration-document)))))

(deftest correctly-handles-overlapping-digits
  (let [calibration-document "2oneight"]
    (is (= 28 (core/sum-calibration-values calibration-document)))))