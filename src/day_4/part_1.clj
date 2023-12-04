(ns day-4.part-1
  (:require [clojure.string :as string]))

(defn parse-card
  [card]
  (println card)
  (let [[_ numbers] (re-find #"Card.*: (.*)" card)
        [winning-numbers our-numbers] (string/split numbers #"\|")]
    {:winning-numbers (map parse-long (re-seq #"\d+" winning-numbers))
     :our-numbers (map parse-long (re-seq #"\d+" our-numbers))} ))


(defn calculate-card-score
  [{:keys [winning-numbers our-numbers]} ]
  (let [win-count(count (filter (set winning-numbers) our-numbers))]
    (if (pos? win-count)
      (reduce * 1 (repeat (dec win-count) 2))
      0)) )

(defn sum-wins
  [cards]
  (apply + (map calculate-card-score (map parse-card (string/split-lines cards)))))

;(sum-wins (slurp "./src/day_4/input.txt"))