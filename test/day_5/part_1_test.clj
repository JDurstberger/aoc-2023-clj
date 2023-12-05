(ns day-5.part-1-test
  (:require [clojure.string :as string]
            [clojure.test :refer [deftest is]]
            [day-5.part-1 :as part-1]))

(deftest returns-35-for-example-input
  (let [almanac (string/join "\n" [
                           "seeds: 79 14 55 13"
                           ""
                           "seed-to-soil map:"
                           "50 98 2"
                           "52 50 48"
                           ""
                           "soil-to-fertilizer map:"
                           "0 15 37"
                           "37 52 2"
                           "39 0 15"
                           ""
                           "fertilizer-to-water map:"
                           "49 53 8"
                           "0 11 42"
                           "42 0 7"
                           "57 7 4"
                           ""
                           "water-to-light map:"
                           "88 18 7"
                           "18 25 70"
                           ""
                           "light-to-temperature map:"
                           "45 77 23"
                           "81 45 19"
                           "68 64 13"
                           ""
                           "temperature-to-humidity map:"
                           "0 69 1"
                           "1 0 69"
                           ""
                           "humidity-to-location map:"
                           "60 56 37"
                           "56 93 4"])]

    (is (= 35 (part-1/lowest-location almanac)))))

(deftest returns-1-when-maps-1-all-the-way-through
  (let [almanac (string/join "\n" ["seeds: 1"
                                   ""
                                   "seed-to-soil map:"
                                   "1 1 0"
                                   ""
                                   "soil-to-fertilizer map:"
                                   "1 1 0"
                                   ""
                                   "fertilizer-to-water map:"
                                   "1 1 0"
                                   ""
                                   "water-to-light map:"
                                   "1 1 0"
                                   ""
                                   "light-to-temperature map:"
                                   "1 1 0"
                                   ""
                                   "temperature-to-humidity map:"
                                   "1 1 0"
                                   ""
                                   "humidity-to-location map:"
                                   "1 1 0"])]

    (is (= 1 (part-1/lowest-location almanac)))))

(deftest returns-1-when-maps-1-all-the-way-through-except-for-location
  (let [almanac (string/join "\n" ["seeds: 1"
                                   ""
                                   "seed-to-soil map:"
                                   "1 1 0"
                                   ""
                                   "soil-to-fertilizer map:"
                                   "1 1 0"
                                   ""
                                   "fertilizer-to-water map:"
                                   "1 1 0"
                                   ""
                                   "water-to-light map:"
                                   "1 1 0"
                                   ""
                                   "light-to-temperature map:"
                                   "1 1 0"
                                   ""
                                   "temperature-to-humidity map:"
                                   "1 1 0"
                                   ""
                                   "humidity-to-location map:"
                                   "2 1 0"])]

    (is (= 2 (part-1/lowest-location almanac)))))

(deftest returns-1-when-maps-1-all-the-way-through-and-humidity-to-location-not-mapped
  (let [almanac (string/join "\n" ["seeds: 1"
                                   ""
                                   "seed-to-soil map:"
                                   "1 1 0"
                                   ""
                                   "soil-to-fertilizer map:"
                                   "1 1 0"
                                   ""
                                   "fertilizer-to-water map:"
                                   "1 1 0"
                                   ""
                                   "water-to-light map:"
                                   "1 1 0"
                                   ""
                                   "light-to-temperature map:"
                                   "1 1 0"
                                   ""
                                   "temperature-to-humidity map:"
                                   "1 1 0"
                                   ""
                                   "humidity-to-location map:"
                                   "2 2 0"])]

    (is (= 1 (part-1/lowest-location almanac)))))