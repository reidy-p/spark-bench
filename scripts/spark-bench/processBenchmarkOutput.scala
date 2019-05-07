val df = spark.read.parquet("/tmp/benchmarkOutput/full.parquet").withColumn("total_runtime (seconds)", $"total_runtime" / 1000000000)
df.count
df.printSchema
val grouped = df.groupBy("name", "`spark.executor.instances`", "`spark.dynamicAllocation.enabled`", "`spark.executor.cores`", "`spark.executor.memory`", "slices")

(grouped.agg(round(mean(col("total_runtime (seconds)")), 2).alias("avg seconds"), count(col("total_runtime")).alias("number of runs"), round(mean("pi_approximate"), 2).alias("avg pi_approximate"))
  .orderBy("avg seconds")
  .show)
