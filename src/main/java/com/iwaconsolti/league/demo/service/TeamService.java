package com.iwaconsolti.league.demo.service;

import com.iwaconsolti.league.demo.dto.request.TeamUpdateRequest;
import com.iwaconsolti.league.demo.model.League;
import com.iwaconsolti.league.demo.model.Player;
import com.iwaconsolti.league.demo.model.Team;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeamService {
    private final LeagueService leagueService;
    private final List<Team> teamList = new ArrayList<>();
    private long nextId = 1L;


    public TeamService(LeagueService leagueService) {
        this.leagueService = leagueService;
    }

    public Team createTeam(long leagueId, Team team) {
        League league = leagueService.findLeagueById(leagueId)
                .orElseThrow(() -> new IllegalArgumentException("League not found"));

        if (!leagueService.canAddTeamToLeague(league)) {
            throw new IllegalStateException("League has reached maximum team capacity");
        }

        team.setId(nextId++);
        team.setLeague(league);
        teamList.add(team);
        league.getTeamList().add(team);
        return team;
    }

    public Optional<Team> findTeamById(long leagueId, long teamId) {
        return teamList.stream()
                .filter(team -> team.getId() == (teamId) &&
                        team.getLeague().getId() == (leagueId))
                .findFirst();
    }

    public List<Team> findTeamsByLeague(long leagueId) {
        return teamList.stream()
                .filter(team -> team.getLeague().getId() == (leagueId))
                .toList();
    }

    public Team updateTeam(long leagueId, long teamId, TeamUpdateRequest request) {
        return teamList.stream()
                .filter(team -> team.getId() == teamId &&
                        team.getLeague().getId() == leagueId)
                .findFirst()
                .map(team -> {
                    if (request.name() != null) {
                        team.setName(request.name());
                    }

                    if (request.playerList() != null) {
                        // Limpiar jugadores actuales
                        team.getPlayerList().clear();

                        // Crear nuevos jugadores
                        List<Player> newPlayerList = request.playerList().stream()
                                .map(playerRequest -> {
                                    Player player = new Player();
                                    player.setId(nextId++);
                                    player.setName(playerRequest.name());
                                    player.setTeam(team);
                                    return player;
                                })
                                .collect(Collectors.toList());

                        team.setPlayerList(newPlayerList);
                    }

                    return team;
                })
                .orElseThrow(() -> new IllegalArgumentException("Team not found in league"));
    }
}