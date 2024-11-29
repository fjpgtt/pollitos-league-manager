package com.iwaconsolti.league.manager.response;

import com.iwaconsolti.league.manager.persistence.model.Players;
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
}
