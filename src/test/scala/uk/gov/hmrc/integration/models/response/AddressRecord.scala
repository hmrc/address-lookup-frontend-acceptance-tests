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

package uk.gov.hmrc.integration.models.response

import play.api.libs.json.{Json, Reads, Writes}

case class AddressRecord(
  id: String,
  uprn: Long,
  parentUprn: Option[Long],
  usrn: Option[Long],
  address: Address,
  language: String = "en",
  localCustodian: Option[LocalCustodian] = None,
  location: Option[Seq[BigDecimal]] = None,
  administrativeArea: Option[String] = None,
  poBox: Option[String] = None
) {}

object AddressRecord {
  implicit val reads: Reads[AddressRecord]   = Json.reads[AddressRecord]
  implicit val writes: Writes[AddressRecord] = Json.writes[AddressRecord]
}
