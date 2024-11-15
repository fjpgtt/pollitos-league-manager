package com.iwaconsolti.league.demo.service;

import com.iwaconsolti.league.demo.model.League;
import com.iwaconsolti.league.demo.model.Match;
import com.iwaconsolti.league.demo.model.Team;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MatchService {
    private final LeagueService leagueService;

    private final List<Match> matchList = new ArrayList<>();
    private long nextId = 1L;

    public MatchService(LeagueService leagueService) {
        this.leagueService = leagueService;
    }
    public Match createMatch(long leagueId, Match match) {
        League league = leagueService.findLeagueById(leagueId)
                .orElseThrow(() -> new IllegalArgumentException("Liga no encontrada"));

        Team homeTeam = league.getTeamList().stream()
                .filter(team -> team.getId() == match.getHomeTeam().getId())
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Equipo local no encontrado en la liga"));

        Team awayTeam = league.getTeamList().stream()
                .filter(team -> team.getId() == match.getAwayTeam().getId())
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Equipo visitante no encontrado en la liga"));

        validateTeamsInSameLeague(homeTeam, awayTeam, league);

        match.setId(nextId++);
        match.setLeague(league);
        matchList.add(match);
        league.getMatchList().add(match);
        return match;
    }

    public void deleteAllMatchesFromLeague(long leagueId) {
        League league = leagueService.findLeagueById(leagueId)
                .orElseThrow(() -> new IllegalArgumentException("League not found"));

        matchList.removeIf(match -> match.getLeague().equals(league));
        league.getMatchList().clear();
    }

    private void validateTeamsInSameLeague(Team homeTeam, Team awayTeam, League league) {
        Team foundHomeTeam = league.getTeamList().stream()
                .filter(team -> team.getId() == (homeTeam.getId()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Home team not found in league"));

        Team foundAwayTeam = league.getTeamList().stream()
                .filter(team -> team.getId() == (awayTeam.getId()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Away team not found in league"));
    }

    public List<Match> findMatchesByTeam(long leagueId, long teamId) {
        League league = leagueService.findLeagueById(leagueId)
                .orElseThrow(() -> new IllegalArgumentException("League not found"));

        return matchList.stream()
                .filter(match -> match.getLeague().getId() == (leagueId) &&
                        (match.getHomeTeam().getId() == (teamId) ||
                                match.getAwayTeam().getId() == (teamId)))
                .collect(Collectors.toList());
    }
}
