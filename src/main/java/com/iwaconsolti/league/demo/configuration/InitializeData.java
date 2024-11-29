package com.iwaconsolti.league.demo.configuration;

import com.iwaconsolti.league.demo.entity.MatchEntity;
import com.iwaconsolti.league.demo.entity.PlayerEntity;
import com.iwaconsolti.league.demo.entity.TeamEntity;
import com.iwaconsolti.league.demo.repository.MatchRepository;
import com.iwaconsolti.league.demo.repository.PlayerRepository;
import com.iwaconsolti.league.demo.repository.TeamRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("populated")
@Slf4j
public class InitializeData {

    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;
    private final MatchRepository matchRepository;

    public InitializeData(PlayerRepository playerRepository, TeamRepository teamRepository, MatchRepository matchRepository) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
        this.matchRepository = matchRepository;
    }

    @PostConstruct
    public void fillBasketLeagues() {
        TeamEntity team1 = new TeamEntity();
        team1.setName("Team Basket1");
        team1.setLeague("basketleague");
        teamRepository.save(team1);

        TeamEntity team2 = new TeamEntity();
        team2.setName("Team Basket2");
        team2.setLeague("basketleague");
        teamRepository.save(team2);

        PlayerEntity player1 = new PlayerEntity();
        player1.setName("Player1");
        player1.setTeam(team1);
        playerRepository.save(player1);

        PlayerEntity player2 = new PlayerEntity();
        player2.setName("Player2");
        player2.setTeam(team1);
        playerRepository.save(player2);

        PlayerEntity player3 = new PlayerEntity();
        player3.setName("Player3");
        player3.setTeam(team2);
        playerRepository.save(player3);


        PlayerEntity player4 = new PlayerEntity();
        player4.setName("Player4");
        player4.setTeam(team2);
        playerRepository.save(player4);

        log.info("Basket league initialized with teams and players.");


        MatchEntity match = new MatchEntity();
        match.setTeam1(team1);
        match.setTeam2(team2);
        match.setScoreTeam1(1);
        match.setScoreTeam2(2);
        matchRepository.save(match);

        log.info("Match created: {} vs {}", team1.getName(), team2.getName());
    }

    @PostConstruct
    public void fillSoccerLeagues() {
        TeamEntity team3 = new TeamEntity();
        team3.setName("TeamSoccer1");
        team3.setLeague("soccerleague");
        teamRepository.save(team3);

        TeamEntity team4 = new TeamEntity();
        team4.setName("TeamSoccer2");
        team4.setLeague("soccerleague");
        teamRepository.save(team4);

        PlayerEntity player1 = new PlayerEntity();
        player1.setName("Player1");
        player1.setTeam(team3);
        playerRepository.save(player1);

        PlayerEntity player2 = new PlayerEntity();
        player2.setName("Player2");
        player2.setTeam(team3);
        playerRepository.save(player2);

        PlayerEntity player3 = new PlayerEntity();
        player3.setName("Player3");
        player3.setTeam(team4);
        playerRepository.save(player3);


        PlayerEntity player4 = new PlayerEntity();
        player4.setName("Player4");
        player4.setTeam(team4);
        playerRepository.save(player4);

        log.info("Soccer league initialized with teams and players.");


        MatchEntity match1 = new MatchEntity();
        match1.setTeam1(team3);
        match1.setTeam2(team4);
        match1.setScoreTeam1(1);
        match1.setScoreTeam2(2);
        matchRepository.save(match1);

        log.info("Match created: {} vs {}", team3.getName(), team4.getName());
    }


}
