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

package com.ibm.sparktc.sparkbench.workload.exercise

import com.ibm.sparktc.sparkbench.workload.{Workload, WorkloadDefaults}
import com.ibm.sparktc.sparkbench.utils.SparkFuncs._
import com.ibm.sparktc.sparkbench.utils.SaveModes
import org.apache.spark.sql.{DataFrame, Row, SparkSession}
import com.ibm.sparktc.sparkbench.utils.GeneralFunctions._
import org.apache.spark.sql.functions.{col, count, lit, mean, round}

object AggregateWorkload extends WorkloadDefaults {

  val name = "aggregate"

  def apply(m: Map[String, Any]): AggregateWorkload =
  new AggregateWorkload(input = m.get("input").map(_.asInstanceOf[String]),
    output = m.get("output").map(_.asInstanceOf[String]),
    saveMode = getOrDefault[String](m, "save-mode", SaveModes.error)
  )
}

case class AggregateWorkload(
                           input: Option[String],
                           output: Option[String],
                           saveMode: String
                           ) extends Workload {

  override def doWorkload(df: Option[DataFrame] = None, spark: SparkSession): DataFrame = {
    val fullResults = spark.read.parquet(input.get)

    val aggregatedResults = fullResults.groupBy("`spark.executor.cores`", "`spark.executor.instances`")
      .agg(round(mean(col("total_runtime") / 1000000000), 2).alias("averageSeconds"), count(col("total_runtime")).alias("numberOfRuns"))
      .orderBy("averageSeconds")

    aggregatedResults

  }

}
