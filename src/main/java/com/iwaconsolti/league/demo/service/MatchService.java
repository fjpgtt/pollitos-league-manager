package com.iwaconsolti.league.demo.service;

import com.iwaconsolti.league.demo.model.League;
import com.iwaconsolti.league.demo.model.Match;
import com.iwaconsolti.league.demo.model.Team;
import com.iwaconsolti.league.demo.repository.LeagueRepository;
import com.iwaconsolti.league.demo.repository.MatchRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MatchService {
    private final MatchRepository matchRepository;
    private final LeagueRepository leagueRepository;

    public MatchService(MatchRepository matchRepository, LeagueRepository leagueRepository) {
        this.matchRepository = matchRepository;
        this.leagueRepository = leagueRepository;
    }

    public Match createMatch(long leagueId, Match match) {
        League league = leagueRepository.findById(leagueId)
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

        match.setLeague(league);
        return matchRepository.save(match);
    }

    public void deleteAllMatchesFromLeague(long leagueId) {
        League league = leagueRepository.findById(leagueId)
                .orElseThrow(() -> new IllegalArgumentException("Liga no encontrada"));

        matchRepository.deleteAll(league.getMatchList());
    }

    public List<Match> findMatchesByTeam(long leagueId, long teamId) {
        League league = leagueRepository.findById(leagueId)
                .orElseThrow(() -> new IllegalArgumentException("Liga no encontrada"));

        return matchRepository.findAll().stream()
                .filter(match -> match.getLeague().getId() == leagueId &&
                        (match.getHomeTeam().getId() == teamId || match.getAwayTeam().getId() == teamId))
                .collect(Collectors.toList());
    }

    private void validateTeamsInSameLeague(Team homeTeam, Team awayTeam, League league) {
        if (!league.getTeamList().contains(homeTeam) || !league.getTeamList().contains(awayTeam)) {
            throw new IllegalArgumentException("Ambos equipos deben pertenecer a la misma liga");
        }
    }
}
