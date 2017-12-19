#!/bin/bash

DEST_DIR=webroot/WEB-INF/lib/
mkdir -p ${DEST_DIR}
wget https://bitbucket.org/xerial/sqlite-jdbc/downloads/sqlite-jdbc-3.21.0.jar -O ${DEST_DIR}/sqlite-jdbc.jar
