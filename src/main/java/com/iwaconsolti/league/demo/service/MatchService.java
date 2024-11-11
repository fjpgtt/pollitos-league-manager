package com.iwaconsolti.league.demo.service;

import com.iwaconsolti.league.demo.model.League;
import com.iwaconsolti.league.demo.model.Match;
import com.iwaconsolti.league.demo.model.Team;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MatchService {
    private static final Logger logger = LoggerFactory.getLogger(MatchService.class);
    private final LeagueService leagueService;

    private final List<Match> matches = new ArrayList<>();
    private Long nextId = 1L;

    public MatchService(LeagueService leagueService) {
        this.leagueService = leagueService;
    }
    public Match createMatch(Long leagueId, Match match) {
        League league = leagueService.getLeagueById(leagueId)
                .orElseThrow(() -> new IllegalArgumentException("League not found"));

        validateTeamsInSameLeague(match.getHomeTeam(), match.getAwayTeam(), league);

        match.setId(nextId++);
        match.setLeague(league);
        matches.add(match);
        league.getMatches().add(match);
        return match;
    }

    public void deleteAllMatchesFromLeague(Long leagueId) {
        League league = leagueService.getLeagueById(leagueId)
                .orElseThrow(() -> new IllegalArgumentException("League not found"));

        matches.removeIf(match -> match.getLeague().equals(league));
        league.getMatches().clear();
    }

    private void validateTeamsInSameLeague(Team homeTeam, Team awayTeam, League league) {
        Team foundHomeTeam = league.getTeams().stream()
                .filter(team -> team.getId().equals(homeTeam.getId()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Home team not found in league"));

        Team foundAwayTeam = league.getTeams().stream()
                .filter(team -> team.getId().equals(awayTeam.getId()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Away team not found in league"));
    }

    public List<Match> getMatchesByTeam(Long leagueId, Long teamId) {
        League league = leagueService.getLeagueById(leagueId)
                .orElseThrow(() -> new IllegalArgumentException("League not found"));

        return matches.stream()
                .filter(match -> match.getLeague().getId().equals(leagueId) &&
                        (match.getHomeTeam().getId().equals(teamId) ||
                                match.getAwayTeam().getId().equals(teamId)))
                .collect(Collectors.toList());
    }
}
