/**
  * Created by ysong on 3/16/17.
  */
package example

import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf

object WordCount {
  def main(args: Array[String]) {
    val logFile = "/Users/master/OneDrive/workspace/wikipedia.dat" // Should be some file on your system
    val conf = new SparkConf().setAppName("Simple Word Count Application")
    val sc = new SparkContext(conf)
    val logData = sc.textFile(logFile, 4).persist()
    val numAs = logData.filter(line => line.contains("scala")).count()
    val numBs = logData.filter(line => line.contains("java")).count()
    println(s"Lines with Scala: $numAs, Lines with Java: $numBs")
    sc.stop()
  }
}
