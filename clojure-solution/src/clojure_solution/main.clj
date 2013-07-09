(ns clojure-solution.main
  (:require [clojurewerkz.cassaforte.client :as client])
  (:use clojurewerkz.cassaforte.cql
        clojurewerkz.cassaforte.query))

(def host "127.0.0.1")

(defn -main []

    ; Stateful
    (client/connect! [host] :keyspace :fookeyspace)

    ; Pure
    (def cluster (client/build-cluster {:contact-points [host]}))
    (def session (client/connect cluster :fookeyspace))

    ; Create table for testing
    (if (not (describe-table :fookeyspace "foo") )
        (create-table "foo"
            (column-definitions {:foo_id      :int
                                 :fooname     :varchar
                                 :primary-key [:foo_id]})))

    ; Inserts
    (insert :foo {:foo_id 123 :fooname "Munich"})
    (insert :foo {:foo_id 456 :fooname "Sam"})

    ; Selects
    (doseq [e [ (select :foo)
                (select :foo (where :foo_id 123))
                (select :foo (where :foo_id [:in [123]]))
                (client/execute ["select * from foo"] session)
                (describe-table :fookeyspace "foo")
                (describe-table :fookeyspace "bar")
              ]]
            (prn e)))
