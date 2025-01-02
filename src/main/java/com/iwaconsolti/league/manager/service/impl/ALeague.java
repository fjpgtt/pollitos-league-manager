package com.iwaconsolti.league.manager.service.impl;

import com.iwaconsolti.league.manager.exceptions.*;
import com.iwaconsolti.league.manager.persistence.model.Matches;
import com.iwaconsolti.league.manager.persistence.model.Players;
import com.iwaconsolti.league.manager.persistence.model.Teams;
import com.iwaconsolti.league.manager.persistence.repository.IMatchesRepository;
import com.iwaconsolti.league.manager.persistence.repository.IPlayersRepository;
import com.iwaconsolti.league.manager.persistence.repository.ITeamsRepository;
import com.iwaconsolti.league.manager.response.MatchesRequest;
import com.iwaconsolti.league.manager.response.PlayersRequest;
import com.iwaconsolti.league.manager.response.TeamsRequest;
import com.iwaconsolti.league.manager.service.ILeagues;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public abstract class ALeague implements ILeagues {

    @Value("${league.teams.limit:10}")
    private int teamLimit;

    private final IPlayersRepository playersRepository;
    private final ITeamsRepository teamsRepository;
    private final IMatchesRepository matchesRepository;

    public ALeague(IPlayersRepository playersRepository, ITeamsRepository teamsRepository, IMatchesRepository matchesRepository) {
        this.playersRepository = playersRepository;
        this.teamsRepository = teamsRepository;
        this.matchesRepository = matchesRepository;
    }

    @Override
    public Players savePlayers(String leagueType, PlayersRequest playerRequest) {
        Teams team = findTeams(leagueType, playerRequest.getTeamId());

        if (team == null) {
            log.warn("Team id {} not found", playerRequest.getTeamId());
            log.warn("League {} not found", leagueType);
            throw new TeamNotFoundException("Team not found");
        } else if (playerRequest.getName() == null || playerRequest.getName().trim().isEmpty()) {
            log.warn("Player name required!!!");
            throw new NameRequiredException("Player name required!!!");
        }

        Players player = new Players(leagueType, playerRequest.getName(), playerRequest.getTeamId());
        log.debug("Saving player {}", playerRequest);
        player = playersRepository.save(player);

        if (team.getPlayers() == null) {
            team.setPlayers(new ArrayList<>());
        }
        team.getPlayers().add(player);

        teamsRepository.save(team);

        return player;
    }

    private int teamsSize(String leagueType) {
        return teamsRepository.findByLeagueType(leagueType).size();
    }

    @Override
    public Teams saveTeams(String leagueType, TeamsRequest teamRequest) {

        if (teamsSize(leagueType) >= teamLimit) {
            log.warn("Team limit exceeded");
            throw new TeamLimitExceededException("You have reached the maximum limit of teams allowed.");
        } else if (teamRequest.getName() == null || teamRequest.getName().trim().isEmpty()) {
            log.warn("Team name required!!!");
            throw new NameRequiredException("Team name required!!!");
        }

        log.debug("Saving team {}", teamRequest);

        Teams team = new Teams(leagueType, teamRequest.getName());

        return teamsRepository.save(team);
    }

    @Override
    public Matches saveMatches(String leagueType, MatchesRequest matchesRequest) {

        if (findTeams(leagueType, matchesRequest.getTeamIdA()) == null || findTeams(leagueType, matchesRequest.getTeamIdB()) == null) {
            log.warn("Team not found");
            throw new TeamNotFoundException("Team not found");
        }

        log.debug("Saving match {}", matchesRequest);
        Matches match = new Matches(leagueType, matchesRequest.getTeamIdA(), matchesRequest.getTeamIdB(),
                matchesRequest.getScoreTeamA(), matchesRequest.getScoreTeamB());
        return matchesRepository.save(match);
    }

    @Override
    public Players findPlayers(String leagueType, int id) {
        return playersRepository.findPlayersByIdAndLeagueType(id, leagueType).orElse(null);
    }

    @Override
    public PlayersRequest getPlayerById(String leagueType, int id) {
        Players player = findPlayers(leagueType, id);
        if (player != null) {
            return new PlayersRequest(player.getId(), player.getName(), player.getTeamId());
        } else {
            throw new PlayerNotFoundException("Player not found");
        }
    }

    @Override
    public Teams findTeams(String leagueType, int id) {
        return teamsRepository.findByIdAndLeagueType(id, leagueType).orElse(null);
    }

    @Override
    public TeamsRequest getTeamById(String leagueType, int id) {
        Teams team = findTeams(leagueType, id);
        if (team != null) {
            return new TeamsRequest(team.getId(), team.getName());
        } else {
            throw new TeamNotFoundException("Team not found");
        }
    }

    public List<Matches> findMatches(int teamIdA, int teamIdB) {
        return matchesRepository.findByTeamIdAOrTeamIdB(teamIdA, teamIdB);
    }

    @Override
    public List<MatchesRequest> getMatchesByTeam(String leagueType, int teamIdA, int teamIdB) {
        List<MatchesRequest> matchesRequests = new ArrayList<>();

        for (Matches match : findMatches(teamIdA, teamIdB)) {
            if (match.getLeagueType().equals(leagueType)) {
                String teamNameA = match.getTeamA().getName();
                String teamNameB = match.getTeamB().getName();

                matchesRequests.add(new MatchesRequest(match.getIdMatch(), match.getLeagueType(), match.getTeamIdA(), teamNameA,
                        match.getTeamIdB(), teamNameB, match.getTeamScoreA(), match.getTeamScoreB()));
            } else {
                throw new MatchNotFoundException("Match not found");
            }
        }
        return matchesRequests;
    }

    @Override
    public List<PlayersRequest> getPlayersTeam(String leagueType, int teamId) {
        List<PlayersRequest> playersRequests = new ArrayList<>();

        for (Players player : playersRepository.findAll()) {
            if (player.getLeagueType().equals(leagueType)) {
                if (player.getTeamId() == teamId) {
                    playersRequests.add(new PlayersRequest(player.getId(),
                            player.getLeagueType(), player.getName(), player.getTeamId()));
                }
            }
        }
        return playersRequests;
    }

    @Override
    public List<TeamsRequest> getAllTeams(String leagueType) {
        List<TeamsRequest> teamsRequest = new ArrayList<>();

        for (Teams team : teamsRepository.findAll()) {
            if (team.getLeagueType().equals(leagueType)) {
                List<String> playerNames = team.getPlayers()
                        .stream()
                        .map(Players::getName)
                        .toList();
                teamsRequest.add(new TeamsRequest(team.getId(), team.getLeagueType(), team.getName(), playerNames));
            }
        }
        return teamsRequest;
    }

    @Override
    public Players updatePlayer(String leagueType, int id, Players player) {
        Players existingPlayer = findPlayers(leagueType, id);

        if (existingPlayer == null) {
            log.warn("Player id {} not found", id);
            throw new PlayerNotFoundException("Player not found");
        } else if (findTeams(leagueType, player.getTeamId()) == null) {
            log.warn("Team id {} not found", player.getTeamId());
            throw new TeamNotFoundException("Team not found");
        } else if (player.getName() == null || player.getName().trim().isEmpty()) {
            log.warn("Player name required!!!");
            throw new NameRequiredException("Player name required!!!");
        }

        existingPlayer.setName(player.getName());
        existingPlayer.setTeamId(player.getTeamId());

        return playersRepository.save(existingPlayer);
    }

    @Override
    public Teams updateTeam(String leagueType, int teamId, Teams team) {
        Teams existingTeam = findTeams(leagueType, teamId);

        if (existingTeam == null) {
            log.warn("Team with id {} not found", teamId);
            throw new TeamNotFoundException("Team not found");
        } else if (team.getName() == null || team.getName().trim().isEmpty()) {
            log.warn("Player name required!!!");
            throw new NameRequiredException("Player name required!!!");
        }

        existingTeam.setName(team.getName());

        return teamsRepository.save(existingTeam);
    }

    @Override
    public Teams deletePlayersByTeam(String leagueType, int teamId) {
        Teams team = findTeams(leagueType, teamId);
        if (team == null) {
            log.warn("Team with id {} not found", teamId);
            throw new TeamNotFoundException("Team not found");
        }
        List<Players> players = team.getPlayers();
        if (players == null || players.isEmpty()) {
            throw new PlayerNotFoundException("Player not found");
        }
        playersRepository.deleteAll(players);
        team.setPlayers(new ArrayList<>());
        teamsRepository.save(team);

        return team;
    }

    @Override
    public void deleteAllMatches(String leagueType) {
        List<Matches> matches = matchesRepository.findByLeagueType(leagueType);
        if (matches == null || matches.isEmpty()) {
            throw new MatchNotFoundException("Match not found");
        }
        matchesRepository.deleteAll(matches);
    }

}
