ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.15"

lazy val root = (project in file("."))
  .settings(
    name := "Consumer"
  )

val playVersion       = "2.10.1"
val kafkaVersion      = "3.9.0"
val catsEffectVersion = "3.5.7"
val fs2KafkaVersion   = "3.6.0"
val doobieVersion     = "1.0.0-RC6"
val jacksonVersion    = "2.18.2"

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play-json" % playVersion,
  "org.apache.kafka" % "kafka-clients" % kafkaVersion exclude ("org.apache.logging.log4j", "log4j-slf4j-impl"),
  "org.postgresql" % "postgresql" % "42.7.4",
  "org.slf4j" % "slf4j-api" % "2.0.16",
  "ch.qos.logback" % "logback-classic" % "1.5.12",
  "com.typesafe" % "config" % "1.4.3",
  "org.typelevel" %% "cats-effect" % catsEffectVersion,
  "com.github.fd4s" %% "fs2-kafka" % fs2KafkaVersion,
  "org.tpolecat" %% "doobie-core" % doobieVersion,
  "org.tpolecat" %% "doobie-hikari" % doobieVersion,
  "com.fasterxml.jackson.core" % "jackson-core" % jacksonVersion,
  "com.fasterxml.jackson.core" % "jackson-databind" % jacksonVersion,
  "com.fasterxml.jackson.module" %% "jackson-module-scala" % jacksonVersion
) ++ Seq(
  "org.scalatest" %% "scalatest" % "3.2.19" % Test,
  "org.mockito" %% "mockito-scala" % "1.17.37" % Test,
  "com.dimafeng" %% "testcontainers-scala-scalatest" % "0.41.4" % Test
)

scalacOptions += "-Xlint"

assembly / assemblyMergeStrategy := {
  case PathList("META-INF", "FastDoubleParser-NOTICE")              => MergeStrategy.first
  case PathList("org", "slf4j", "impl", "StaticLoggerBinder.class") => MergeStrategy.first
  case PathList("org", "slf4j", "impl", "StaticMDCBinder.class")    => MergeStrategy.first
  case PathList("org", "slf4j", "impl", "StaticMarkerBinder.class") => MergeStrategy.first
  case PathList("mime.types")                                       => MergeStrategy.first
  case PathList("META-INF", "io.netty.versions.properties")         => MergeStrategy.first
  case PathList("module-info.class")                                => MergeStrategy.discard
  case x if x.endsWith("/module-info.class")                        => MergeStrategy.discard
  case PathList("org", "apache", "log4j", _*)                       => MergeStrategy.first
  case PathList("org", "apache", "yetus", _*)                       => MergeStrategy.first
  case PathList("javax", "activation", _*)                          => MergeStrategy.first
  case PathList("io", "netty", "channel", "epoll", _*)              => MergeStrategy.first
  case x                                                            =>
    val oldStrategy = (ThisBuild / assemblyMergeStrategy).value
    oldStrategy(x)
}
