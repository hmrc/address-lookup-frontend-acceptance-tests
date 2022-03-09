import sbt._

object Dependencies {

  val test = Seq(
    "org.scalatest"           %%  "scalatest"         % "3.2.9"       % Test,
    "org.scalatestplus"       %%  "selenium-3-141"    % "3.2.9.0"     % Test,
    "com.vladsch.flexmark"    %   "flexmark-all"      % "0.36.8"      % Test,
    "uk.gov.hmrc"             %%  "webdriver-factory" % "0.+"         % Test,
    "com.google.guava"        %   "guava"             % "30.1.1-jre"  % Test,
    "com.typesafe.play"       %%  "play-json"         % "2.9.2"       % Test,
    "com.typesafe"            %   "config"            % "1.4.2"       % Test,
    "org.mock-server"         %   "mockserver-netty"  % "5.11.2"      % Test,
    "org.assertj"             %   "assertj-core"      % "3.22.0"      % Test,
    "org.tpolecat"            %%  "doobie-core"       % "0.7.1"       % Test,
    "org.tpolecat"            %%  "doobie-postgres"   % "0.7.1"       % Test,
    "org.tpolecat"            %%  "doobie-scalatest"  % "0.7.1"       % Test,
    "uk.gov.hmrc"             %%  "address-lookup"    % "4.+"         % Test,
  )
}
