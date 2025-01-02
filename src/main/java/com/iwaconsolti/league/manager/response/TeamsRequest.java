package com.iwaconsolti.league.manager.response;

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

    public TeamsRequest(int id, String name) {
        this.id = id;
        this.name = name;
    }

}
