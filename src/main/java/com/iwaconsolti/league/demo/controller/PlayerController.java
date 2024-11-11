package com.iwaconsolti.league.demo.controller;

import com.iwaconsolti.league.demo.model.Player;
import com.iwaconsolti.league.demo.model.Team;
import com.iwaconsolti.league.demo.service.PlayerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leagues/{leagueId}/teams/{teamId}/players")
@Slf4j
@RequiredArgsConstructor
public class PlayerController {

    private static final Logger logger = LoggerFactory.getLogger(PlayerController.class);

    private final PlayerService playerService;

    @PostMapping
    public ResponseEntity<Player> createPlayer(
            @PathVariable Long leagueId,
            @PathVariable Long teamId,
            @RequestBody Player player) {
        try {
            log.info("Creating player in team {} of league {}", teamId, leagueId);
            return ResponseEntity.ok(playerService.addPlayer(leagueId, teamId, player));
        } catch (IllegalArgumentException e) {
            log.error("Error creating player: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Player>> getPlayersByTeam(
            @PathVariable Long leagueId,
            @PathVariable Long teamId) {
        try {
            return ResponseEntity.ok(playerService.getPlayersByTeam(leagueId, teamId));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{playerId}")
    public ResponseEntity<Player> updatePlayer(
            @PathVariable Long leagueId,
            @PathVariable Long teamId,
            @PathVariable Long playerId,
            @RequestBody Player player) {
        try {
            return ResponseEntity.ok(playerService.updatePlayer(leagueId, teamId, playerId, player));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deletePlayersFromTeam(
            @PathVariable Long leagueId,
            @PathVariable Long teamId) {
        try {
            playerService.deletePlayersFromTeam(leagueId, teamId);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
