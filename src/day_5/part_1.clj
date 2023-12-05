(ns day-5.part-1
  (:require [clojure.string :as string]
            [clojure.pprint :as pprint]))

;(defn mapping-line-to-map
;  [[destination-start source-start length]]
;  (println "mapping-line-to-map")
;  (let [_ (println "building source range")
;        source-range (range source-start (+ source-start length 1))
;        _ (println "building destination range")
;        destination-range (range destination-start (+ destination-start length 1))
;        _ (println "combine into vectors")
;        vectors (map vector source-range destination-range)]
;    (println "into map")
;    (into {} vectors)))

(defn mapping-line-to-mapping
  [[destination-start source-start length]]
  {:source-start      source-start
   :source-end        (+ source-start length)
   :destination-start destination-start})

(defn parse-mapping
  [raw-mapping]
  (let [mapping (second (re-find #"(.*) map:" raw-mapping))
        ;_ (println "parse mapping for " mapping "" (java.time.LocalDateTime/now))
        mapping-lines (rest (string/split-lines raw-mapping))
        parsed-lines (map #(map parse-long (re-seq #"\d+" %)) mapping-lines)]
    (map mapping-line-to-mapping parsed-lines)))

(defn parse-almanac
  [raw-almanac]
  (println "parse almanac")
  (let [[raw-seeds & raw-mappings] (string/split raw-almanac #"\n\n")
        seeds (map parse-long (re-seq #"\d+" raw-seeds))
        mappings (map parse-mapping raw-mappings)]
    {:seeds    seeds
     :mappings mappings}))

;;number 4
;;source-start + range -> (and (4 >= source-start) (4 <- source-end))
;;(number - source-start) ->
;;destination-start + offset

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

(defn location-for-seed
  [mappings seed]
  (reduce source-in-mapping seed mappings))

(defn lowest-location
  [raw-almanac]
  (let [almanac (parse-almanac raw-almanac)]
    (->> (:seeds almanac)
           (map (partial location-for-seed (:mappings almanac)))
           (sort)
           (first))))

(lowest-location (slurp "./src/day_5/input.txt"))