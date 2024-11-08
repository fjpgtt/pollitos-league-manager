package com.iwaconsolti.league.manager.service.impl;

import com.iwaconsolti.league.manager.model.Matches;
import com.iwaconsolti.league.manager.model.Players;
import com.iwaconsolti.league.manager.model.Teams;
import com.iwaconsolti.league.manager.service.ILeagues;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SoccerLeague implements ILeagues {

    private List<Players> players = new ArrayList<Players>();
    private List<Teams> teams = new ArrayList<Teams>();
    private List<Matches> matches = new ArrayList<Matches>();

    @Override
    public Players savePlayers(Players player) {
        Players existingPlayer = findPlayers(player.getId()).orElse(null);
        if (existingPlayer == null) {
            players.add(player);
        } else {
            players.remove(existingPlayer);
            Players newPlayer = new Players(player);
            players.add(newPlayer);
        }
        return player;
    }

    @Override
    public Optional<Players> findPlayers(Long id) {
        return players.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    @Override
    public Teams saveTeams(Teams team) {
        Teams existingTeam = findTeams(team.getId()).orElse(null);
        if (existingTeam == null) {
            teams.add(team);
        } else {
            teams.remove(existingTeam);
            Teams newTeam = new Teams (team);
            teams.add(newTeam);
        }
        return team;
    }

    @Override
    public Optional <Teams> findTeams(Long id) {
        return teams.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    @Override
    public Matches saveMatches(Matches match) {
        Optional<Matches> existingMatch = findMatches(match.getTeam());

        if (existingMatch.isEmpty()) {
            matches.add(match);
        } else {
            matches.remove(existingMatch.get());
            matches.add(new Matches(match));
        }

        return match;
    }

    public Optional<Matches> findMatches(Teams team) {
        return matches.stream().filter(m -> m.getTeam().equals(team)).findFirst();
    }

    @Override
    public List<Players> getAllPlayers() {
        return new ArrayList<>(players);
    }

    @Override
    public List<Teams> getAllTeams() {
        return new ArrayList<>(teams);
    }

    @Override
    public List<Matches> getAllMatches() {
        return new ArrayList<>(matches);
    }
}
