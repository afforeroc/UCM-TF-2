import sbt.Keys.libraryDependencies
import sbtassembly.AssemblyPlugin.defaultShellScript

import scala.collection.Seq

ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion := "2.13.15"
ThisBuild / assemblyPrependShellScript := Some(defaultShellScript)

lazy val root = (project in file("."))
  .settings(
    name := "cargador_vuelos",
    Compile / run / mainClass := Some("org.ntic.flights.FlightsLoaderApp"),
    packageBin / mainClass := Some("org.ntic.flights.FlightsLoaderApp"),
    assembly / mainClass := Some("org.ntic.flights.FlightsLoaderApp"),
    assembly / assemblyJarName := "flights_loader.jar",
    libraryDependencies ++= Seq(
      "com.typesafe" % "config" % "1.4.3",
      "com.typesafe.akka" %% "akka-http-spray-json" % "10.5.3",
      "org.scalatest" %% "scalatest" % "3.2.19" % Test,
      "org.scala-lang" %% "toolkit-test" % "0.6.0" % Test
    )
  )
