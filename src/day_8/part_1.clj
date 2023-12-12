(ns day-8.part-1
  (:require [clojure.string :as string]))

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
  [{:keys [instructions maps]}]
  (loop [current {:instructions (cycle instructions)
                  :count        0
                  :position     "AAA"}]
    (if (= (:position current) "ZZZ")
      (:count current)
      (recur (-> current
                 (update :count inc)
                 (update :instructions rest)
                 (update :position (fn [current-position]
                                     (get-in maps [current-position
                                                   (first (:instructions current))]))))))))

(defn navigation-steps
  [map-documents]
  (count-steps (parse-navigation-documents map-documents)))

(navigation-steps (slurp "./src/day_8/input.txt"))