(ns day-4.part-2
  (:require [clojure.string :as string]))

(defn parse-card
  [card]
  (let [[_ card-number numbers] (re-find #"Card\s*(\d+): (.*)" card)
        [winning-numbers our-numbers] (string/split numbers #"\|")]
    {:card-number     (parse-long card-number)
     :winning-numbers (map parse-long (re-seq #"\d+" winning-numbers))
     :our-numbers     (map parse-long (re-seq #"\d+" our-numbers))}))

(defn calculate-card-score
  [{:keys [winning-numbers our-numbers]}]
  (count (filter (set winning-numbers) our-numbers)))

(defn to-scoring-shape
  [cards]
  (into (sorted-map) (map (fn [card]
                  [(:card-number card)
                   {:win-count (calculate-card-score card)
                    :count     1}])
                cards)))

(defn add-scores
  [cards [card-number {:keys [win-count]}]]
  (let [cnt (get-in cards [card-number :count])
        next-card (inc card-number)
        last-card (+ (inc card-number) win-count)
        cards-to-increment (range next-card last-card)]
    (reduce (fn [cards card-number]
              (update-in cards [card-number :count] + cnt))
            cards
            cards-to-increment) ))

(defn sum-cards
  [cards]
  (let [parsed-cards (map parse-card (string/split-lines cards))
        card-scores (to-scoring-shape parsed-cards)]

    (->> card-scores
         (reduce add-scores card-scores)
         (vals)
         (map :count)
         (apply +))))

(sum-cards (slurp "./src/day_4/input.txt"))