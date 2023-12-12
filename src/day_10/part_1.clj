(ns day-10.part-1
  (:require [clojure.string :as string]))

(get-in (string/split-lines "abc\n123") [0 1])

(defn connected?
  [{:keys [direction pipe] :as candidate}]
  (let [connections {:north #{\| \7 \F}
                     :south #{\| \J \L}
                     :east  #{\- \J \7}
                     :west  #{\- \L \F}
                     }]
    (when ((direction connections) pipe) candidate)))

(defn find-connection-to-start
  [[line-index character-index] lines]
  (let [candidates [{:direction :north :index [(dec line-index) character-index]}
                    {:direction :west :index [line-index (dec character-index)]}
                    {:direction :east :index [line-index (inc character-index)]}
                    {:direction :south :index [(inc line-index) character-index]}]]
    (->> candidates
         (map (fn [candidate]
                (assoc candidate :pipe (get-in lines (:index candidate)))))
         (filter connected?)
         (first))))

(def next-positions
  {\- {:east [identity inc :east]
       :west [identity dec :west]}
   \| {:south [inc identity :south]
       :north [dec identity :north]}
   \J {:south [identity dec :west]
       :east  [dec identity :north]}
   \F {:north [identity inc :east]
       :west  [inc identity :south]}
   \L {:south [identity inc :east]
       :west  [dec identity :north]}
   \7 {:east  [inc identity :south]
       :north [identity dec :west]}})

(defn find-next-position
  [lines {:keys [index direction pipe] :as position}]
  (let [[line-fn index-fn direction] (get-in next-positions [pipe direction])
        next-index [(line-fn (first index)) (index-fn (second index))]]
    (-> position
        (assoc :index next-index)
        (assoc :pipe (get-in lines next-index))
        (assoc :direction direction))))

(defn find-farthest-step
  [raw-pipes]
  (let [lines (string/split-lines raw-pipes)
        start (->> lines
                   (map-indexed (fn [line-index line] [line-index (string/index-of line "S")]))
                   (filter #(when (second %) %))
                   (first))]
    (/ (loop [count 1
              position (find-connection-to-start start lines)]
         (println position)
         (if (= (:pipe position) \S)
           count
           (recur (inc count)
                  (find-next-position lines position))))
       2)))

(find-farthest-step (slurp "./src/day_10/input.txt"))