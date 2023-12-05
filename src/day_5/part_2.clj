(ns day-5.part-2
  (:require [clojure.string :as string]
            [clojure.pprint :as pprint]))

(defn mapping-line-to-mapping
  [[destination-start source-start length]]
  {:source-start      source-start
   :source-end        (+ source-start length)
   :destination-start destination-start})

(defn parse-mapping
  [raw-mapping]
  (let [mapping-lines (rest (string/split-lines raw-mapping))
        parsed-lines (mapv #(mapv parse-long (re-seq #"\d+" %)) mapping-lines)]
    (mapv mapping-line-to-mapping parsed-lines)))

(defn parse-seeds
  [raw-seeds]
  (let [pairs (partition 2 (map parse-long (re-seq #"\d+" raw-seeds)))
        ranges (mapv (fn [[from length]] (range from (+ from length)))
                    pairs)]
    ranges))

(defn parse-almanac
  [raw-almanac]
  (let [[raw-seeds & raw-mappings] (string/split raw-almanac #"\n\n")
        seeds  (parse-seeds raw-seeds)
        mappings (mapv parse-mapping raw-mappings)]
    {:seeds    seeds
     :mappings mappings}))

(defn source-in-range
  [source {:keys [source-start source-end destination-start]}]
  (when (and (>= source source-start)
             (<= source source-end))
    (+ destination-start (- source source-start))))

(defn source-in-mapping
  [source mapping]
  (if-let [destination (some (partial source-in-range source) mapping)]
    destination
    source))

(defn find-location-for-seed
  [mappings seed]
  (reduce source-in-mapping seed mappings))

(defn find-lowest-location
  [mappings seeds]
  (reduce
    (fn [{:keys [lowest-location count] :as acc} seed]
      (when (zero? (rem count 1000000))
        (println "checking seed " count))
      (let [location-for-seed (find-location-for-seed mappings seed)]
        (if (<= location-for-seed lowest-location)
          {:lowest-location location-for-seed
           :count (inc count)}
          (update acc :count inc ))))
    {:lowest-location 999999999
     :count 1}
    seeds))

(defn lowest-location
  [raw-almanac]
  (let [almanac (parse-almanac raw-almanac)]
    (println "parsed almanac " (count (:seeds almanac)))
    (mapv (partial find-lowest-location (:mappings almanac)) (:seeds almanac))))

(lowest-location (slurp "./src/day_5/input.txt"))