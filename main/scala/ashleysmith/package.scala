package ashleysmith {

  import org.apache.spark.sql.SparkSession

  object cleanlog {
    def main(args: Array[String]): Unit = {

      // Start a simple Spark Session
      val spark = SparkSession
        .builder()
        .master("local")
        .appName("cybersecuritylog")
        .getOrCreate()

      //Read in log files
      import org.apache.spark.sql.functions._

      val df = spark.read.text("/Users/ashleysmith/Downloads/tutorialdata/www1/secure.log")

      df.printSchema()

      //Clean up columns
      val splitDF = df.withColumn("dayofweek", split(col("value"), " ").getItem(0))
        .withColumn("month", split(col("value"), " ").getItem(1))
        .withColumn("day", split(col("value"), " ").getItem(2))
        .withColumn("year", split(col("value"), " ").getItem(3))
        .withColumn("time", split(col("value"), " ").getItem(4))
        .withColumn("r1", split(col("value"), " ").getItem(7))
        .withColumn("r2", split(col("value"), " ").getItem(8))
        .withColumn("f1", split(col("value"), " ").getItem(10))
        .withColumn("f2", split(col("value"), " ").getItem(11))
        .withColumn("f3", split(col("value"), " ").getItem(12))
        .drop("value")


      val mergeDF = splitDF.withColumn("date", concat_ws("-", (col("month")), (col("day")), (col("year")), (col("time")))).drop("dayofweek").drop("month").drop("day").drop("year").drop("time")
        .withColumn("reason", (concat_ws(" ", (col("r1")), (col("r2"))))).drop("r1").drop("r2")
        .withColumn("who", (concat_ws(" ", (col("f1")), (col("f2")), (col("f3"))))).drop("f1").drop("f2").drop("f3")

      val finalDF = mergeDF.filter(col("reason") rlike "password")

      //Write to csv
      finalDF.write.option("header", "true")
        .csv("/Users/ashleysmith/Downloads/passwords")
    }
  }

}
