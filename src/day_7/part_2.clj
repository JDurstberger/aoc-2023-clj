(ns day-7.part-2
  (:require [clojure.string :as string]))

(defn rank-up-type
  [type joker-count]
  (if (zero? joker-count)
    type
    (case type
      :four-of-a-kind :five-of-a-kind
      :full-house :five-of-a-kind
      :three-of-a-kind (case joker-count
                         2 :five-of-a-kind
                         1 :four-of-a-kind)
      :two-pairs :full-house
      :one-pair (case joker-count
                  1 :three-of-a-kind
                  2 :four-of-a-kind
                  3 :five-of-a-kind)
      :high-card (case joker-count
                   1 :one-pair
                   2 :three-of-a-kind
                   3 :four-of-a-kind
                   4 :five-of-a-kind
                   5 :five-of-a-kind))))

(defn counts->type
  [hand counts]
  (let [highest-count (first counts)
        initial-type (cond
                       (= highest-count 5) :five-of-a-kind
                       (= highest-count 4) :four-of-a-kind
                       (= counts [3 2]) :full-house
                       (= highest-count 3) :three-of-a-kind
                       (= (take 2 counts) [2 2]) :two-pairs
                       (= highest-count 2) :one-pair
                       :else :high-card)
        joker-count (count (re-seq #"J" hand))]
    (rank-up-type initial-type joker-count)))

(defn hand->type
  [hand]
  (->> (group-by identity hand)
       ((fn [groups] (dissoc groups \J)))
       (vals)
       (map count)
       (sort >)
       ((partial counts->type hand))))

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
  (->> [\J \2 \3 \4 \5 \6 \7 \8 \9 \T \Q \K \A]
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