package com.iwaconsolti.league.demo.controller;

import com.iwaconsolti.league.demo.dto.request.MatchCreateRequest;
import com.iwaconsolti.league.demo.dto.response.MatchResponse;
import com.iwaconsolti.league.demo.dto.response.TeamSummaryResponse;
import com.iwaconsolti.league.demo.model.League;
import com.iwaconsolti.league.demo.model.Match;
import com.iwaconsolti.league.demo.model.Team;
import com.iwaconsolti.league.demo.service.LeagueService;
import com.iwaconsolti.league.demo.service.MatchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/leagues/{leagueId}/matches")
@Slf4j
@RequiredArgsConstructor
public class MatchController {
    private final MatchService matchService;
    private final LeagueService leagueService;

    @PostMapping
    public ResponseEntity<MatchResponse> createMatch(
            @PathVariable long leagueId,
            @RequestBody MatchCreateRequest request) {
        try {
            log.info("Creating match in league {}", leagueId);

            League league = leagueService.findLeagueById(leagueId)
                    .orElseThrow(() -> new IllegalArgumentException("Liga no encontrada"));

            Team homeTeam = league.getTeamList().stream()
                    .filter(team -> team.getId() == request.homeTeamId())
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Equipo local no encontrado en la liga"));

            Team awayTeam = league.getTeamList().stream()
                    .filter(team -> team.getId() == request.awayTeamId())
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Equipo visitante no encontrado en la liga"));

            Match match = new Match();
            match.setHomeTeam(homeTeam);
            match.setAwayTeam(awayTeam);
            match.setHomeScore(request.homeScore());
            match.setAwayScore(request.awayScore());

            Match createdMatch = matchService.createMatch(leagueId, match);
            return ResponseEntity.ok(convertToResponse(createdMatch));
        } catch (IllegalArgumentException e) {
            log.error("Error creating match: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllMatches(@PathVariable long leagueId) {
        try {
            log.info("Deleting all matches from league {}", leagueId);
            matchService.deleteAllMatchesFromLeague(leagueId);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            log.error("Error deleting matches: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/teams/{teamId}")
    public ResponseEntity<List<MatchResponse>> findMatchesByTeam(
            @PathVariable long leagueId,
            @PathVariable long teamId) {
        try {
            log.info("Finding matches for team {} in league {}", teamId, leagueId);
            List<Match> matchList = matchService.findMatchesByTeam(leagueId, teamId);

            List<MatchResponse> responseList = matchList.stream()
                    .map(this::convertToResponse)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(responseList);
        } catch (IllegalArgumentException e) {
            log.error("Error finding matches: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    private MatchResponse convertToResponse(Match match) {
        TeamSummaryResponse homeTeamResponse = new TeamSummaryResponse(
                match.getHomeTeam().getId(),
                match.getHomeTeam().getName()
        );

        TeamSummaryResponse awayTeamResponse = new TeamSummaryResponse(
                match.getAwayTeam().getId(),
                match.getAwayTeam().getName()
        );

        return new MatchResponse(
                match.getId(),
                homeTeamResponse,
                awayTeamResponse,
                match.getHomeScore(),
                match.getAwayScore()
        );
    }
}