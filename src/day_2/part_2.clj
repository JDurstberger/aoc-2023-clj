(ns day-2.part-2
  (:require [clojure.string :as string]))

(def cube-counts
  {"red"   12
   "green" 13
   "blue"  14})

(defn parse-game
  [game]
  (let [game-match (re-find #"Game (\d+)" game)
        dice-match (re-seq #"(\d+) (blue|red|green)" game)]

    {:id    (Integer/parseInt (second game-match))
     :cubes (map (fn [[_ raw-count colour]]
                   {:colour colour
                    :count  (Integer/parseInt raw-count)})
                 dice-match)}))

(defn power-of-minimum-set
  [game]
  (let [grouped-cubes (group-by :colour (:cubes game))
        minimums (map (fn [[_ dices]]
                        (first (sort > (map :count dices))))
                      grouped-cubes)]
    (apply * minimums)))

(defn sum-of-power-of-games
  [games]
  (->> games
       (string/split-lines)
       (map parse-game)
       (map power-of-minimum-set)
       (apply +)))

(comment
  (sum-of-power-of-games (slurp "./src/day_2/input.txt"))
  )