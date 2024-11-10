package com.iwaconsolti.league.demo.repository;

import com.iwaconsolti.league.demo.model.Player;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TeamRepository {
    private List<Team> teams = new ArrayList<>();

    @PostConstruct
    public void fillTeams() {
        teams.add(new Team("Team1", 1));
        teams.add(new Team("Team2", 2));
        teams.add(new Team("Team3", 3));
        teams.add(new Team("Team4", 4));
        teams.add(new Team("Team5", 5));
    }


    public String getTeams() {
        StringBuilder result = new StringBuilder();
        for (Team team : teams) {
            result.append(team.getName()).append("\n");
        }
        return result.toString();
    }

    public Team findTeam(String name){
        for(Team team: teams){
            if(team.getName().equalsIgnoreCase(name)){
                return team;
            }
        }
            return null;
    }

    public List<Player> getPlayers(String teamName){
        Team team = findTeam(teamName);
        //Need help: Trying to add a message in case the team has no players
//        List<Player> message = new ArrayList<Player>(new Player("Not found"));
        if(team != null){
            return team.getPlayers();
        }
//        return new ArrayList<Player>(List.of("Not players found"));
        //Sending a null value in case there are no players found.
        return null;
    }

    //For endpoint add
    public void addPlayer(String teamName, Player player) {
        Team team = findTeam(teamName);
        if (team != null) {
            team.addPlayer(player);
        }
    }

    public void deletePLayer(String teamName, Player player){
        Team team = findTeam(teamName);
        if(team != null){
            team.removePlayer(player);
        }
    }

}
