package com.iwaconsolti.league.DTO;

import com.iwaconsolti.league.model.PlayerModel;
import com.iwaconsolti.league.model.MatchModel;
import com.iwaconsolti.league.model.TeamModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class LeagueDetailsDTO {

    private List<PlayerModel> players;
    private List<MatchModel> matches;
    private List<TeamModel> teams;

}
