package com.iwaconsolti.league.demo.controller;

import com.fasterxml.jackson.databind.deser.DataFormatReaders;
import com.iwaconsolti.league.demo.model.Matches;
import com.iwaconsolti.league.demo.service.MatchService;
import com.iwaconsolti.league.demo.service.PlayerService;
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
public class MatchContoller {
    private static final Logger logger = LoggerFactory.getLogger(PlayerController.class);
    private final MatchService matchService;

    @PostMapping
    public ResponseEntity<Matches> createMatch(
            @PathVariable Long leagueId,
            @RequestBody Matches match) {
        try {
            log.info("Creating match in league {}", leagueId);
            return ResponseEntity.ok(matchService.createMatch(leagueId, match));
        } catch (IllegalArgumentException e) {
            log.error("Error creating match: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllMatches(@PathVariable Long leagueId) {
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
    public ResponseEntity<List<Matches>> getMatchesByTeam(
            @PathVariable Long leagueId,
            @PathVariable Long teamId) {
        try {
            log.info("Getting matches for team {} in league {}", teamId, leagueId);
            List<Matches> teamMatches = matchService.getMatchesByTeam(leagueId, teamId);
            return ResponseEntity.ok(teamMatches);
        } catch (IllegalArgumentException e) {
            log.error("Error getting matches: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}
