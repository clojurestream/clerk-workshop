(ns schedule
  {:nextjournal.clerk/visibility {:code :hide}}
  (:require [nextjournal.clerk :as clerk]
            [nextjournal.clerk.parser :as parser]
            [babashka.fs :as fs]))

;; # The Excercise Plan™️

(clerk/html
 (into [:ol]
       (comp (map #(parser/parse-file {:doc? true} %))
             (map (fn [{:keys [file title]}] [:li [:a {:href (clerk/doc-url file)} title]])))
       user/paths))

