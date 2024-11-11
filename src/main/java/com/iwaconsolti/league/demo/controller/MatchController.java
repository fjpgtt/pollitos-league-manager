package com.iwaconsolti.league.demo.controller;

import com.iwaconsolti.league.demo.model.Match;
import com.iwaconsolti.league.demo.service.MatchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leagues/{leagueId}/matches")
@Slf4j
@RequiredArgsConstructor
public class MatchController {
    private static final Logger logger = LoggerFactory.getLogger(MatchController.class);
    private final MatchService matchService;

    @PostMapping
    public ResponseEntity<Match> createMatch(
            @PathVariable Long leagueId,
            @RequestBody Match match) {
        try {
            logger.info("Creating match in league {}", leagueId);
            return ResponseEntity.ok(matchService.createMatch(leagueId, match));
        } catch (IllegalArgumentException e) {
            logger.error("Error creating match: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllMatches(@PathVariable Long leagueId) {
        try {
            logger.info("Deleting all matches from league {}", leagueId);
            matchService.deleteAllMatchesFromLeague(leagueId);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            logger.error("Error deleting matches: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/teams/{teamId}")
    public ResponseEntity<List<Match>> getMatchesByTeam(
            @PathVariable Long leagueId,
            @PathVariable Long teamId) {
        try {
            logger.info("Getting matches for team {} in league {}", teamId, leagueId);
            List<Match> teamMatches = matchService.getMatchesByTeam(leagueId, teamId);
            return ResponseEntity.ok(teamMatches);
        } catch (IllegalArgumentException e) {
            logger.error("Error getting matches: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}
