(ns day-8.part-2
  (:require [clojure.string :as string]
            [fastmath.core :as math]))

(defn parse-navigation-documents
  [map-documents]
  (let [[instructions _ & maps] (string/split-lines map-documents)
        maps (into {} (map (fn [m]
                             (let [[_ from to-l to-r] (re-find #"(...) = \((...), (...)\)" m)]
                               [from {\L to-l
                                      \R to-r}]))
                           maps))]

    {:instructions instructions
     :maps         maps}))

(defn count-steps
  [{:keys [instructions maps]} position]
  (loop [current {:instructions (cycle instructions)
                  :count        0
                  :position     position}]
    (if (#{\Z} (last (:position current)))
      (:count current)
      (recur (-> current
                 (update :count inc)
                 (update :instructions rest)
                 (update :position (fn [current-position]
                                     (get-in maps [current-position
                                                   (first (:instructions current))]))))))))

(defn navigation-steps
  [map-documents]
  (let [navigation-documents (parse-navigation-documents map-documents)]
    (->> (:maps navigation-documents)
         (keys)
         (filter (comp #{\A} last))
         (map (fn [starting-position] (count-steps navigation-documents starting-position)))
         (reduce math/lcm))))

(navigation-steps (slurp "./src/day_8/input.txt"))