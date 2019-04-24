hdfs dfs -test -d /tmp/benchmarkOutput/
if [ $? -eq 0 ]; then
    hdfs dfs -rm -r /tmp/benchmarkOutput/
fi
# Use the standard spark-bench library
if [ "$1" = "standard" ]; then
  echo "Using standard version of spark-bench library"
  if [ ! -d "./spark-bench_2.3.0_0.4.0-RELEASE" ]; then
      wget https://github.com/CODAIT/spark-bench/releases/download/v99/spark-bench_2.3.0_0.4.0-RELEASE_99.tgz
      tar -xvzf spark-bench_2.3.0_0.4.0-RELEASE_99.tgz
      rm spark-bench_2.3.0_0.4.0-RELEASE_99.tgz
  fi
  ./spark-bench_2.3.0_0.4.0-RELEASE/bin/spark-bench.sh $2
# Use my custom version of spark-bench
else
  echo "Using custom version of spark-bench library"
  ./spark-bench-custom/bin/spark-bench.sh $2      
fi
