(ns day-10.part-2
  (:require [clojure.string :as string]
            [clojure.test :refer [deftest is]]
            [day-10.part-1 :as part-1]))

(deftest returns-4-for-first-example-input
  (let [pipes (string/join "\n" ["....."
                                 ".S-7."
                                 ".|.|."
                                 ".L-J."
                                 "....."])]

    (is (= 4 (part-1/find-farthest-step pipes)))))

(deftest returns-8-for-second-example-input
  (let [pipes (string/join "\n" ["..F7."
                                 ".FJ|."
                                 "SJ.L7"
                                 "|F--J"
                                 "LJ..."])]

    (is (= 8 (part-1/find-farthest-step pipes)))))
