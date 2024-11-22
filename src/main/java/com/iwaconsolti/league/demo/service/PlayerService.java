package com.iwaconsolti.league.demo.service;

import com.iwaconsolti.league.demo.model.Player;
import com.iwaconsolti.league.demo.model.Team;
import com.iwaconsolti.league.demo.repository.PlayerRepository;
import com.iwaconsolti.league.demo.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;

    public PlayerService(PlayerRepository playerRepository, TeamRepository teamRepository) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
    }

    public Player createPlayer(long leagueId, long teamId, Player player) {
        Team team = teamRepository.findById(teamId)
                .filter(t -> t.getLeague().getId() == leagueId)
                .orElseThrow(() -> new IllegalArgumentException("Team not found in league"));

        player.setTeam(team);
        return playerRepository.save(player);
    }

    public List<Player> findPlayersByTeam(long leagueId, long teamId) {
        Team team = teamRepository.findById(teamId)
                .filter(t -> t.getLeague().getId() == leagueId)
                .orElseThrow(() -> new IllegalArgumentException("Team not found in league"));

        return playerRepository.findByTeamId(team.getId());
    }

    public Player updatePlayer(long leagueId, long teamId, long playerId, Player playerDetails) {
        Player player = playerRepository.findById(playerId)
                .filter(p -> p.getTeam().getId() == teamId && p.getTeam().getLeague().getId() == leagueId)
                .orElseThrow(() -> new IllegalArgumentException("Player not found in team/league"));

        if (playerDetails.getName() != null) {
            player.setName(playerDetails.getName());
        }
        if (playerDetails.getTeam() != null && playerDetails.getTeam().getId() != teamId) {
            Team newTeam = teamRepository.findById(playerDetails.getTeam().getId())
                    .filter(t -> t.getLeague().getId() == leagueId)
                    .orElseThrow(() -> new IllegalArgumentException("New team not found in league"));
            player.setTeam(newTeam);
        }

        return playerRepository.save(player);
    }

    public void deletePlayersFromTeam(long leagueId, long teamId) {
        Team team = teamRepository.findById(teamId)
                .filter(t -> t.getLeague().getId() == leagueId)
                .orElseThrow(() -> new IllegalArgumentException("Team not found in league"));

        List<Player> players = playerRepository.findByTeamId(team.getId());
        playerRepository.deleteAll(players);
    }
}
