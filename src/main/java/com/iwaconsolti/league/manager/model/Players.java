package com.iwaconsolti.league.manager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Players {
    private Long id;
    private String name;
    private Long teamId;

    public Players(Players players) {
        this.id = players.getId();
        this.name = players.getName();
        this.teamId = players.getTeamId();
    }

}
