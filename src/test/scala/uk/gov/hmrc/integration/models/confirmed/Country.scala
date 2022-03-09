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

package uk.gov.hmrc.integration.models.confirmed

import play.api.libs.json.{Json, OWrites, Reads}

case class Country(code: String,
                   name: String) {
}

object Country {
  implicit val reads: Reads[Country] = Json.reads[Country]
  implicit val writes: OWrites[Country] = Json.writes[Country]
}
