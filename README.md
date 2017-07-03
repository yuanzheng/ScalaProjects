# Functional Programming in Scala Specialization
This repository is only for assignments of Scala courses on Coursera taught by Martin Odersky.

Solutions are on each specific branch.

## Functional Programming Principles in Scala
1. Recursion
2. Functional Sets
3. Object-Orientd Sets
4. Huffman Coding
5. Anagrams

## Functional Program Design in Scala
1. Bloxorz
2. Quickcheck

## Parallel programming

## Big Data Analysis with Scala and Spark
1. StackOverflow



plugins.sbt
addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse-plugin" % "3.0.0")

resolvers += Classpaths.sbtPluginReleases

addSbtPlugin("org.scoverage" % "sbt-scoverage" % "1.0.4")

addSbtPlugin("org.scalastyle" %% "scalastyle-sbt-plugin" % "0.6.0")

resolvers += "sonatype-releases" at "https://oss.sonatype.org/content/repositories/releases/"


