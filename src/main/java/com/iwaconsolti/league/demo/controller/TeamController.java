package com.iwaconsolti.league.demo.controller;

import com.iwaconsolti.league.demo.model.Player;
import com.iwaconsolti.league.demo.model.Team;
import com.iwaconsolti.league.demo.service.TeamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leagues/{leagueId}/team")
@Slf4j
@RequiredArgsConstructor
public class TeamController {

    private static final Logger logger = LoggerFactory.getLogger(TeamController.class);
    private final TeamService teamService;


    @PostMapping
    public ResponseEntity<Team> createTeam(
            @PathVariable Long leagueId,
            @RequestBody Team team) {
        try {
            log.info("Creating team in league {}", leagueId);
            return ResponseEntity.ok(teamService.createTeam(leagueId, team));
        } catch (IllegalArgumentException | IllegalStateException e) {
            log.error("Error creating team: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Team>> getTeamsByLeague(@PathVariable Long leagueId) {
        return ResponseEntity.ok(teamService.getTeamsByLeague(leagueId));
    }

    @GetMapping("/{teamId}")
    public ResponseEntity<Team> getTeamById(
            @PathVariable Long leagueId,
            @PathVariable Long teamId) {
        return teamService.getTeamById(leagueId, teamId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{teamId}")
    public ResponseEntity<Team> updateTeam(
            @PathVariable Long leagueId,
            @PathVariable Long teamId,
            @RequestBody Team team) {
        try {
            return ResponseEntity.ok(teamService.updateTeam(leagueId, teamId, team));
        } catch (IllegalArgumentException e) {
            log.error("Error updating team: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}
