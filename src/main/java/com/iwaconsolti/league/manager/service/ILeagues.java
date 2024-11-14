package com.iwaconsolti.league.manager.service;

import com.iwaconsolti.league.manager.model.Matches;
import com.iwaconsolti.league.manager.model.Players;
import com.iwaconsolti.league.manager.model.Teams;

import java.util.List;
import java.util.Optional;

public interface ILeagues {

    Players savePlayers(Players players);

    Players findPlayers(int id);

    Teams saveTeams(Teams teams);

    Teams findTeams(int id);

    Matches saveMatches(Matches matches);

    Matches findMatches(int id);

    List<Matches> getMatchesTeam(String nameTeam);

    Players updatePlayer(int id, Players players);

    Teams updateTeam(int id, Teams team);

    List<Teams> getAllTeams();

    Teams deletePlayersTeam(int teamId);

    List<Matches> deleteAllMatches();

    List<Players> getPlayersTeam(int teamId);


}
