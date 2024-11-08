package com.iwaconsolti.league.manager.model;

import lombok.Data;

import java.util.Objects;
import java.util.Random;

@Data
public class Players {
    private Long id;
    private String name;

    public Players(Long id, String name) {
        if(Objects.isNull(id)) {
            id = new Random().nextLong();
        }
        this.id = id;
        this.name = name;
    }

    public Players(Players players) {
        this.id = players.getId();
        this.name = players.getName();
    }

    public Players() {}

}
