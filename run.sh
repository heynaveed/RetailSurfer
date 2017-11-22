#!/usr/bin/env bash

#itemName@itemColor@itemCategory@itemSize@actor

set -e

echo "Building jar - please wait..."
mvn clean compile assembly:single >> /dev/null

echo "Running jar..."
java -jar target/RetailSurfer-1.0-SNAPSHOT-jar-with-dependencies.jar\
    "supreme"\
    "2017-11-22 10:00:50"\
    "Supreme®/Independent® Fuck The Rest Crewneck@dark slate@sweatshirts@none@test"
