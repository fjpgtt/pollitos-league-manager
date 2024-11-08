package com.iwaconsolti.league.demo.repository;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TeamRepository {
    private List<Team> teams = new ArrayList<>();

    @PostConstruct
    public void fillTeams() {
        teams.add(new Team("Team1"));
        teams.add(new Team("Team2"));
        teams.add(new Team("Team3"));
        teams.add(new Team("Team4"));
        teams.add(new Team("Team5"));
    }


    public String getTeams() {
        StringBuilder result = new StringBuilder();
        for (Team team : teams) {
            result.append(team.getName()).append("\n");
        }
        return result.toString();
    }


}
