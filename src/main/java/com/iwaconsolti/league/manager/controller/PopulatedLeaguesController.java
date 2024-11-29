package com.iwaconsolti.league.manager.controller;

import com.iwaconsolti.league.manager.persistence.model.Players;
import com.iwaconsolti.league.manager.persistence.model.Teams;
import com.iwaconsolti.league.manager.response.PlayersRequest;
import com.iwaconsolti.league.manager.response.TeamsRequest;
import com.iwaconsolti.league.manager.service.ILeagues;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;

@Profile("populated")
@Slf4j
public class PopulatedLeaguesController {

    private ILeagues soccerLeagues;
    private ILeagues baseBallLeague;



    @PostConstruct
    public void postConstruct() {
/*
        TeamsRequest team1 = new TeamsRequest("Los Atlas");
        TeamsRequest team2 = new TeamsRequest("Equipo Maravilla");
        TeamsRequest team5 = new TeamsRequest("Los Reyes");
        TeamsRequest team6 = new TeamsRequest("Equipo Yeah");
        TeamsRequest team7 = new TeamsRequest("Equipo Foo");
        TeamsRequest team8 = new TeamsRequest("Equipo Bar");
        TeamsRequest team9 = new TeamsRequest("Equipo Basta");
        TeamsRequest team10 = new TeamsRequest("Equipo Equipo");
        TeamsRequest team11 = new TeamsRequest("Equipo Super");
        TeamsRequest team12 = new TeamsRequest("Equipo 10");
        TeamsRequest team13 = new TeamsRequest("Eq 11");

        *//*soccerLeagues.saveTeams(team1);
        soccerLeagues.saveTeams(team2);
        soccerLeagues.saveTeams(team5);
        soccerLeagues.saveTeams(team6);
        soccerLeagues.saveTeams(team7);
        soccerLeagues.saveTeams(team8);
        soccerLeagues.saveTeams(team9);
        soccerLeagues.saveTeams(team10);
        soccerLeagues.saveTeams(team11);
        soccerLeagues.saveTeams(team12);
        soccerLeagues.saveTeams(team13);*//*

        *//*TeamsRequest team3 = new TeamsRequest("Dogers");
        TeamsRequest team4 = new TeamsRequest("Yankis");

        baseBallLeague.saveTeams(team3);
        baseBallLeague.saveTeams(team4);*//*

        log.info("Teams Leagues created");

        PlayersRequest player1 = new PlayersRequest("Maradona", 1);
        PlayersRequest player2 = new PlayersRequest("Leonel Messi", 1);
        PlayersRequest player3 = new PlayersRequest("Cristiano Ronaldo", 2);
        PlayersRequest player4 = new PlayersRequest("Piolin", 2);

        soccerLeagues.savePlayers(player1);
        soccerLeagues.savePlayers(player2);
        soccerLeagues.savePlayers(player3);
        soccerLeagues.savePlayers(player4);

        *//*PlayersRequest player5 = new PlayersRequest("Valenzuela", team3.getId());
        PlayersRequest player6 = new PlayersRequest("Terrenator", team3.getId());
        PlayersRequest player7 = new PlayersRequest("Fiera", team3.getId());
        PlayersRequest player8 = new PlayersRequest("Nakamura", team3.getId());

        baseBallLeague.savePlayers(player5);
        baseBallLeague.savePlayers(player6);
        baseBallLeague.savePlayers(player7);
        baseBallLeague.savePlayers(player8);*//*

        log.info("Players Leagues created");*/

    }

}
