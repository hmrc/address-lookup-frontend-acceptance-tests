lazy val testSuite = (project in file("."))
  .enablePlugins(SbtAutoBuildPlugin)
  .disablePlugins(JUnitXmlReportPlugin) //Required to prevent https://github.com/scalatest/scalatest/issues/1427
  .settings(
    organization := "uk.gov.hmrc",
    name := "address-lookup-frontend-acceptance-tests",
    version := "0.1.0",
    scalaVersion := "2.12.15",
    console / initialCommands := "import uk.gov.hmrc._",
    Test / parallelExecution := false,
    libraryDependencies ++= Dependencies.test,
    Test / testOptions := Seq(
      Tests.Argument(TestFrameworks.ScalaTest, "-u", "target/test-reports"),
      Tests.Argument(TestFrameworks.ScalaTest, "-h", "target/test-reports/html-report"),
      Tests.Argument("-oD")
    )
  )
