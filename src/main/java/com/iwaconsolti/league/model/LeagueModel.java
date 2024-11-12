package com.iwaconsolti.league.model;

import lombok.Data;
import java.util.List;

@Data
public class  LeagueModel {
    private int idLeague;
    private String nameleague;
    private List<TeamModel> teamsleague;
    private List<PlayerModel> playersleague;
    private List<MatchModel> matchesleague;
}
