(ns day-9.part-2
  (:require [clojure.string :as string]))

(defn build-sequences
  [history]
  (loop [sequences-of-differences [history]
         current-sequence history]
    (let [sequence-of-differences (map (fn [[a b]] (- b a)) (partition 2 1 current-sequence))]
      (if (every? zero? sequence-of-differences)
        (conj sequences-of-differences sequence-of-differences)
        (recur (conj sequences-of-differences sequence-of-differences)
               sequence-of-differences)))))

(defn predict-history
  [history]
  (->> (re-seq #"-*\d+" history)
       (map parse-long)
       (build-sequences)
       (reverse)
       (reduce (fn [sum sod] (- (first sod) sum )) 0)))

(defn predict
  [histories]
  (apply + (map predict-history (string/split-lines histories))))

(predict (slurp "./src/day_9/input.txt"))