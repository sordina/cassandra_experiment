
;*CLJSBUILD-REMOVE*; (ns catbook.crossover.html
;*CLJSBUILD-REMOVE*;    (:require-macros [hiccups.core    :as h  ])
;*CLJSBUILD-REMOVE*;    (:require        [hiccups.runtime :as hr ]) )

;*CLJSBUILD-REMOVE*; (comment
                     (ns catbook.crossover.html
                        (:require        [hiccup.def      :as h  ]
                                         [hiccup.core     :as hr ]) )
;*CLJSBUILD-REMOVE*; )


(h/defhtml my-template []
  [:div
    [:a {:href "https://github.com/weavejester/hiccup"}
      "Hiccup"]])
