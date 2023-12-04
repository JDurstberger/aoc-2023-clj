(ns day-3.part-2
  (:require [clojure.string :as string]))

(defn calculate-neighbour-index
  [line-index number-index length]
  (let [neighbour-range (range (dec number-index)
                               (inc (+ number-index length)))
        previous-line-neighbours (map (fn [number-index]
                                        {:number-index number-index
                                         :line-index   (dec line-index)})
                                      neighbour-range)
        next-line-neighbours (map (fn [number-index]
                                    {:number-index number-index
                                     :line-index   (inc line-index)})
                                  neighbour-range)
        number-neighbours [{:line-index   line-index
                            :number-index (dec number-index)}
                           {:line-index   line-index
                            :number-index (+ number-index length)}]]
    (concat previous-line-neighbours
            number-neighbours
            next-line-neighbours)))

(defn parse-numbers-in-line
  [line]
  (let [matcher (re-matcher #"\d+" line)
        matches (repeatedly (fn [] (when-let [match (re-find matcher)]
                                     {:number match :index (.start matcher)})))]
    (take-while some? matches)))

(defn index-line
  [line-index line]
  (map (fn [{:keys [number index]}]
         {:number       (parse-long number)
          :last-index   (+ index (count number))
          :line-index   line-index
          :number-index index
          :neighbours   (calculate-neighbour-index line-index index (count number))})
       (parse-numbers-in-line line)))

(defn index-numbers
  [engine-schematics]
  (apply concat (map-indexed index-line (string/split-lines engine-schematics))))

(defn index-schematics-line
  [line-index line]
  [line-index (into {} (map-indexed (fn [idx c] [idx c]) line))])

(defn index-schematics
  [engine-schematics]
  (into {} (map-indexed index-schematics-line (string/split-lines engine-schematics))))

(defn index-gears
  [indexed-schematics]
  (reduce (fn [gears [line-index indexed-characters]]
            (let [line-gears (filter (fn [[_ c]] (= \* c)) indexed-characters)]
              (concat gears (map (fn [[gear_index]] [line-index gear_index]) line-gears))))
          []
          indexed-schematics))

(defn in-adjacent-line?
  [line-index number]
  (#{(dec line-index) line-index (inc line-index)} (:line-index number)))

(defn has-gear-as-neighbour?
  [[line-index gear-index] {:keys [neighbours]}]
  (some (fn [neighbour]
          (and (= (:number-index neighbour) gear-index)
               (= (:line-index neighbour) line-index)))
        neighbours))

(defn calculate-gear-ratio
  [indexed-numbers [line-index :as gear]]
  (let [numbers-neighbouring-gear (filter (fn [number]
                                            (and (in-adjacent-line? line-index number)
                                                 (has-gear-as-neighbour? gear number)))
                                          indexed-numbers)]
    (if (= (count numbers-neighbouring-gear) 2)
      (* (:number (first numbers-neighbouring-gear))
         (:number (second numbers-neighbouring-gear)))
      0)))

(defn sum-of-all-gear-ratios
  [engine-schematics]
  (let [indexed-numbers (index-numbers engine-schematics)
        indexed-schematics (index-schematics engine-schematics)
        indexed-gears (index-gears indexed-schematics)]

    (apply + (map (partial calculate-gear-ratio indexed-numbers) indexed-gears))))

;(sum-of-all-gear-ratios (slurp "./src/day_3/input.txt"))
