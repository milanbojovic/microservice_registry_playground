name := "YiluMicroservice"

version := "0.1"

scalaVersion := "2.13.0"

libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.5.23"
libraryDependencies += "com.typesafe.akka" %% "akka-stream" % "2.5.23"
libraryDependencies += "com.typesafe.akka" %% "akka-http" % "10.1.9"
libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2"
libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.1.7"
libraryDependencies += "org.jline" % "jline" % "3.1.3"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.8" % Test
