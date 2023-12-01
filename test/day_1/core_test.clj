(ns day-1.core-test
  (:require
    [clojure.test :refer [deftest is]]
    [day-1.core :as core]
    [clojure.string :as string]))

(deftest returns-142-for-example-input
  (let [calibration-document (string/join "\n" ["1abc2"
                                                "pqr3stu8vwx"
                                                "a1b2c3d4e5f"
                                                "treb7uchet"])]
    (is (= 142 (core/sum-calibration-values calibration-document)))))

(deftest returns-12-for-1abc2
  (let [calibration-document "1abc2"]
    (is (= 12 (core/sum-calibration-values calibration-document)))))