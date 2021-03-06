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

package com.ibm.sparktc.sparkbench.cli

import org.slf4j.{Logger, LoggerFactory}
import com.ibm.sparktc.sparkbench.workload.MultipleSuiteKickoff

object CLIKickoff {

  def main(args: Array[String]): Unit = {
    val log: Logger = LoggerFactory.getLogger(this.getClass)
    log.info(s"args received: ${args.mkString(", ")}")
    if(args.isEmpty) throw new IllegalArgumentException("CLIKickoff received no arguments")
    val oneStr = args.mkString(" ")
    val worksuites = Configurator(oneStr)
    MultipleSuiteKickoff.run(worksuites)
  }

}
