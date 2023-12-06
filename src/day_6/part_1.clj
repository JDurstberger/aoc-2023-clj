(ns day-6.part-1
  (:require [clojure.string :as string]))

(defn hold->distance
  [time hold]
  (* (- time hold) hold))

(defn find-wins
  [[time record-distance]]
  (->> (range 0 (inc time))
       (map (partial hold->distance time))
       (filter #(> % record-distance))
       (count)))

(defn parse-races
  [raw-races]
  (->> (string/split-lines raw-races)
       (map #(map parse-long (re-seq #"\d+" %)))
       (apply map vector)))

(defn product-of-win-counts
  [raw-races]
  (apply * (map find-wins (parse-races raw-races))))
(product-of-win-counts (slurp "./src/day_6/input.txt"))
