;; # Viewer Require
(ns ex-7-viewer-require
  (:require [nextjournal.clerk :as clerk]))

;; In this exercise, we'll use the Clerk's viewer api to dynamically
;; load a JavaScript library.

;; Complete the following viewer to dynamically require the `emoji-js`
;; package so you see an emoji at the end.

;; Hint: once you loaded the package using `v/with-d3-require`, you
;; will need to instantiate the EmojiConverter and use it's
;; `.replace_colons` function.

(def emoji-viewer
  {:transform-fn clerk/mark-presented
   :render-fn '(fn [value]
                 [nextjournal.clerk.viewer/with-d3-require {:package [",,,"]}
                  (fn [EmojiConverter]
                    (.replace_colons (new ,,,) value))])})

(clerk/with-viewer emoji-viewer
  "Hallo :smile:")
