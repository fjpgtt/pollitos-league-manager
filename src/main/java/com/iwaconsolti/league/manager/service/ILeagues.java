package com.iwaconsolti.league.manager.service;

import com.iwaconsolti.league.manager.persistence.model.Matches;
import com.iwaconsolti.league.manager.persistence.model.Players;
import com.iwaconsolti.league.manager.persistence.model.Teams;
import com.iwaconsolti.league.manager.response.MatchesRequest;
import com.iwaconsolti.league.manager.response.PlayersRequest;
import com.iwaconsolti.league.manager.response.TeamsRequest;


import java.util.List;

public interface ILeagues {

    Players savePlayers(String leagueType, PlayersRequest playerRequest);

    Teams saveTeams(String leagueType, TeamsRequest teamRequest);

    Matches saveMatches(String leagueType, MatchesRequest matchesRequest);

    Players findPlayers(String leagueType,int id);

    Teams findTeams(String leagueType, int id);

    Matches findMatches(int id);

    List<MatchesRequest> getMatchesByTeam(String leagueType, int teamIdA, int teamIdB);

    List<PlayersRequest> getPlayersTeam(String leagueType, int teamId);

    List<TeamsRequest> getAllTeams();

    Players updatePlayer(String leagueType,int id, Players players);

    Teams updateTeam(String leagueType,int id, Teams team);


    Teams deletePlayersTeam(String leagueType,int teamId);

    void deleteAllMatches();

}
