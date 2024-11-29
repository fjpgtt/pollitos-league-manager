package com.iwaconsolti.league.manager.service.impl;

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
        Teams team = findTeams(playerRequest.getLeagueType(),playerRequest.getTeamId());
        if (team == null) {
            log.warn("Team not found");
            log.info("the team id {}", playerRequest.getTeamId());
            return null;
        }
        Players player = new Players(playerRequest.getName(), playerRequest.getTeamId());
        log.debug("Saving player {}", playerRequest);
        player = playersRepository.save(player);

        if (team.getPlayers() == null) {
            team.setPlayers(new ArrayList<>());
        }
        team.getPlayers().add(player);
        teamsRepository.save(team);

        return player;
    }

    @Override
    public Teams saveTeams(String leagueType,TeamsRequest teamRequest) {
        if (teamsRepository.count() >= teamLimit) {
            log.warn("Team limit exceeded");
            return null;
        }

        log.debug("Saving team {}", teamRequest);

        Teams team = new Teams(teamRequest.getName());

        return teamsRepository.save(team);
    }

    @Override
    public Matches saveMatches(String leagueType,MatchesRequest matchesRequest) {

        if (findTeams(matchesRequest.getLeagueType(), matchesRequest.getTeamIdA()) == null && findTeams(matchesRequest.getLeagueType(), matchesRequest.getTeamIdB()) == null) {
            log.warn("Team not found");
            return null;
        }

        log.debug("Saving match {}", matchesRequest);
        Matches match = new Matches(matchesRequest.getLeagueType(), matchesRequest.getTeamIdA(), matchesRequest.getTeamIdB(),
                matchesRequest.getScoreTeamA(), matchesRequest.getScoreTeamB());
        return matchesRepository.save(match);
    }

    @Override
    public Players findPlayers(String leagueType,int id) {
        return playersRepository.findById(id).orElse(null);
    }

    @Override
    public Teams findTeams(String leagueType,int id) {
        return teamsRepository.findById(id).orElse(null);
    }

    public Matches findMatches(int id) {
        return matchesRepository.findById(id).orElse(null);
    }

    @Override
    public List<MatchesRequest> getMatchesByTeam(String leagueType,int teamIdA, int teamIdB) {
        List<MatchesRequest> matchesRequests = new ArrayList<>();

        for (Matches match : matchesRepository.findByTeamIdAOrTeamIdB(teamIdA, teamIdB)) {
            String teamNameA = match.getTeamA().getName();
            String teamNameB = match.getTeamB().getName();

            matchesRequests.add(new MatchesRequest(match.getIdMatch(), match.getLeagueType(), match.getTeamIdA(), teamNameA,
                    match.getTeamIdB(), teamNameB, match.getTeamScoreA(), match.getTeamScoreB()));
        }
        return matchesRequests;
    }

    @Override
    public List<PlayersRequest> getPlayersTeam(String leagueType,int teamId) {
        List<PlayersRequest> playersRequests = new ArrayList<>();

        for (Players player : playersRepository.findAll()) {
            if (player.getTeamId() == teamId) {
                playersRequests.add(new PlayersRequest(player.getId(), player.getLeagueType(), player.getName(), player.getTeamId()));
            }
        }
        return playersRequests;
    }

    @Override
    public List<TeamsRequest> getAllTeams() {
        List<TeamsRequest> teamsRequest = new ArrayList<>();

        for (Teams team : teamsRepository.findAll()) {
            List<String> playerNames = team.getPlayers()
                    .stream()
                    .map(Players::getName)
                    .toList();
            teamsRequest.add(new TeamsRequest(team.getId(), team.getLeagueType(), team.getName(), playerNames));
        }

        return teamsRequest;
    }


    @Override
    public Players updatePlayer(String leagueType,int id, Players player) {
        if (!playersRepository.existsById(id)) {
            log.warn("Player with id {} not found", id);
            return null;
        } else if (teamsRepository.existsById(player.getTeamId())) {
            log.warn("Team with id {} not found", player.getTeamId());
            return null;
        }

        return playersRepository.save(player);
    }

    @Override
    public Teams updateTeam(String leagueType,int id, Teams team) {
        if (!teamsRepository.existsById(id)) {
            log.warn("Team with id {} not found", id);
            return null;
        }

        return teamsRepository.save(team);
    }

    @Override
    public Teams deletePlayersTeam(String leagueType,int teamId) {
        Teams team = teamsRepository.findById(teamId).orElse(null);
        if (team == null) {
            log.warn("Team not found");
            return null;
        }

        List<Players> players = team.getPlayers();
        if (players != null) {
            playersRepository.deleteAll(players);
            team.setPlayers(new ArrayList<>());
            teamsRepository.save(team);
        }
        return team;
    }

    @Override
    public void deleteAllMatches() {
        matchesRepository.deleteAll();
    }

}
