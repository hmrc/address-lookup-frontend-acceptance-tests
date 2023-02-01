import sbt._

object Dependencies {

  val test: Seq[ModuleID] = Seq(
    "org.scalatest"       %% "scalatest"         % "3.2.12"   % Test,
    "org.scalatestplus"   %% "selenium-3-141"    % "3.2.10.0" % Test,
    "com.vladsch.flexmark" % "flexmark-all"      % "0.62.2"   % Test,
    "uk.gov.hmrc"         %% "webdriver-factory" % "0.+"      % Test,
    "com.squareup.okhttp3" % "okhttp"            % "4.10.0"   % Test,
    "com.google.guava"     % "guava"             % "31.1-jre" % Test,
    "com.typesafe.play"   %% "play-json"         % "2.9.2"    % Test,
    "com.typesafe"         % "config"            % "1.4.2"    % Test,
    "org.mock-server"      % "mockserver-netty"  % "5.12.0"   % Test,
    "org.assertj"          % "assertj-core"      % "3.22.0"   % Test,
    "org.tpolecat"        %% "doobie-core"       % "0.13.4"   % Test,
    "org.tpolecat"        %% "doobie-postgres"   % "0.13.4"   % Test,
    "org.tpolecat"        %% "doobie-scalatest"  % "0.13.4"   % Test,
    "uk.gov.hmrc"         %% "address-lookup"    % "4.+"      % Test
  )
}
