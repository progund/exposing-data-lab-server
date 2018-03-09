#!/bin/bash

PATHSEP=":"
if [[ $OS == "Windows_NT" ]] || [[ $OSTYPE == "cygwin" ]]
then
    PATHSEP=";"
fi

javac -Xlint:unchecked -cp winstone.jar${PATHSEP}webroot/WEB-INF/lib/* webroot/WEB-INF/classes/servlets/*.java

