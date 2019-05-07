#!/usr/bin/env bash

hdfs dfs -test -d /tmp/benchmarkOutput/
if [ $? -eq 0 ]; then
    hdfs dfs -rm -r /tmp/benchmarkOutput/
fi
./spark-bench-custom/bin/spark-bench.sh spark-pi-example.conf
./spark-bench-custom/bin/spark-bench.sh spark-pi-dynamic.conf
