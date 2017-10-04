#!/usr/bin/env bash

#itemName@itemColor@itemCategory@itemSize@actor

set -e

echo "Building jar - please wait..."
mvn clean compile assembly:single >> /dev/null

echo "Running jar..."
java -jar target/RetailSurfer-1.0-SNAPSHOT-jar-with-dependencies.jar\
    "supreme"\
    "2017-10-05 10:59:00"\
    "Supreme®/Stone Island® Hooded Sweatshirt@black@sweatshirts@none@naveed"\
    "Supreme®/Stone Island® S/S Top@black@shirts@none@naveed"
