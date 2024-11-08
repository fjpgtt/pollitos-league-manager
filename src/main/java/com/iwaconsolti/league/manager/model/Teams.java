package com.iwaconsolti.league.manager.model;

import lombok.Data;

import java.util.Objects;
import java.util.Random;

@Data
public class Teams {
    private Long id;
    private String name;
    private Players players;

    public Teams(Long id, String name) {
        this.id = id;
        this.name = name;
    }



    public Teams(Long id, String name, Players players) {
        if(Objects.isNull(id)) {
            id = new Random().nextLong();
        }
        this.id = id;
        this.name = name;
        this.players = players;
    }

    public Teams(Teams team) {
        this.id = team.getId();
        this.name = team.getName();
        this.players = team.getPlayers();
    }
}
