package com.iwaconsolti.league.demo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Player {
    private int id;
    private String name;
    private String team;


    public Player(int id, String name, String team) {
        this.id = id;
        this.name = name;
        this.team = team;
    }

}