#!/usr/bin/env bash

kill -9 $(ps -ef | grep "wiremock" | awk '{print $2}')
sudo service nginx stop


