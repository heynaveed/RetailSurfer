#!/usr/bin/env bash

#itemName@itemColor@itemCategory@itemSize@actor

set -e

echo "Building jar - please wait..."
mvn clean compile assembly:single >> /dev/null

echo "Running jar..."
java -jar target/RetailSurfer-1.0-SNAPSHOT-jar-with-dependencies.jar\
    "supreme"\
    "2017-10-19 10:59:50"\
    "New Era® Reflective Logo Headband@black@accessories@none@naveed"\
    "Arabic Logo Hooded Sweatshirt@black@sweatshirts@none@naveed"
