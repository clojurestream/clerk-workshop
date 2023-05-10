(ns index
  {:nextjournal.clerk/visibility {:code :hide :result :hide}}
  (:require [nextjournal.clerk :as clerk]
            [nextjournal.clerk.parser :as parser]
            [babashka.fs :as fs]))

;; # The Plan™️

(def exercises
  (let [minutes [40 10 15 15 25 15 15 15 15 15]]
    (into []
          (map-indexed (fn [idx file] (-> (parser/parse-file {:doc? true} file)
                                          (select-keys [:title :file])
                                          (assoc :minutes (minutes idx)))))
          user/paths)))


(def schedule
  (concat [{:title "Intro" :minutes 15}]
          (subvec exercises 0 1)
          [{:title "Break" :minutes 10}]
          (subvec exercises 1 4)
          [{:title "Break" :minutes 10}]
          (subvec exercises 4 7)
          [{:title "Break" :minutes 10}]
          (subvec exercises 7)
          [{:title "Open Ended Exploration & Discussion" :minutes 15}]))

(defn minutes-span [minutes]
  [:span.text-slate-400 [:span.mx-2 "•"] minutes "min"])

{:nextjournal.clerk/visibility {:result :show}}
(clerk/html
 (into [:ul]
       (map (fn [{:keys [title file minutes]}] [:li
                                                (if file
                                                  [:a {:href (clerk/doc-url file)} title]
                                                  title)
                                                (minutes-span minutes)])
            schedule)))

(clerk/html [:div.font-medium "Total Duration" (minutes-span (reduce + (mapv :minutes schedule)))])

