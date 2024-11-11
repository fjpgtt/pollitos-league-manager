package com.iwaconsolti.league.demo.service;

import com.iwaconsolti.league.demo.model.League;
import com.iwaconsolti.league.demo.model.Player;
import com.iwaconsolti.league.demo.model.Team;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeamService {
    private static final Logger logger = LoggerFactory.getLogger(PlayerService.class);
    private final LeagueService leagueService;
    private List<Team> teams = new ArrayList<>();
    private Long nextId = 1L;


    public TeamService(LeagueService leagueService) {
        this.leagueService = leagueService;
    }

    public Team createTeam(Long leagueId, Team team) {
        League league = leagueService.getLeagueById(leagueId)
                .orElseThrow(() -> new IllegalArgumentException("League not found"));

        if (!leagueService.canAddTeamToLeague(league)) {
            throw new IllegalStateException("League has reached maximum team capacity");
        }

        team.setId(nextId++);
        team.setLeague(league);
        teams.add(team);
        league.getTeams().add(team);
        return team;
    }

    public Optional<Team> getTeamById(Long leagueId, Long teamId) {
        return teams.stream()
                .filter(team -> team.getId().equals(teamId) &&
                        team.getLeague().getId().equals(leagueId))
                .findFirst();
    }

    public List<Team> getTeamsByLeague(Long leagueId) {
        return teams.stream()
                .filter(team -> team.getLeague().getId().equals(leagueId))
                .toList();
    }

    public Team updateTeam(Long leagueId, Long teamId, Team teamDetails) {
        return teams.stream()
                .filter(team -> team.getId().equals(teamId) &&
                        team.getLeague().getId().equals(leagueId))
                .findFirst()
                .map(team -> {
                    if (teamDetails.getName() != null) {
                        team.setName(teamDetails.getName());
                    }
                    if (teamDetails.getPlayers() != null) {
                        team.setPlayers(teamDetails.getPlayers());
                    }
                    return team;
                })
                .orElseThrow(() -> new IllegalArgumentException("Team not found in league"));
    }
}