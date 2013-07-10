
;*CLJSBUILD-REMOVE*; (ns catbook.crossover.html
;*CLJSBUILD-REMOVE*;    (:require-macros [hiccups.core    :as h  ])
;*CLJSBUILD-REMOVE*;    (:require        [hiccups.runtime :as hr ]) )

;*CLJSBUILD-REMOVE*; (comment
                     (ns catbook.crossover.html
                        (:require        [hiccup.def      :as h  ]
                                         [hiccup.core     :as hr ]) )
;*CLJSBUILD-REMOVE*; )


(defn img [src] [:img {:src src}])

(defn avatar [src] [:a.avatar {:href "/me"}
                          [:img {:src src}]])

(def friend img)

(defn friends [& args] [:div.friends args])

(defn sidebar [a f] [:div.sidebar a [:hr] f])
