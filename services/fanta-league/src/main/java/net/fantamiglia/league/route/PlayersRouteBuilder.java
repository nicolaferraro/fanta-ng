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
import net.fantamiglia.league.core.Player;
import net.fantamiglia.league.core.PlayerId;
import net.fantamiglia.league.core.PlayerService;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public class PlayersRouteBuilder extends RouteBuilder {

    @Autowired
    private PlayerService service;

    @Override
    public void configure() throws Exception {

        //restConfiguration().component("jetty").port(8080);

        rest().get("/leagues/{leagueId}/players")
            .produces("application/json")
            .route()
            .bean(service, "findPlayersByLeague(${header.leagueId})")
            .marshal().json(JsonLibrary.Jackson);


        rest().get("/leagues/{leagueId}/players/{playerCode}")
            .produces("application/json")
            .route()
            .process(e -> e.getIn().setBody(new PlayerId(e.getIn().getHeader("leagueId", String.class), e.getIn().getHeader("playerCode", String.class))))
            .bean(service, "findOnePlayer")
            .choice()
                .when().simple("${body} == null")
                    .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(404))
                .otherwise()
                    .marshal().json(JsonLibrary.Jackson);



        rest().post("/leagues/{leagueId}/players")
            .produces("application/json")
            .consumes("application/json")
            .route()
            .unmarshal().json(JsonLibrary.Jackson, Player.class)
            .bean(service, "insertPlayer(${header.leagueId}, ${body})");



        rest().put("/leagues/{leagueId}/players/{playerCode}")
            .produces("application/json")
            .consumes("application/json")
            .route()
            .process(e -> e.getIn().setHeader("id", new PlayerId(e.getIn().getHeader("leagueId", String.class), e.getIn().getHeader("playerCode", String.class))))
            .unmarshal().json(JsonLibrary.Jackson, Player.class)
            .bean(service, "savePlayer(${header.id}, ${body})");



        rest().delete("/leagues/{leagueId}/players/{playerCode}")
                .produces("text/plain")
                .route()
                .process(e -> e.getIn().setBody(new PlayerId(e.getIn().getHeader("leagueId", String.class), e.getIn().getHeader("playerCode", String.class))))
                .bean(service, "deletePlayer");


    }
}
