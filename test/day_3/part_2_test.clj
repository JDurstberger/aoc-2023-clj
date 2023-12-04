(ns day-3.part-2-test
  (:require [clojure.test :refer [deftest is]]
            [clojure.string :as string]
            [day-3.part-2 :as part-2]))

(deftest returns-4361-for-example-input
  (let [engine-schematics (string/join "\n" ["467..114.."
                                             "...*......"
                                             "..35..633."
                                             "......#..."
                                             "617*......"
                                             ".....+.58."
                                             "..592....."
                                             "......755."
                                             "...$.*...."
                                             ".664.598.."])]
    (is (= 467835 (part-2/sum-of-all-gear-ratios engine-schematics)))))
