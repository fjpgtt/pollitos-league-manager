package com.iwaconsolti.league.manager.model;

import lombok.Data;

@Data
public class Players {
    private int id;
    private String name;
    private int teamId;

    public Players(String name, int teamId) {
        this.name = name;
        this.teamId = teamId;
    }
}
