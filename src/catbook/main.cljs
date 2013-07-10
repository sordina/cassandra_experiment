(ns catbook.main
  (:require        [catbook.crossover.html   :as t    ]
                   [catbook.crossover.widget :as w    ]
                   [catbook.test             :as test ]
                   [jayq.core                :as j    ])
  (:require-macros [hiccups.core             :as h    ]))

(defn log [& items]
    (.log js/console (clj->js items))
    (.log js/console (h/html items))
    (j/append (j/$ :body) (h/html items)))

(def my-avatar  (t/avatar "resources/public/cat.png"))
(def my-img     (t/img    "resources/public/cat.png"))
(def my-friend  (t/friend "resources/public/cat.png"))
(def my-friends (t/friends my-friend my-friend))
(def my-sidebar (t/sidebar my-avatar my-friends))

(log my-avatar)
(log my-img)
(log my-friend)
(log my-friends)
(log my-sidebar)

; Testing stuff
(log (test/main) )

(def foo (-> (w/pure   [:div.foo])
             (w/addCSS ".foo {border: 1px solid red;}")
             (w/addJS  "console.log('testing')")))

(log (:a          foo))
(log (:css        foo))
(log (:javascript foo))
