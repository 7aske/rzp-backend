#!/usr/bin/env bash

groupId="$(xpath -q -e '/project/groupId/text()' ./pom.xml)"
artifactId="$(xpath -q -e '/project/artifactId/text()' ./pom.xml)"

springstrap ddl/rzp.ddl \
	-d "$groupId.$artifactId" \
	-slawprA \
	-o ./ \
	--ignore=user,role