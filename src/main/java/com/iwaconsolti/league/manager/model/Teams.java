package com.iwaconsolti.league.manager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Teams {
    private Long id;
    private String name;
    private List<Players> players;

    public Teams(Teams team) {
        this.id = team.getId();
        this.name = team.getName();
        this.players = team.getPlayers();
    }
}
