/*
 * Copyright 2023 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.integration.spec

import org.scalatest._
import org.scalatest.featurespec.AnyFeatureSpec
import org.scalatest.matchers.should.Matchers
import uk.gov.hmrc.integration.utils.{BrowserDriver, CommonAssertions, JourneyBuilder, PostgresDB}

import java.nio.file.Paths

trait BaseSpec
    extends AnyFeatureSpec
    with PostgresDB
    with GivenWhenThen
    with BeforeAndAfterAll
    with BeforeAndAfterEach
    with BrowserDriver
    with JourneyBuilder
    with CommonAssertions
    with Matchers {

  override def beforeAll(): Unit = {
    super.beforeAll()
    tx //Initialise DB for tests
    sys.addShutdownHook {
      webDriver.quit()
    }
  }

  override def afterEach: Unit =
    webDriver.manage().deleteAllCookies()

  override def afterAll(): Unit =
    webDriver.quit()

  override def withFixture(test: NoArgTest): Outcome = {
    val fixture = super.withFixture(test)
    if (!fixture.isSucceeded) {
      val screenshotDirectory = Paths.get("./target/screenshots").toAbsolutePath.toString
      val screenshotFilename  = test.name.replaceAll("\\s", "_").replaceAll("/", "")
      setCaptureDir(screenshotDirectory)
      capture to screenshotFilename
      println(s"Saved screenshot for failing test to '$screenshotDirectory/$screenshotFilename'")
    }
    fixture
  }
}
