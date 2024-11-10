package com.iwaconsolti.league.demo.repository;

import com.iwaconsolti.league.demo.model.Player;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Repository
public class LeaguesRepository {
    private List<Leagues> leagues = new ArrayList<>();

    @PostConstruct
    public void fillLeagues() {
        // creating leagues
        Leagues menLeague = new Leagues("Men", 1);
        Leagues womenLeague = new Leagues("Women", 2);

        // Added teams
        Team team1 = new Team("Team1", 1);
        Team team2 = new Team("Team2", 2);
        menLeague.addTeam(team1);
        menLeague.addTeam(team2);

        //Adding players
        Player player1 = new Player(1, "Player1","First");
        Player player2 = new Player(2, "PLayer2", "Second");

        team1.addPlayer(player1);
        team1.addPlayer(player2);

        // added women teams
        Team team3 = new Team("Team3", 3);
        Team team4 = new Team("Team4", 4);
        womenLeague.addTeam(team3);
        womenLeague.addTeam(team4);


        // Agregamos las ligas a la lista de ligas
        leagues.add(menLeague);
        leagues.add(womenLeague);
        System.out.print("Leagues filled with teams");
    }

    public List<Leagues> getAllLeagues() {
        return leagues;
    }

    public Leagues findLeague(String name) {
        for (Leagues league : leagues) {
            if (league.getName().equalsIgnoreCase(name)) {
                return league;
            }
        }
        return null;
    }

    public void addTeamToLeague(String leagueName, Team team) {
        Leagues league = findLeague(leagueName);
        if (league != null) {
            league.addTeam(team);
        }
    }

    public List<Team> getTeamsFromLeague(String leagueName) {
        Leagues league = findLeague(leagueName);
        if(league!= null){
            return league.getTeams();
        }
        return null;

    }


}
