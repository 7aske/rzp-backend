#!/usr/bin/env bash

read -r -p "tomcat user: " user
read -r -s -p "tomcat pass: " pass

ctx_path="blog"
#ip="127.0.0.1"
#port="8081"
src_war="$(pwd)/target/$ctx_path.war"
#dest_dir="/tmp/nagradica/backend/"

tomcat_ip="127.0.0.1"
tomcat_port="8181"

if [ ! -e "$src_war" ]; then
   ./scripts/build.sh
fi

#ssh "$ip" "mkdir -p $dest_dir" && \
#rsync --progress -have ssh "$src_war" "$ip:$dest_dir/$ctx_path.war"
curl -v -u "$user:$pass" "http://$tomcat_ip:$tomcat_port/manager/text/undeploy?path=/$ctx_path"
curl -v -u "$user:$pass" -T "$src_war" "http://$tomcat_ip:$tomcat_port/manager/text/deploy?path=/$ctx_path&update=true"
