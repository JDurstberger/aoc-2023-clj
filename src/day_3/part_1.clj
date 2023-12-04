(ns day-3.part-1
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

(defn symbol-neighbour?
  [indexed-schematics {:keys [number-index line-index]}]
  (let [c (get-in indexed-schematics [line-index number-index])]
    (if (nil? c)
      false
      (not (#{\1 \2 \3 \4 \5 \6 \7 \8 \9 \0 \.} c)))))

(defn any-symbol-neighbours?
  [indexed-schematics {:keys [neighbours]}]
  (some? (first (filter (partial symbol-neighbour? indexed-schematics)
                        neighbours))))

(defn sum-of-all-part-numbers
  [engine-schematics]
  (let [indexed-numbers (index-numbers engine-schematics)
        indexed-schematics (index-schematics engine-schematics)
        numbers-with-neighbours (filter (partial any-symbol-neighbours? indexed-schematics)
                                        indexed-numbers)]
    (reduce (fn [acc number] (+ acc (:number number))) 0 numbers-with-neighbours)))

(sum-of-all-part-numbers (slurp "./src/day_3/input.txt"))

