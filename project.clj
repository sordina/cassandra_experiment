(defproject catbook "0.1.0-SNAPSHOT"

  :description "FIXME: write description"

  :url "http://example.com/FIXME"

  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[org.clojure/clojure     "1.5.1"]
                 [clojurewerkz/cassaforte "1.0.0-rc5"]
                 [ring                    "1.2.0-RC1"]
                 [compojure               "1.1.5"]
                 [hiccup                  "1.0.3"]
                 [hiccups                 "0.2.0"]
                 [jayq                    "2.4.0"]
                 ]

  :main catbook.main

  :plugins [ [lein-cljsbuild "0.3.0"] ]

  :cljsbuild {
    :builds [{:source-paths ["src" "crossover-cljs"]
              :compiler {:output-to     "main.js"
                         ; :target        :nodejs
                         :optimizations :whitespace
                         :pretty-print  true}}]

    :crossovers     [catbook.crossover]
    :crossover-path "crossover-cljs"
    :crossover-jar  true }
)
