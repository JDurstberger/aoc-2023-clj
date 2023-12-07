(ns day-7.part-1
  (:require [clojure.string :as string]))

(defn hand->type
  [hand]
  (->> (group-by identity hand)
       (vals)
       (map count)
       (sort >)
       ((fn [counts]
          (cond
            (= counts [5]) :five-of-a-kind
            (= counts [4 1]) :four-of-a-kind
            (= counts [3 2]) :full-house
            (= counts [3 1 1]) :three-of-a-kind
            (= counts [2 2 1]) :two-pairs
            (= counts [2 1 1 1]) :one-pair
            :else :high-card)))))

(def type->strengths
  {:five-of-a-kind  7
   :four-of-a-kind  6
   :full-house      5
   :three-of-a-kind 4
   :two-pairs       3
   :one-pair        2
   :high-card       1})

(defn parse-hands
  [raw-hands]
  (map (fn [hand]
         (let [[_ hand bid] (re-find #"(.....) (\d+)" hand)
               type (hand->type hand)]
           {:hand     hand
            :bid      (parse-long bid)
            :type     type
            :strength (type->strengths type)}))
       (string/split-lines raw-hands)))

(def card->strength
  (->> [\2 \3 \4 \5 \6 \7 \8 \9 \T \J \Q \K \A]
       (map-indexed #(vector %2 %1))
       (into {})))

(defn compare-high-cards
  [cards-1 cards-2]
  (loop [index 0]
    (let [card-1-strength (card->strength (nth cards-1 index))
          card-2-strength (card->strength (nth cards-2 index))]
      (cond
        (> card-1-strength card-2-strength) 1
        (< card-1-strength card-2-strength) -1
        :else (recur (inc index))))))

(defn compare-hands
  [hand-1 hand-2]
  (cond
    (> (:strength hand-1) (:strength hand-2)) 1
    (< (:strength hand-1) (:strength hand-2)) -1
    :else (compare-high-cards (:hand hand-1) (:hand hand-2))))

(defn calculate-winnings
  [hands]
  (:winnings
    (reduce (fn [{:keys [count] :as acc} hand]
              (-> acc
                  (update :winnings + (* (:bid hand) count))
                  (update :count inc)))
            {:winnings 0
             :count    1}
            hands)))

(defn sum-winnings
  [raw-hands]
  (->> raw-hands
       (parse-hands)
       (sort compare-hands)
       (calculate-winnings)))

(sum-winnings (slurp "./src/day_7/input.txt"))