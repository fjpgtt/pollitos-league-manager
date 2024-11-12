package com.iwaconsolti.league.demo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class Team {
    private int ID;
    private int score;
    private String name;
    private List<Player> players = new ArrayList<>();

    public Team(String name, int score, int ID) {
        this.name = name;
        this.score = score;
        this.ID = ID;
    }

    public void addPlayer(Player player){
        players.add(player);
    }

}
