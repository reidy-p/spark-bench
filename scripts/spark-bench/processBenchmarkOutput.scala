val df = spark.read.parquet("/tmp/benchmarkOutput/full.parquet")
df.count
df.select($"total_runtime" / 1000000000).show
df.groupBy("`spark.executor.cores`", "`spark.executor.instances`").agg(round(mean(col("total_runtime") / 1000000000), 2).alias("average seconds"), count(col("total_runtime")).alias("number of runs")).orderBy("average seconds").show(100)
