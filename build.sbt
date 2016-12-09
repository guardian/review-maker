organization := "com.gu"
description := "Review Maker"
scalaVersion := "2.11.8"
name := "review-maker"
scalacOptions ++= Seq("-feature", "-deprecation", "-unchecked", "-target:jvm-1.8", "-Xfatal-warnings")
scalacOptions in doc in Compile := Nil

lazy val reviewMaker = (project in file(".")).enablePlugins(PlayScala)

resolvers += Resolver.sonatypeRepo("releases")
resolvers += "Bintary JCenter" at "http://jcenter.bintray.com"

val AtomLibVersion = "0.1.6"
val CapiModelsVersion = "10.17"

libraryDependencies ++= Seq(
  "com.gu" %% "content-atom-model" % "2.4.26",
  "com.gu" % "content-api-models-scala" % CapiModelsVersion,
  "com.gu" % "content-api-models-json" % CapiModelsVersion,
  "play-circe" %% "play-circe" % "2.5-0.5.2",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.1.0",
  "com.gu" %% "scanamo-scrooge" % "0.1.3",
  "org.typelevel" %% "cats-core" % "0.7.0", // for interacting with scanamo
  "com.gu" %% "atom-publisher-lib" % AtomLibVersion,
  "com.gu" %% "atom-manager-play"  % AtomLibVersion
//  "com.gu" %% "atom-publisher-lib" % atomLibVersion % "test" classifier "tests",
//  "com.gu" %% "atom-manager-play"  % atomLibVersion % "test" classifier "tests"
)

initialize := {
  val _ = initialize.value
  assert(sys.props("java.specification.version") == "1.8",
    "Java 8 is required for this project.")
}
