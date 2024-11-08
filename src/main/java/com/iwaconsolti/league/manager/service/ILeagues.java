package com.iwaconsolti.league.manager.service;

import com.iwaconsolti.league.manager.model.Matches;
import com.iwaconsolti.league.manager.model.Players;
import com.iwaconsolti.league.manager.model.Teams;

import java.util.List;
import java.util.Optional;

public interface ILeagues {

    Players savePlayers(Players players);

    Optional<Players> findPlayers(Long id);

    Teams saveTeams(Teams teams);

    Optional <Teams> findTeams(Long id);

    Matches saveMatches(Matches matches);

    Optional <Matches> findMatches(Teams team);

    public List<Players> getAllPlayers();


    public List<Teams> getAllTeams();


    public List<Matches> getAllMatches();


}
