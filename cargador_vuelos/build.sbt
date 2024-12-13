import sbt.Keys.libraryDependencies
//import sbtassembly.AssemblyPlugin.defaultShellScript
//import scala.collection.Seq

ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion := "2.13.15"
//ThisBuild / assemblyPrependShellScript := Some(defaultShellScript)

lazy val root = (project in file("."))
  .settings(
    name := "cargador_vuelos",// TODO: establece el nombre del proyecto. Tiene que ser el mismo que el nombre que le has dado al proyecto en IntelliJ
    // TODO: define la clase principal del proyecto para la etapa `run` de `Compile`
    // TODO: define la clase principal del proyecto para la etapa `packageBin` de `Compile`
    // TODO: define la clase principal del proyecto para el ensamblado de `assembly`
    // TODO: define `flights_loader.jar` como el nombre del jar que se genera en la etapa assembly
    libraryDependencies ++= Seq(
      // TODO añade la dependencia de la librería de configuración de Typesafe
      //"com.typesafe.akka" %% "akka-http-spray-json" % "10.5.2"
      "org.scalatest" %% "scalatest" % "3.2.19" % Test,
      //"org.scala-lang" %% "toolkit-test" % "0.1.7" % Test
    )
  )

