package com.iwaconsolti.league.manager.controller;

import com.iwaconsolti.league.manager.model.Players;
import com.iwaconsolti.league.manager.model.Teams;
import com.iwaconsolti.league.manager.service.ILeagues;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;

@Profile("populated")
@RestController
@RequestMapping(value = "/leagues/{leagueType}")
@Slf4j
public class PopulatedLeaguesController {

    private ILeagues soccerLeagues;
    private ILeagues baseBallLeague;

    public PopulatedLeaguesController(@Qualifier("soccerLeague") ILeagues soccerLeague, @Qualifier("baseBallLeague") ILeagues baseBallLeague) {
        this.soccerLeagues = soccerLeague;
        this.baseBallLeague = baseBallLeague;
        log.info("Populated LeaguesController created");
    }

    @PostConstruct
    public void postConstruct() {

        Teams team1 = new Teams("Los Atlas");
        Teams team2 = new Teams("Equipo Maravilla");
        Teams team5 = new Teams("Los Reyes");
        Teams team6 = new Teams("Equipo Yeah");
        Teams team7 = new Teams("Equipo Foo");
        Teams team8 = new Teams("Equipo Bar");
        Teams team9 = new Teams("Equipo Basta");
        Teams team10 = new Teams("Equipo Equipo");
        Teams team11 = new Teams("Equipo Super");
        Teams team12 = new Teams("Equipo 10");
        Teams team13 = new Teams("Eq 11");

        soccerLeagues.saveTeams(team1);
        soccerLeagues.saveTeams(team2);
        soccerLeagues.saveTeams(team5);
        soccerLeagues.saveTeams(team6);
        soccerLeagues.saveTeams(team7);
        soccerLeagues.saveTeams(team8);
        soccerLeagues.saveTeams(team9);
        soccerLeagues.saveTeams(team10);
        soccerLeagues.saveTeams(team11);
        soccerLeagues.saveTeams(team12);
        soccerLeagues.saveTeams(team13);

        Teams team3 = new Teams("Dogers");
        Teams team4 = new Teams("Yankis");

        baseBallLeague.saveTeams(team3);
        baseBallLeague.saveTeams(team4);

        log.info("Teams Leagues created");

        Players player1 = new Players("Maradona", team1.getId());
        Players player2 = new Players("Leonel Messi", team1.getId());
        Players player3 = new Players("Cristiano Ronaldo", team2.getId());
        Players player4 = new Players("Piolin", team2.getId());

        soccerLeagues.savePlayers(player1);
        soccerLeagues.savePlayers(player2);
        soccerLeagues.savePlayers(player3);
        soccerLeagues.savePlayers(player4);

        Players player5 = new Players("Valenzuela", team3.getId());
        Players player6 = new Players("Terrenator", team3.getId());
        Players player7 = new Players("Fiera", team3.getId());
        Players player8 = new Players("Nakamura", team3.getId());

        baseBallLeague.savePlayers(player5);
        baseBallLeague.savePlayers(player6);
        baseBallLeague.savePlayers(player7);
        baseBallLeague.savePlayers(player8);

        log.info("Players Leagues created");

    }

}
