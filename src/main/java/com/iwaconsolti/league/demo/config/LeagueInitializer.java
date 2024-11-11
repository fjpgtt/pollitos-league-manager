package com.iwaconsolti.league.demo.config;

import com.iwaconsolti.league.demo.model.*;
import com.iwaconsolti.league.demo.service.LeagueService;
import com.iwaconsolti.league.demo.service.TeamService;
import org.springframework.stereotype.Component;

@Component
public class LeagueInitializer {
    private final LeagueService leagueService;
    private final TeamService teamService;
    private final int maxTeams;

    public LeagueInitializer(LeagueService leagueService, TeamService teamService, int maxTeams) {
        this.leagueService = leagueService;
        this.teamService = teamService;
        this.maxTeams = maxTeams;
        initializeData();
    }

    private void initializeData() {
        // Crear League de Soccer
        League soccerLeague = new League();
        soccerLeague.setName("Soccer League");
        soccerLeague.setType(LeagueType.SOCCER);
        soccerLeague.setMaxTeams(maxTeams);
        League createdSoccerLeague = leagueService.createLeague(soccerLeague);

        // Crear equipos para Soccer
        Team soccerTeam1 = new Team();
        soccerTeam1.setName("Soccer Team 1");

        Team soccerTeam2 = new Team();
        soccerTeam2.setName("Soccer Team 2");

        // Crear players
        Player player1 = new Player();
        player1.setName("Soccer Player 1");

        Player player2 = new Player();
        player2.setName("Soccer Player 2");

        Player player3 = new Player();
        player3.setName("Soccer Player 3");

        Player player4 = new Player();
        player4.setName("Soccer Player 4");

        // Crear teams en la liga
        Team createdTeam1 = teamService.createTeam(createdSoccerLeague.getId(), soccerTeam1);
        Team createdTeam2 = teamService.createTeam(createdSoccerLeague.getId(), soccerTeam2);

        // Establecer relaciones con players
        player1.setTeam(createdTeam1);
        player2.setTeam(createdTeam1);
        createdTeam1.getPlayers().add(player1);
        createdTeam1.getPlayers().add(player2);

        player3.setTeam(createdTeam2);
        player4.setTeam(createdTeam2);
        createdTeam2.getPlayers().add(player3);
        createdTeam2.getPlayers().add(player4);

        // Similar para baseball
        League baseballLeague = new League();
        baseballLeague.setName("Baseball League");
        baseballLeague.setType(LeagueType.BASEBALL);
        baseballLeague.setMaxTeams(maxTeams);
        League createdBaseballLeague = leagueService.createLeague(baseballLeague);

        // ... similar para baseball teams y players
    }
}