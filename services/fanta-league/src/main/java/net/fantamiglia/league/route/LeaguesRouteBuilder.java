/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.fantamiglia.league.route;

import net.fantamiglia.league.core.League;
import net.fantamiglia.league.core.LeagueService;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public class LeaguesRouteBuilder extends RouteBuilder {

    @Autowired
    private LeagueService service;

    @Override
    public void configure() throws Exception {

        restConfiguration().component("jetty").port(8080)
                .apiContextPath("/api-doc")
                .apiProperty("api.title", "User API").apiProperty("api.version", "1.2.3")
                // and enable CORS
                .apiProperty("cors", "true");

        rest().get("/leagues")
            .produces("application/json")
            .route()
            .bean(service, "findAllLeagues")
            .marshal().json(JsonLibrary.Jackson);



        rest().get("/leagues/{id}")
                .produces("application/json")
                .route()
                .bean(service, "findOneLeague(${header.id})")
                .choice()
                    .when().simple("${body} == null")
                        .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(404))
                    .otherwise()
                        .marshal().json(JsonLibrary.Jackson);



        rest().post("/leagues")
            .produces("application/json")
            .consumes("application/json")
            .route()
            .unmarshal().json(JsonLibrary.Jackson, League.class)
            .bean(service, "insertLeague");



        rest().put("/leagues/{id}")
            .produces("application/json")
            .consumes("application/json")
            .route()
            .unmarshal().json(JsonLibrary.Jackson, League.class)
            .bean(service, "saveLeague(${header.id}, ${body})");



        rest().delete("/leagues/{id}")
                .produces("text/plain")
                .route()
                .bean(service, "deleteLeague(${header.id})");


    }
}
