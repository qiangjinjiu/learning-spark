package com.oreilly.learningsparkexamples.scala

import org.apache.spark._
import org.apache.spark.rdd.RDD

object BasicAvg {
  def main(args: Array[String]) {
    val master = args.length match {
      case x: Int if x > 0 => args(0)
      case _ => "local"
    }

    val sc = new SparkContext(master, "BasicAvg", System.getenv("SPARK_HOME"))
    val input = sc.parallelize(List(1, 2, 3, 4))
    val result = basicAvg(input)
    val avg = result._1 / result._2.toFloat
    println (avg)
  }

  def basicAvg(input: RDD[Int]) = {
    input.aggregate((0, 0))(
      (acc, value) => (acc._1 + value, acc._2 + 1),
      (acc1, acc2) => (acc1._1 + acc2._1, acc1._2 + acc2._2))
  }
}
