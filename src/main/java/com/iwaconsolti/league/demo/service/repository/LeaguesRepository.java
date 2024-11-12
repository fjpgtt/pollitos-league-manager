package com.iwaconsolti.league.demo.service.repository;

import com.iwaconsolti.league.demo.model.Player;
import com.iwaconsolti.league.demo.model.Team;
import com.iwaconsolti.league.demo.service.BasketLeague;
import com.iwaconsolti.league.demo.service.SoccerLeague;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
@Slf4j
public class LeaguesRepository {
    private final BasketLeague basketLeague;
    private final SoccerLeague soccerLeague;

    @Autowired
    public LeaguesRepository(BasketLeague basketLeague, SoccerLeague soccerLeague) {
        this.basketLeague = basketLeague;
        this.soccerLeague = soccerLeague;
    }


    @PostConstruct
    public void fillLeagues() {
        // creating leagues

        // Creating teams
        Team team1 = new Team("team1", 0, 1);
        Team team2 = new Team("team2", 0, 2);
        Team team3 = new Team("team3", 0, 3);
        Team team4 = new Team("team4", 0, 4);

        //Creating players
        Player player1 = new Player(1, "Player1", team1.getName());
        Player player2 = new Player(2, "Player2", team2.getName());
        //Creating players
        Player player3 = new Player(1, "Player3", team1.getName());
        Player player4 = new Player(2, "Player4", team2.getName());

        //Adding Players to teams
        team1.addPlayer(player1);

        //Adding Teams to league basket
        basketLeague.createTeam(team1);
        basketLeague.createTeam(team2);

        //Adding Player to Team
        basketLeague.createPlayer(player1);
        basketLeague.createPlayer(player2);

        //Adding Team to soccerLeague
        soccerLeague.createTeam(team3);
        soccerLeague.createTeam(team4);

        //Adding Player to Team
        soccerLeague.createPlayer(player1);
        soccerLeague.createPlayer(player2);

        //Creating Match for basket
        basketLeague.createMatch(team1, team2);
        //Creating Match for basket
        soccerLeague.createMatch(team4, team3);

        log.info("leagues already created and filled.");
    }

}
