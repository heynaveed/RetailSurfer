#!/usr/bin/env bash

set -e

echo "Building jar - please wait..."
mvn clean compile assembly:single >> /dev/null

echo "Running jar..."
java -jar target/RetailResale-1.0-SNAPSHOT-jar-with-dependencies.jar "supreme" "2017-09-07 10:59:40" "supreme®/quiet carry knife@none@accessories@none" "inflatable blimp@none@accessories@none" "piping track jacket@black@jackets@medium"
