(ns day-6.part-2
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
       (map #(re-seq #"\d+" %))
       (map #(parse-long (string/join %))) ))

(defn win-counts
  [raw-races]
  (find-wins (parse-races raw-races)))

(win-counts (slurp "./src/day_6/input.txt"))
