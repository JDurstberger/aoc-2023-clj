(ns day-3.part-1-test
  (:require [clojure.test :refer [deftest is]]
            [clojure.string :as string]
            [day-3.part-1 :as part-1]))

(deftest returns-4361-for-example-input
  (let [engine-schematics (string/join "\n" ["467..114.."
                                             "...*......"
                                             "..35..633."
                                             "......#..."
                                             "617*......"
                                             ".....+.58."
                                             "..592....."
                                             "......755."
                                             "...$.*...."
                                             ".664.598.."])]
    (is (= 4361 (part-1/sum-of-all-part-numbers engine-schematics)))))

(deftest returns-0-when-number-is-not-next-to-symbol
  (is (= 0 (part-1/sum-of-all-part-numbers "1"))))

(deftest returns-1-when-1-is-right-of-symbol
  (is (= 1 (part-1/sum-of-all-part-numbers "+1"))))

(deftest returns-1-when-only-one-1-is-next-to-symbol
  (is (= 1 (part-1/sum-of-all-part-numbers "1.1+"))))

(deftest returns-33-when-equals-in-front
  (is (= 33 (part-1/sum-of-all-part-numbers "336...=33....."))))
