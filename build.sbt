organization := "com.gu"
description := "Review Maker"
scalaVersion := "2.11.8"
name := "review-maker"
scalacOptions ++= Seq("-feature", "-deprecation", "-unchecked", "-target:jvm-1.8", "-Xfatal-warnings")
scalacOptions in doc in Compile := Nil

lazy val reviewMaker = (project in file(".")).enablePlugins(PlayScala)

resolvers += "Sonatype releases" at "https://oss.sonatype.org/content/repositories/releases"

libraryDependencies ++= Seq(
  "com.typesafe.scala-logging" %% "scala-logging" % "3.1.0",
  "com.gu" %% "atom-publisher-lib" % "0.1.3",
  "com.gu" %% "atom-manager-play"  % "0.1.3"
//  "com.gu" %% "atom-publisher-lib" % "0.1.3" % "test" classifier "tests",
//  "com.gu" %% "atom-manager-play"  % "0.1.3" % "test" classifier "tests"
)

initialize := {
  val _ = initialize.value
  assert(sys.props("java.specification.version") == "1.8",
    "Java 8 is required for this project.")
}
