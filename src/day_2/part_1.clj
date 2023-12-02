(ns day-2.part-1
  (:require [clojure.string :as string]))

(def cube-counts
  {"red" 12
   "green" 13
   "blue" 14})

(defn parse-game
  [game]
  (let [game-match (re-find #"Game (\d+)" game)
        dice-match (re-seq #"(\d+) (blue|red|green)" game)]

    {:id (Integer/parseInt (second game-match))
     :cubes (map (fn [[ _ raw-count colour]]
                   {:colour colour
                    :count (Integer/parseInt raw-count)})
                 dice-match)}))

(defn possible?
  [{:keys [cubes]}]
  (every? (fn [{ :keys [colour count]}]
               (<= count (get cube-counts colour)))
          cubes))

(defn sum-of-valid-game-ids
  [games]
  (->> games
       (string/split-lines)
       (map parse-game)
       (filter possible?)
       (map :id)
       (apply +)))

(comment
  (sum-of-valid-game-ids (slurp "./src/day_2/input.txt"))
  )