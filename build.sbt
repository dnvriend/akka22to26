name := "akka22to26"

organization := "com.github.dnvriend"

version := "1.0.0"

scalaVersion := "2.11.8"

// the akka-persistence-jdbc plugin lives here
resolvers += Resolver.jcenterRepo

// the slick-extension library (which is used by akka-persistence-jdbc) lives here
resolvers += "Typesafe Releases" at "http://repo.typesafe.com/typesafe/maven-releases/"

lazy val v22 = project

lazy val v26 = project
