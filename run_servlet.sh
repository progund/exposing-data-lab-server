#!/bin/bash

PATHSEP=":"
if [[ $OS == "Windows_NT" ]] || [[ $OSTYPE == "cygwin" ]]
then
    PATHSEP=";"
fi

echo -e "Servlet URL: \nhttp://localhost:8080/search/products/all?min_alcohol=50&max_price=200&type=Öl&name=Guinness&max_alcohol=60\nOr using an HTML form:\nhttp://localhost:8080/search.html"

echo "Compiling and starting servlet container..."
javac -Xlint:unchecked -cp winstone.jar${PATHSEP}webroot/WEB-INF/lib/* webroot/WEB-INF/classes/servlets/*.java && java -jar winstone.jar --webroot=webroot
