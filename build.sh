#!/bin/bash

./clean.sh

CP=".:lib/org.json.jar"
javac -cp $CP se/itu/systemet/main/ProductSearch.java
