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
    private static final Logger logger = LoggerFactory.getLogger(PlayerService.class);

    private final TeamService teamService;
    private final List<Player> players = new ArrayList<>();
    private Long nextId = 1L;

    public PlayerService(TeamService teamService) {
        this.teamService = teamService;
    }

    public Player addPlayer(Long leagueId, Long teamId, Player player) {
        Team team = teamService.getTeamById(leagueId, teamId)
                .orElseThrow(() -> new IllegalArgumentException("Team not found in league"));

        player.setId(nextId++);
        player.setTeam(team);
        players.add(player);
        team.getPlayers().add(player);

        return player;
    }

    public List<Player> getPlayersByTeam(Long leagueId, Long teamId) {
        return teamService.getTeamById(leagueId, teamId)
                .map(Team::getPlayers)
                .orElseThrow(() -> new IllegalArgumentException("Team not found in league"));
    }

    public Player updatePlayer(Long leagueId, Long teamId, Long playerId, Player playerDetails) {
        return players.stream()
                .filter(p -> p.getId().equals(playerId) &&
                        p.getTeam().getId().equals(teamId) &&
                        p.getTeam().getLeague().getId().equals(leagueId))
                .findFirst()
                .map(player -> {
                    if (playerDetails.getName() != null) {
                        player.setName(playerDetails.getName());
                    }
                    if (playerDetails.getTeam() != null && !playerDetails.getTeam().getId().equals(teamId)) {
                        Team newTeam = teamService.getTeamById(leagueId, playerDetails.getTeam().getId())
                                .orElseThrow(() -> new IllegalArgumentException("New team not found in league"));
                        player.getTeam().getPlayers().remove(player);
                        player.setTeam(newTeam);
                        newTeam.getPlayers().add(player);
                    }
                    return player;
                })
                .orElseThrow(() -> new IllegalArgumentException("Player not found in team/league"));
    }

    public void deletePlayersFromTeam(Long leagueId, Long teamId) {
        Team team = teamService.getTeamById(leagueId, teamId)
                .orElseThrow(() -> new IllegalArgumentException("Team not found in league"));

        players.removeIf(player -> player.getTeam().equals(team));
        team.getPlayers().clear();
    }

}
