;; # Sync
(ns ex-9-sync
  (:require [nextjournal.clerk :as clerk]
            [nextjournal.clerk.experimental :as cx]))

;; A `^{::clerk/sync true}` metadata annotation enables two-way
;; bindings between the JVM and Clerk's viewer in the browser.

(defonce !text
  (atom "Hello"))

(defonce !repeat
  (atom 3))

(repeat @!repeat @!text)

;; Start by evaluating forms in the following `comment` form and show
;; the notebook again to see it update. Then add a `^{::clerk/sync
;; true}` metadata annotation and try again.

(comment
  (reset! !text "Hello, again")
  (reset! !text "Hello World")
  (reset! !text "Hello")

  (swap! !repeat inc)
  (swap! !repeat dec)
  )

;; Finally, add `::clerk/viewer` with `cx/text-input` and `cx/slider`
;; values to the metadata annotation. Play with the value propagation
;; from both the browser and the REPL.

