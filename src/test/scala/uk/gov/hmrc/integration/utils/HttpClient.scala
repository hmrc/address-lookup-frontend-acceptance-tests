/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.integration.utils

import akka.actor.ActorSystem
import com.typesafe.config.Config
import play.api.libs.ws.DefaultBodyWritables._
import play.api.libs.ws.ahc.{AhcWSClientConfig, StandaloneAhcWSClient}
import play.api.libs.ws.{DefaultWSProxyServer, StandaloneWSRequest, WSClientConfig}

import scala.concurrent.{ExecutionContext, Future}
import scala.language.reflectiveCalls

trait HttpClient {

  implicit val actorSystem: ActorSystem = ActorSystem()
  val wsClient: StandaloneAhcWSClient = StandaloneAhcWSClient(
    AhcWSClientConfig(WSClientConfig())
  )

  implicit val ec: ExecutionContext = ExecutionContext.global

  def get(url: String, headers: (String, String)*): Future[StandaloneWSRequest#Self#Response] =
    wsClient
      .url(url)
      .withHttpHeaders(headers: _*)
      .get()

  def post(url: String, bodyAsJson: String, headers: (String, String)*): Future[StandaloneWSRequest#Self#Response] =
    wsClient
      .url(url)
      .withHttpHeaders(headers: _*)
      .post(bodyAsJson)

  def delete(url: String, headers: (String, String)*): Future[StandaloneWSRequest#Self#Response] =
    wsClient
      .url(url)
      .withHttpHeaders(headers: _*)
      .delete()
}
