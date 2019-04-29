/**
  * (C) Copyright IBM Corp. 2015 - 2017
  *
  * Licensed under the Apache License, Version 2.0 (the "License");
  * you may not use this file except in compliance with the License.
  * You may obtain a copy of the License at
  *
  *     http://www.apache.org/licenses/LICENSE-2.0
  *
  * Unless required by applicable law or agreed to in writing, software
  * distributed under the License is distributed on an "AS IS" BASIS,
  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  * See the License for the specific language governing permissions and
  * limitations under the License.
  *
  */

package com.ibm.sparktc.sparkbench

import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.functions.{col, count, lit, mean, round}

object AggregateResults {

  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder.getOrCreate()

    import spark.implicits._
    val inputPath = args.head

    val df = spark.read.parquet(inputPath).withColumn("total_runtime (seconds)", $"total_runtime" / 1000000000)
    val grouped = df.groupBy("name", "`spark.executor.cores`", "`spark.executor.instances`", "`spark.executor.memory`")

    grouped.agg(round(mean(col("total_runtime (seconds)")), 2).alias("average seconds"), count(col("total_runtime")).alias("number of runs"))
      .orderBy("average seconds")
      .show

  }

}
