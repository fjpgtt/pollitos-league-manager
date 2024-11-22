package com.iwaconsolti.league.demo.config;

import com.iwaconsolti.league.demo.model.*;
import com.iwaconsolti.league.demo.repository.LeagueRepository;
import com.iwaconsolti.league.demo.repository.PlayerRepository;
import com.iwaconsolti.league.demo.repository.TeamRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Slf4j
@Profile("populated")
@Component
public class LeagueInitializer {
    private final LeagueRepository leagueRepository;
    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;

    public LeagueInitializer(LeagueRepository leagueRepository, TeamRepository teamRepository, PlayerRepository playerRepository) {
        this.leagueRepository = leagueRepository;
        this.teamRepository = teamRepository;
        this.playerRepository = playerRepository;
    }

    @PostConstruct
    private void initializeData() {
        League soccerLeague = new League();
        soccerLeague.setName("Soccer League");
        soccerLeague.setType(LeagueType.SOCCER);
        soccerLeague.setMaxTeams(10);

        League savedLeague = leagueRepository.save(soccerLeague);

        Team team1 = new Team();
        team1.setName("Team A");
        team1.setLeague(savedLeague);

        Team team2 = new Team();
        team2.setName("Team B");
        team2.setLeague(savedLeague);

        Team savedTeam1 = teamRepository.save(team1);
        Team savedTeam2 = teamRepository.save(team2);

        Player player1 = new Player();
        player1.setName("Player 1");
        player1.setTeam(savedTeam1);

        Player player2 = new Player();
        player2.setName("Player 2");
        player2.setTeam(savedTeam1);

        Player player3 = new Player();
        player3.setName("Player 3");
        player3.setTeam(savedTeam2);

        Player player4 = new Player();
        player4.setName("Player 4");
        player4.setTeam(savedTeam2);

        playerRepository.save(player1);
        playerRepository.save(player2);
        playerRepository.save(player3);
        playerRepository.save(player4);

        log.info("LeagueInitializer completed: Soccer League created with 2 teams and 4 players.");

    }
}