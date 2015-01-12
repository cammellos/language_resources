lazy val commonSettings = Seq(
  organization := "uk.co.bocuma.polish_stemmer",
  version := "0.1.0",
  // set the Scala version used for the project
  scalaVersion := "2.11.4"
)

// define ModuleID for library dependencies
lazy val scalacheck = "org.scalacheck" %% "scalacheck" % "1.12.0"


lazy val root = (project in file(".")).
  settings(commonSettings: _*).
  settings(
    // set the name of the project
    name := "Polish stemmer",

    // set the main Scala source directory to be <base>/src
    scalaSource in Compile := baseDirectory.value / "src",

    // set the Scala test source directory to be <base>/test
    scalaSource in Test := baseDirectory.value / "test",

    // add a test dependency on ScalaCheck
    libraryDependencies += scalacheck % Test,


    // reduce the maximum number of errors shown by the Scala compiler
    maxErrors := 20,

    // increase the time between polling for file changes when using continuous execution
    pollInterval := 1000,

    // append several options to the list of options passed to the Java compiler
    javacOptions ++= Seq("-source", "1.5", "-target", "1.5"),

    // append -deprecation to the options passed to the Scala compiler
    scalacOptions += "-deprecation",

    // add a maven-style repository
    resolvers += "name" at "url",

    // add a sequence of maven-style repositories
    resolvers ++= Seq("name" at "url"),

    // define the repository to publish to
    publishTo := Some("name" at "url"),

    // set the prompt (for this build) to include the project id.
    shellPrompt in ThisBuild := { state => Project.extract(state).currentRef.project + "> " },

    // set the prompt (for the current project) to include the username
    shellPrompt := { state => System.getProperty("user.name") + "> " },

    // disable printing timing information, but still print [success]
    showTiming := false,

    // disable printing a message indicating the success or failure of running a task
    showSuccess := false,

    // change the format used for printing task completion time
    timingFormat := {
        import java.text.DateFormat
        DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT)
    },

    // disable using the Scala version in output paths and artifacts
    crossPaths := false,

    // fork a new JVM for 'run' and 'test:run'
    fork := true,

    // fork a new JVM for 'test:run', but not 'run'
    fork in Test := true,

    // add a JVM option to use when forking a JVM for 'run'
    javaOptions += "-Xmx2G",

    // only use a single thread for building
    parallelExecution := false,

    // Execute tests in the current project serially
    //   Tests from other projects may still run concurrently.
    parallelExecution in Test := false,

    // only show warnings and errors on the screen for compilations.
    //  this applies to both test:compile and compile and is Info by default
    logLevel in compile := Level.Warn,

    // only show warnings and errors on the screen for all tasks (the default is Info)
    //  individual tasks can then be more verbose using the previous setting
    logLevel := Level.Warn,

    // only store messages at info and above (the default is Debug)
    //   this is the logging level for replaying logging with 'last'
    persistLogLevel := Level.Debug,

    // publish test jar, sources, and docs
    publishArtifact in Test := true,

    // disable publishing of main docs
    publishArtifact in (Compile, packageDoc) := false,

    // change the classifier for the docs artifact
    artifactClassifier in packageDoc := Some("doc"),

    // Exclude transitive dependencies, e.g., include log4j without including logging via jdmk, jmx, or jms.
    libraryDependencies +=
      "log4j" % "log4j" % "1.2.15" excludeAll(
        ExclusionRule(organization = "com.sun.jdmk"),
        ExclusionRule(organization = "com.sun.jmx"),
        ExclusionRule(organization = "javax.jms")
      )
  )
