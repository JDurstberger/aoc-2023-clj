(ns day-1.core
  (:require
    [clojure.string :as string]))

(defn extract-calibration-value
  [line]
  (let [found-digits (re-seq #"\d" line)
        raw-number (str (first found-digits) (last found-digits))]
    (Integer/parseInt raw-number)))

(defn sum-calibration-values
  [calibration-document]
  (let [calibration-values (map extract-calibration-value
                                (string/split-lines calibration-document))]
    (apply + calibration-values)))

(comment
  (sum-calibration-values (slurp "./src/day_1/input.txt"))
  )