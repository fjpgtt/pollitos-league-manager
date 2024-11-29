package com.iwaconsolti.league.manager.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MatchesRequest {

    private int idMatch;
    private String leagueType;
    private int teamIdA;
    private String teamNameA;
    private int teamIdB;
    private String teamNameB;
    private int scoreTeamA;
    private int scoreTeamB;

}
