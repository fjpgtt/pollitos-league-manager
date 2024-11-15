package com.iwaconsolti.league.demo.config;

import com.iwaconsolti.league.demo.model.*;
import com.iwaconsolti.league.demo.service.LeagueService;
import com.iwaconsolti.league.demo.service.TeamService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Profile("populated")
public class LeagueInitializer {
    private final LeagueService leagueService;
    private final TeamService teamService;
    @Value("${league.max-teams}")
    private int maxTeams;

    public LeagueInitializer(LeagueService leagueService, TeamService teamService) {
        this.leagueService = leagueService;
        this.teamService = teamService;
        initializeData();
    }

    @PostConstruct
    private void initializeData() {
        League soccerLeague = new League();
        soccerLeague.setName("Soccer League");
        soccerLeague.setType(LeagueType.SOCCER);
        log.info("maxTeams before set: " + maxTeams);
        soccerLeague.setMaxTeams(maxTeams);
        log.info("maxTeams before set: " + maxTeams);
        League createdSoccerLeague = leagueService.createLeague(soccerLeague);

        Team soccerTeam1 = new Team();
        soccerTeam1.setName("Soccer Team 1");
        Team soccerTeam2 = new Team();
        soccerTeam2.setName("Soccer Team 2");

        Player player1 = new Player();
        player1.setName("Soccer Player 1");
        player1.setId(1L);

        Player player2 = new Player();
        player2.setName("Soccer Player 2");
        player2.setId(2L);

        Player player3 = new Player();
        player3.setName("Soccer Player 3");
        player3.setId(3L);

        Player player4 = new Player();
        player4.setName("Soccer Player 4");
        player4.setId(4L);

        Team createdTeam1 = teamService.createTeam(createdSoccerLeague.getId(), soccerTeam1);
        Team createdTeam2 = teamService.createTeam(createdSoccerLeague.getId(), soccerTeam2);

        player1.setTeam(createdTeam1);
        player2.setTeam(createdTeam1);
        createdTeam1.getPlayerList().add(player1);
        createdTeam1.getPlayerList().add(player2);

        player3.setTeam(createdTeam2);
        player4.setTeam(createdTeam2);
        createdTeam2.getPlayerList().add(player3);
        createdTeam2.getPlayerList().add(player4);

        League baseballLeague = new League();
        baseballLeague.setName("Baseball League");
        baseballLeague.setType(LeagueType.BASEBALL);
        baseballLeague.setMaxTeams(maxTeams);
        League createdBaseballLeague = leagueService.createLeague(baseballLeague);
    }
}