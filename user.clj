(ns user
  (:require [nextjournal.clerk :as clerk]
            [babashka.fs :as fs]))

(clerk/serve! {:browse? true})

(def paths
  (sort (fs/glob "exercises" "*.clj")))

(comment
  ;; start with file watcher for these sub-directory paths
  (clerk/serve! {:watch-paths ["exercises"] :browse? true})

  (clerk/clear-cache!)

  ;; or call `clerk/show!` explicitly
  (clerk/show! "README.md")
  (clerk/show! "exercises/ex_1_exploration.clj")
  (clerk/show! 'nextjournal.clerk.index)
  (clerk/show! 'nextjournal.clerk.tap)

  ;; produce a static app
  (clerk/build! {:paths paths :browse true})

  )
