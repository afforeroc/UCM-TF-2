package org.ntic.flights.data
import com.typesafe.config.{Config, ConfigFactory}
import scala.jdk.CollectionConverters._

object FlightsLoaderConfig {
  val config: Config = ConfigFactory.load()
  val flightsLoaderConfig: Config = config.getConfig("flightsLoader")
  val filePath: String = flightsLoaderConfig.getString("filePath")
  val hasHeaders: Boolean = flightsLoaderConfig.getBoolean("hasHeaders")
  val delimiter: String = flightsLoaderConfig.getString("delimiter")
  val outputDir: String = flightsLoaderConfig.getString("outputDir")
  val headers: List[String] = flightsLoaderConfig.getStringList("headers").asScala.toList
  val headersLength: Int = headers.length
  val filteredOrigin: List[String] = flightsLoaderConfig.getStringList("filteredOrigin").asScala.toList
  val columnIndexMap: Map[String, Int] = headers.map(x => (x, headers.indexOf(x))).toMap
}
