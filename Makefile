help:
	@echo "Targets:"
	@cat Makefile | grep -i '^[a-z]*:' | sed 's/^/    /' | sed 's/://'
	@echo
	@echo "Resources:"
	@echo "    http://wiki.apache.org/cassandra/GettingStarted"
	@echo "    http://www.datastax.com/docs/1.2/cql_cli/using_cql"
	@echo "    http://highlyscalable.wordpress.com/2012/03/01/nosql-data-modeling-techniques/"
	@echo "    http://wiki.apache.org/cassandra/DataModel"

cassandra:
	launchctl load /usr/local/opt/cassandra/homebrew.mxcl.cassandra.plist

cqlsh:
	cqlsh
