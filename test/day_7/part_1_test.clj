(ns day-7.part-1-test
  (:require [clojure.string :as string]
            [clojure.test :refer [deftest is are]]
            [day-7.part-1 :as part-1]))

(deftest returns-6440-for-example-input
  (let [hands (string/join "\n" ["32T3K 765"
                                 "T55J5 684"
                                 "KK677 28"
                                 "KTJJT 220"
                                 "QQQJA 483"])]

    (is (= 6440 (part-1/sum-winnings hands)))))

(deftest returns-bid-from-single-hand
  (are [hands bid]
       (= bid (part-1/sum-winnings hands))
       "AAAAA 1" 1
       "AAAAA 2" 2))

(deftest multiplies-bid-by-rank
  (let [hands "AAAAA 1\nAAAAK 1"]
    (is (= 3 (part-1/sum-winnings hands)))))

(deftest five-of-a-kind-is-stronger-than-four-of-a-kind
  (let [hands "AAAAA 2\nAAAAK 1"]
    (is (= 5 (part-1/sum-winnings hands)))))

(deftest four-of-a-kind-is-stronger-than-full-house
  (let [hands "AAAAK 2\nAAAKK 1"]
    (is (= 5 (part-1/sum-winnings hands)))))

(deftest full-house-is-stronger-than-three-of-a-kind
  (let [hands "AAAKK 2\nAAAKQ 1"]
    (is (= 5 (part-1/sum-winnings hands)))))

(deftest three-of-a-kind-is-stronger-than-two-pairs
  (let [hands "AAAKQ 2\nAAKKQ 1"]
    (is (= 5 (part-1/sum-winnings hands)))))

(deftest two-pairs-are-stronger-than-one-pair
  (let [hands "AAKKQ 2\nAAKQJ 1"]
    (is (= 5 (part-1/sum-winnings hands)))))

(deftest one-pair-is-stronger-than-high-card
  (let [hands "AAKQJ 2\nAKQJT 1"]
    (is (= 5 (part-1/sum-winnings hands)))))