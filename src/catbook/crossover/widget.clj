(ns catbook.crossover.widget)

(defrecord Hiccle [a css javascript])

(defn ap   [l r] (Hiccle. ((:a l) (:a r))
                          (mapcat :css [l r])
                          (mapcat :javascript [l r])))
(defn pure [a]   (Hiccle. a [] []))
(defn fmap [f r] ((pure f) r))

(def <*> ap)
(def <$> fmap)

(defn addCSS [a c] (Hiccle. (:a a) (concat (:css a) c) (:javascript a) ))
(defn addJS  [a j] (Hiccle. (:a a) (:css a) (concat (:javascript a) j)))
