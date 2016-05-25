/*
 * Copyright 2016 President and Fellows of Harvard College
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

package edu.harvard.gis.hhypermap.bopws

import io.dropwizard.Application
import io.dropwizard.setup.Environment

/**
 * Dropwizard main entry for our web-service
 */
class DwApplication : Application<DwConfiguration>() {

  override fun run(configuration: DwConfiguration, environment: Environment) {
    val solrClient = configuration.newSolrClient()
    environment.healthChecks().register(DwHealthCheck.NAME, DwHealthCheck(solrClient))
    environment.jersey().register(SearchWebService(solrClient))
  }

}