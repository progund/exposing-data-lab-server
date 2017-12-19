#!/bin/bash

echo 'select product.name, product.price, product.alcohol, product.volume, product.nr, productgroup.name, product.type from product join productgroup on product.productgroupid=productgroup.id limit 100;' | sqlite3 ../webroot/WEB-INF/db/bolaget.db | tr '|' ','
