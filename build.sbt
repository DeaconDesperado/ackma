import AssemblyKeys._

assemblySettings

organization := "ackma"

name := "default"

version := "0.1-SNAPSHOT"

resolvers ++= Seq(
  "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/",
  "Spray Repository" at "http://repo.spray.io",
  "Nightly Spray Repository" at "http://nightlies.spray.io"
)

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.2.1",
  "io.spray" % "spray-can" % "1.2-20130710",
  "io.spray" % "spray-routing" % "1.2-20130710",
  "io.spray" % "spray-util" % "1.2-20130710",
  "io.spray" % "spray-http" % "1.2-20130710",
  "io.spray" %%  "spray-json" % "1.2.5"
)

seq(Revolver.settings: _*)

Revolver.reStartArgs := Seq("com.ackma.Service")

mainClass in Revolver.reStart := Some("akka.Main")

