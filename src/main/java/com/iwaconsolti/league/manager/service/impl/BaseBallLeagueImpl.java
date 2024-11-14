package com.iwaconsolti.league.manager.service.impl;


import com.iwaconsolti.league.manager.model.Matches;
import com.iwaconsolti.league.manager.model.Players;
import com.iwaconsolti.league.manager.model.Teams;
import com.iwaconsolti.league.manager.service.ILeagues;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service("baseBallLeague")
@Slf4j
public class BaseBallLeagueImpl implements ILeagues {

    @Value("${league.teams.limit}")
    private int teamLimit;

    private List<Players> players = new ArrayList<>();
    private List<Teams> teams = new ArrayList<>();
    private List<Matches> matches = new ArrayList<>();
    private int nextPlayer = 1;
    private int nextTeam = 1;
    private int nextMatches = 1;

    @Override
    public Players savePlayers(Players player) {
        log.debug("Saving player {}", player);
        player.setId(nextPlayer++);
        players.add(player);
        Teams team = findTeams(player.getTeamId());
        if (team != null) {
            if (team.getPlayers() == null) {
                team.setPlayers(new ArrayList<>());
            }
            team.getPlayers().add(player);
        } else {
            log.warn("Team not found");
        }

        return player;
    }

    @Override
    public Players findPlayers(int id) {
        for (Players player : players) {
            if (player.getId() == id) {
                return player;
            } else {
                log.warn("Player not found");
            }
        }

        return null;
    }

    @Override
    public Teams saveTeams(Teams team) {
        if (teams.size() >= teamLimit) {
            log.warn("Team limit exceeded");
        }
        log.debug("Saving team {}", team);
        team.setId(nextTeam++);
        teams.add(team);

        return team;
    }

    @Override
    public Teams findTeams(int id) {
        for (Teams team : teams) {
            if (team.getId() == id) {
                return team;
            } else {
                log.warn("Team not found");
            }
        }
        return null;
    }

    @Override
    public Matches saveMatches(Matches match) {
        log.debug("Saving match {}", match);
        match.setIdMatch(nextMatches++);
        matches.add(match);
        return match;
    }

    public Matches findMatches(int id) {
        for (Matches match : matches) {
            if (match.getIdMatch() == id) {
                return match;
            } else {
                log.warn("Match not found");
            }
        }
        return null;
    }

    @Override
    public List<Matches> getMatchesTeam(String nameTeam) {
        List<Matches> matchesTeam = new ArrayList<>();

        for (Matches match : matches) {
            if (match.getNameTeam1().equals(nameTeam)) {
                matchesTeam.add(match);
            } else if (match.getNameTeam2().equals(nameTeam)) {
                matchesTeam.add(match);
            }
        }

        return matchesTeam;
    }

    @Override
    public Players updatePlayer(int id, Players player) {
        Players existingPlayer = findPlayers(id);
        if (existingPlayer == null) {
            log.warn("Player with id {} not found", id);
            return player;
        }

        existingPlayer.setName(player.getName());
        existingPlayer.setTeamId(player.getTeamId());

        return existingPlayer;
    }

    @Override
    public Teams updateTeam(int id, Teams team) {
        Teams existingTeam = findTeams(id);
        if (existingTeam == null) {
            log.warn("Team with id {} not found", id);
            return team;
        }

        existingTeam.setName(team.getName());

        return existingTeam;
    }


    @Override
    public List<Players> getPlayersTeam(int teamId) {
        return players.stream().filter(player -> player.getTeamId() == teamId).collect(Collectors.toList());
    }

    @Override
    public Teams deletePlayersTeam(int teamId) {
        for (Teams team : teams) {
            if (team.getId() == teamId) {
                team.getPlayers().clear();
                return team;
            } else {
                log.warn("Team not found");
            }
        }
        return null;
    }

    @Override
    public List<Matches> deleteAllMatches() {
        matches.clear();
        return matches;
    }

    @Override
    public List<Teams> getAllTeams() {
        return new ArrayList<>(teams);
    }
}
