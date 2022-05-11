/*
 * Copyright 2022 HM Revenue & Customs
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

package uk.gov.hmrc.integration.utils

import okhttp3._
import play.api.libs.json._
import uk.gov.hmrc.integration.config.TestConfig
import uk.gov.hmrc.integration.models.confirmed.ConfirmedAddress
import uk.gov.hmrc.integration.models.init.{JourneyConfig, JourneyOptions}

import java.util.concurrent.TimeUnit.SECONDS

trait JourneyBuilder {

  val httpClient: OkHttpClient = new OkHttpClient()
    .newBuilder()
    .connectTimeout(10L, SECONDS)
    .readTimeout(10L, SECONDS)
    .build()

  val defaultConfiguration: String = JourneyConfig(2, JourneyOptions("None", ukMode = Some(true))).asJsonString()

  def initializeJourney(configuration: String = defaultConfiguration): String = {
    val request  = new Request.Builder()
      .url(s"${TestConfig.apiUrl("address-lookup-frontend")}/v2/init")
      .method(
        "POST",
        RequestBody.create(MediaType.parse("application/json"), Json.toJson(configuration).asInstanceOf[JsString].value)
      )
    val response = httpClient.newCall(request.build()).execute()
    if (response.isSuccessful) {
      //Return lookup screen URL
      response.headers.get("Location")
    } else {
      throw new IllegalStateException("Unable to initialize a new journey!")
    }
  }

  def getClientID(onRampUrl: String): String =
    HttpUrl.parse(onRampUrl).pathSegments().get(1)

  def getOffRampUrl(onRampUrl: String, continueUrl: String): String = {
    val clientId = getClientID(onRampUrl)
    s"$continueUrl?id=$clientId"
  }

  def getConfirmedAddress(id: String): ConfirmedAddress = {
    val request  = new Request.Builder()
      .url(s"${TestConfig.apiUrl("address-lookup-frontend")}/confirmed?id=$id")
      .method("GET", null)
    val response = httpClient.newCall(request.build()).execute()
    Json.parse(response.body.string()).as[ConfirmedAddress]
  }
}
