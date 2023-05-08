;; # Caching
(ns ex-5-caching
  (:require [nextjournal.clerk :as clerk]))

;; Note how the following notebook will show the same value
;; everytime. Opt out of the caching for the side-effecting expression
;; for it to show an updated order every time.

;; _Hint_: only one expression needs to be annotated.

(defn make-initial-value []
  {:order (shuffle (range 10))})

(defonce !state
  (atom (make-initial-value)))

^::clerk/no-cache
(reset! !state (make-initial-value))

(do
  (prn :running-now)
  (mapv inc (:order @!state)))
