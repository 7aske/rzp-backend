#!/usr/bin/env bash

if [ ! -e pom.xml ] || [ ! -e .git ]; then
	echo "$0: run script from repository root"
	exit 2
fi

# load user specific deploy config
[ -e "./scripts/deploy.conf" ] && . "./scripts/deploy.conf"

version="$(xpath -q -e '/project/version/text()' ./pom.xml)"
artifactId="$(xpath -q -e '/project/artifactId/text()' ./pom.xml)"
package="$(xpath -q -e '/project/package/text()' ./pom.xml)"

PROFILE="${1:-"staging"}"
WAR="${WAR:-"target/$artifactId-$version.$package"}"
STAGING_USER="${STAGING_USER:-"root"}"
STAGING_HOST="${STAGING_HOST:-"7aske.xyz"}"
STAGING_PORT="${STAGING_PORT:-"2203"}"
PROD_USER="${PROD_USER:-"root"}"
PROD_HOST="${PROD_HOST:-"digitize.rs"}"
PROD_PORT="${PROD_PORT:-"22"}"

deploy_staging() {
	echo "deploying $PROFILE -> $STAGING_HOST:$STAGING_PORT"
	mvn -P staging -D maven.test.skip=true clean install &&
		scp -P "$STAGING_PORT" "$WAR" "$STAGING_USER@$STAGING_HOST:/var/lib/tomcat9/webapps/reboot.war"
}

deploy_prod() {
	echo "deploying $PROFILE -> $PROD_HOST:$PROD_PORT"
	mvn -D maven.test.skip=true clean install &&
		scp -P "$STAGING_PORT" "$WAR" "$PROD_USER@$PROD_HOST:/var/lib/tomcat9/webapps/reboot.war"
}

usage() {
	echo "./deploy.sh [staging|prod]"
	exit 2
}

case "$PROFILE" in
	staging) deploy_staging ;;
	prod)    deploy_prod ;;
    *)       usage ;;
esac

