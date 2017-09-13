#!/usr/bin/env bash

set -e

echo "Building jar - please wait..."
mvn clean compile assembly:single >> /dev/null

echo "Running jar..."
java -jar target/RetailResale-1.0-SNAPSHOT-jar-with-dependencies.jar\
    "supreme"\
    "2017-09-13 10:55:00"\
    "Supreme®/Schott® Chief Tassel Perfecto@black@jackets@none@test"
