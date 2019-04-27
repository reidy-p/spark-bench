val df = spark.read.parquet("/tmp/benchmarkOutput/full.parquet").withColumn("total_runtime (seconds)", $"total_runtime" / 1000000000)
df.count
df.printSchema
val grouped = df.groupBy("name", "`spark.executor.cores`", "`spark.executor.instances`", "`spark.executor.memory`")

(grouped.agg(round(mean(col("total_runtime (seconds)")), 2).alias("average seconds"), count(col("total_runtime")).alias("number of runs"), round(mean("pi_approximate"), 2).alias("average pi_approximate"))
  .orderBy("average seconds")
  .show)
