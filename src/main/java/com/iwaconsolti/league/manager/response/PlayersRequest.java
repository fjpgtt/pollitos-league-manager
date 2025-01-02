package com.iwaconsolti.league.manager.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayersRequest {

    private int id;
    private String leagueType;
    private String name;
    private int teamId;

    public PlayersRequest(String name, int teamId) {
        this.name = name;
        this.teamId = teamId;
    }

    public PlayersRequest(int id, String name, int teamId) {
        this.id = id;
        this.name = name;
        this.teamId = teamId;
    }
}
