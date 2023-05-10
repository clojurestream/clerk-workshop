;; # Custom Viewer
(ns exercises.ex-4-custom-viewer
  (:require [nextjournal.clerk :as clerk]
            [nextjournal.clerk.viewer :as viewer]
            [tablecloth.api :as tc]))

;; Here's a tablecloth `dataset` and a skeleton for a
;; `dataset-viewer`. Modify the `:transform-fn` of the viewer so Clerk
;; shows it as a `clerk/table`.

;; You might want to use a table format that preserves the column
;; ordering.

(def dataset
  (-> "https://raw.githubusercontent.com/techascent/tech.ml.dataset/master/test/data/stocks.csv"
      (tc/dataset {:key-fn keyword})
      (tc/group-by (fn [row]
                     {:symbol (:symbol row)
                      :year (tech.v3.datatype.datetime/long-temporal-field :years (:date row))}))
      (tc/aggregate #(tech.v3.datatype.functional/mean (% :price)))))

(def dataset-viewer
  {:transform-fn (clerk/update-val (fn [dataset]
                                     ,,,
                                     dataset))})

(clerk/with-viewer dataset-viewer
  dataset)

;; Extend the viewer with a `:pred-fn` that automatically selects it
;; based on the `tc/dataset?` predicate. Validate that this works
;; correctly using `viewer/viewer-for`.

;; Finally add the viewer to the namespace using `clerk/add-viewers!`
;; so the dataset is rendered as a table, without requiring an
;; explicit `clerk/with-viewer` call.

(clerk/add-viewers! [,,,])

dataset
