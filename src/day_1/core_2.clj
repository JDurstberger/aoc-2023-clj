(ns day-1.core-2
  (:require
    [clojure.string :as string]))

(defn match->digit
  [match]
  (case match
    "one" "1"
    "two" "2"
    "three" "3"
    "four" "4"
    "five" "5"
    "six" "6"
    "seven" "7"
    "eight" "8"
    "nine" "9"
    match))

(def digit-regex
  #"(?=(\d|one|two|three|four|five|six|seven|eight|nine))")

(defn extract-calibration-value
  [line]
  (let [found-digits (re-seq digit-regex line)
        first-digit (match->digit (second (first found-digits)))
        second-digit (match->digit (second (last found-digits)))
        raw-number (str first-digit second-digit)]
    (Integer/parseInt raw-number)))

(defn sum-calibration-values
  [calibration-document]
  (let [calibration-values (map extract-calibration-value
                                (string/split-lines calibration-document))]
    (apply + calibration-values)))

(comment
  (sum-calibration-values (slurp "./src/day_1/input.txt"))
  )