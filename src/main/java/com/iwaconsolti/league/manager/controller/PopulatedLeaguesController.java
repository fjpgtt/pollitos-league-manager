package com.iwaconsolti.league.manager.controller;

import com.iwaconsolti.league.manager.response.PlayersRequest;
import com.iwaconsolti.league.manager.response.TeamsRequest;
import com.iwaconsolti.league.manager.service.ILeagues;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("populated")
@Slf4j
public class PopulatedLeaguesController {

    private final ILeagues soccerLeague;
    private final ILeagues baseBallLeague;

    public PopulatedLeaguesController(@Qualifier("soccerLeague") ILeagues soccerLeague, @Qualifier("baseBallLeague") ILeagues baseBallLeague) {
        this.soccerLeague = soccerLeague;
        this.baseBallLeague = baseBallLeague;
        log.info("Default LeaguesController created");
    }

    @PostConstruct
    public void postConstruct() {
        TeamsRequest team1 = new TeamsRequest("Los Atlas");
        TeamsRequest team2 = new TeamsRequest("Equipo Maravilla");
        soccerLeague.saveTeams("soccer", team1);
        soccerLeague.saveTeams("soccer", team2);

        PlayersRequest player1 = new PlayersRequest("Maradona", 1);
        PlayersRequest player2 = new PlayersRequest("Leonel Messi", 1);
        PlayersRequest player3 = new PlayersRequest("Cristiano Ronaldo", 2);
        PlayersRequest player4 = new PlayersRequest("Piolin", 2);

        soccerLeague.savePlayers("soccer", player1);
        soccerLeague.savePlayers("soccer", player2);
        soccerLeague.savePlayers("soccer", player3);
        soccerLeague.savePlayers("soccer", player4);

        TeamsRequest team3 = new TeamsRequest("Dogers");
        TeamsRequest team4 = new TeamsRequest("Yankis");

        baseBallLeague.saveTeams("baseball", team3);
        baseBallLeague.saveTeams("baseball", team4);

        PlayersRequest player5 = new PlayersRequest("Valenzuela", 3);
        PlayersRequest player6 = new PlayersRequest("Terrenator", 3);
        PlayersRequest player7 = new PlayersRequest("Fiera", 4);
        PlayersRequest player8 = new PlayersRequest("Nakamura", 4);

        baseBallLeague.savePlayers("baseball", player5);
        baseBallLeague.savePlayers("baseball", player6);
        baseBallLeague.savePlayers("baseball", player7);
        baseBallLeague.savePlayers("baseball", player8);

    }
}
