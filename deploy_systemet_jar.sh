#!/bin/bash

cd systemet-api/
./build_jar.sh
[ $? = 0 ] || echo -e "\n\nThere was an error deploying the systemet.jar"
cd ..
cp systemet-api/systemet.jar webroot/WEB-INF/lib/
