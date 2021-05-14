#!/usr/bin/env bash

artifactId="$(xpath -q -e '/project/artifactId/text()' ./pom.xml)"
package="$(xpath -q -e '/project/package/text()' ./pom.xml)"

springstrap ddl/rzp.ddl \
	-d "$artifactId.$package" \
	-slawprA \
	-o ./ \
	--ignore=user,role