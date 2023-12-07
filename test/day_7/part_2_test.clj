(ns day-7.part-2-test
  (:require [clojure.string :as string]
            [clojure.test :refer [deftest is are]]
            [day-7.part-2 :as part-2]))

(deftest returns-5905-for-example-input
  (let [hands (string/join "\n" ["32T3K 765"
                                 "T55J5 684"
                                 "KK677 28"
                                 "KTJJT 220"
                                 "QQQJA 483"])]

    (is (= 5905 (part-2/sum-winnings hands)))))

(deftest returns-bid-from-single-hand
  (are [hands bid]
       (= bid (part-2/sum-winnings hands))
       "AAAAA 1" 1
       "AAAAA 2" 2))

(deftest multiplies-bid-by-rank
  (let [hands "AAAAA 1\nAAAAK 1"]
    (is (= 3 (part-2/sum-winnings hands)))))

(deftest five-of-a-kind-is-stronger-than-four-of-a-kind
  (let [hands "AAAAA 2\nAAAAK 1"]
    (is (= 5 (part-2/sum-winnings hands)))))

(deftest four-of-a-kind-is-stronger-than-full-house
  (let [hands "AAAAK 2\nAAAKK 1"]
    (is (= 5 (part-2/sum-winnings hands)))))

(deftest full-house-is-stronger-than-three-of-a-kind
  (let [hands "AAAKK 2\nAAAKQ 1"]
    (is (= 5 (part-2/sum-winnings hands)))))

(deftest three-of-a-kind-is-stronger-than-two-pairs
  (let [hands "AAAKQ 2\nAAKKQ 1"]
    (is (= 5 (part-2/sum-winnings hands)))))

(deftest two-pairs-are-stronger-than-one-pair
  (let [hands "AAKKQ 2\nAAKQT 1"]
    (is (= 5 (part-2/sum-winnings hands)))))

(deftest one-pair-is-stronger-than-high-card
  (let [hands "AAKQT 2\nAKQT9 1"]
    (is (= 5 (part-2/sum-winnings hands)))))

(deftest five-of-a-kind-without-joker-is-stronger-than-four-of-a-kind-with-joker
  (let [hands "22222 2\n2222J 1"]
    (is (= 5 (part-2/sum-winnings hands)))))

(deftest four-of-a-kind-with-joker-is-stronger-than-four-of-a-kind-without-joker
  (let [hands "AAAAJ 2\nAAAAQ 1"]
    (is (= 5 (part-2/sum-winnings hands)))))

(deftest full-house-with-jokers-is-stronger-than-full-house-without-jokers
  (let [hands "AAAJJ 2\nAAAKK 1"]
    (is (= 5 (part-2/sum-winnings hands)))))

(deftest three-of-a-kind-with-one-joker-is-stronger-than-three-of-a-kind-without
  (let [hands "AAAKJ 2\nAAAKQ 1"]
    (is (= 5 (part-2/sum-winnings hands)))))

(deftest three-of-a-kind-with-two-joker-is-stronger-than-four-of-a-kind
  (let [hands "AAAJJ 2\nAAAAQ 1"]
    (is (= 5 (part-2/sum-winnings hands)))))

(deftest two-pairs-with-one-joker-are-stronger-than-two-pairs-without-joker
  (let [hands "AAKKJ 2\nAAKKQ 1"]
    (is (= 5 (part-2/sum-winnings hands)))))

(deftest two-pairs-with-one-joker-are-stronger-than-three-of-a-kind
  (let [hands "AAKKJ 2\nAAAKQ 1"]
    (is (= 5 (part-2/sum-winnings hands)))))

(deftest two-pairs-with-two-joker-are-stronger-than-three-of-a-kind
  (let [hands "AAJJK 2\nAAAKQ 1"]
    (is (= 5 (part-2/sum-winnings hands)))))

(deftest one-pair-with-two-joker-are-stronger-than-three-of-a-kind
  (let [hands "AAJJK 2\nAAAKQ 1"]
    (is (= 5 (part-2/sum-winnings hands)))))

(deftest one-pair-with-three-joker-is-stronger-than-four-of-a-kind
  (let [hands "AAJJJ 2\nAAAAQ 1"]
    (is (= 5 (part-2/sum-winnings hands)))))

(deftest pair-with-joker-is-stronger-than-a-pair
  (let [hands "KKJQT 2\nAAKQT 1"]
    (is (= 5 (part-2/sum-winnings hands)))))

(deftest high-card-with-one-joker-is-stronger-than-high-card
  (let [hands "2345J 2\n23456 1"]
    (is (= 5 (part-2/sum-winnings hands)))))

(deftest high-card-with-two-joker-is-stronger-than-two-pairs
  (let [hands "234JJ 2\n22446 1"]
    (is (= 5 (part-2/sum-winnings hands)))))

(deftest high-card-with-three-joker-is-stronger-than-full-house
  (let [hands "23JJJ 2\n22444 1"]
    (is (= 5 (part-2/sum-winnings hands)))))

(deftest high-card-with-four-joker-is-stronger-than-four-of-a-kind
  (let [hands "2JJJJ 2\n24444 1"]
    (is (= 5 (part-2/sum-winnings hands)))))

(deftest five-jokers-are-stronger-than-four-of-a-kind
  (let [hands "JJJJJ 2\n24444 1"]
    (is (= 5 (part-2/sum-winnings hands)))))
