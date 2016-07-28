name := "v26"

scalaVersion := "2.11.8"

libraryDependencies ++= {
  val akkaVersion = "2.4.8"
  val akkaPersistenceJdbcVersion = "2.6.3"
  Seq(
    "com.typesafe.akka" %% "akka-actor" % akkaVersion,
    "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
    "com.typesafe.akka" %% "akka-stream" % akkaVersion,
    "com.typesafe.akka" %% "akka-persistence" % akkaVersion,
    "com.typesafe.akka" %% "akka-persistence-query-experimental" % akkaVersion,
    "com.github.dnvriend" %% "akka-persistence-jdbc" % akkaPersistenceJdbcVersion ,
    "ch.qos.logback" % "logback-classic" % "1.1.7",
    "org.postgresql" % "postgresql" % "9.4.1208",
    "com.typesafe.akka" %% "akka-stream-testkit" % akkaVersion % Test,
    "org.scalatest" %% "scalatest" % "2.2.6" % Test
  )
}

