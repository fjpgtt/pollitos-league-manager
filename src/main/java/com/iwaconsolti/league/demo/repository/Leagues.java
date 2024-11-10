package com.iwaconsolti.league.demo.repository;

import com.iwaconsolti.league.demo.model.Player;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class Leagues {
    private int ID;
    private String name;
    private List<Team> teams = new ArrayList<>();

    public void addTeam(Team team){
        teams.add(team);
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void removeTeam(Player team){
        teams.remove(team);
    }

    public Leagues(String name, int ID) {
        this.name = name;
        this.ID = ID;
    }

    public String getName() {
        return name;
    }



}
