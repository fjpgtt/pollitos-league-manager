package com.iwaconsolti.league.demo.service;

import com.iwaconsolti.league.demo.model.Player;
import com.iwaconsolti.league.demo.model.Team;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerService {
    private final TeamService teamService;
    private final List<Player> playerList = new ArrayList<>();
    private long nextId = 1L;

    public PlayerService(TeamService teamService) {
        this.teamService = teamService;
    }

    public Player createPlayer(long leagueId, long teamId, Player player) {
        Team team = teamService.findTeamById(leagueId, teamId)
                .orElseThrow(() -> new IllegalArgumentException("Team not found in league"));

        player.setId(nextId++);
        player.setTeam(team);
        playerList.add(player);
        team.getPlayerList().add(player);

        return player;
    }

    public List<Player> findPlayersByTeam(long leagueId, long teamId) {
        return teamService.findTeamById(leagueId, teamId)
                .map(Team::getPlayerList)
                .orElseThrow(() -> new IllegalArgumentException("Team not found in league"));
    }

    public Player updatePlayer(long leagueId, long teamId, long playerId, Player playerDetails) {
        return playerList.stream()
                .filter(p -> p.getId() == playerId &&
                        p.getTeam().getId() == teamId &&
                        p.getTeam().getLeague().getId() == leagueId)
                .findFirst()
                .map(player -> {
                    if (playerDetails.getName() != null) {
                        player.setName(playerDetails.getName());
                    }
                    if (playerDetails.getTeam() != null && playerDetails.getTeam().getId() != teamId) {
                        Team newTeam = teamService.findTeamById(leagueId, playerDetails.getTeam().getId())
                                .orElseThrow(() -> new IllegalArgumentException("New team not found in league"));
                        player.getTeam().getPlayerList().remove(player);
                        player.setTeam(newTeam);
                        newTeam.getPlayerList().add(player);
                    }
                    return player;
                })
                .orElseThrow(() -> new IllegalArgumentException("Player not found in team/league"));
    }

    public void deletePlayersFromTeam(long leagueId, long teamId) {
        Team team = teamService.findTeamById(leagueId, teamId)
                .orElseThrow(() -> new IllegalArgumentException("Team not found in league"));

        playerList.removeIf(player -> player.getTeam().equals(team));
        team.getPlayerList().clear();
    }

}
