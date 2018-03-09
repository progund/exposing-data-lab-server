#!/bin/bash

echo "Starting Winstone web server and servlet container..."
echo "====================================================="
echo -e "Servlet URL: \nhttp://localhost:8080/search/products/all?min_alcohol=50&max_price=200&type=Öl&name=Guinness&max_alcohol=60"
echo
echo -e "Or accessing the system as a plain web server, using an HTML form:\nhttp://localhost:8080/search.html"

java -jar winstone.jar --webroot=webroot
