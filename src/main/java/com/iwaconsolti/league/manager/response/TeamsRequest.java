package com.iwaconsolti.league.manager.response;

import com.iwaconsolti.league.manager.persistence.model.Players;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamsRequest {

    private int id;
    private String leagueType;
    private String name;
    private List<String> playerNames = new ArrayList<>();

    public TeamsRequest(String name) {
        this.name = name;
    }

}
