package com.iwaconsolti.league.demo.controller;

import com.iwaconsolti.league.demo.dto.request.PlayerCreateRequest;
import com.iwaconsolti.league.demo.dto.response.PlayerResponse;
import com.iwaconsolti.league.demo.dto.response.TeamSummaryResponse;
import com.iwaconsolti.league.demo.model.Player;
import com.iwaconsolti.league.demo.service.PlayerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/leagues/{leagueId}/teams/{teamId}/players")
@Slf4j
@RequiredArgsConstructor
public class PlayerController {
    private final PlayerService playerService;

    @PostMapping
    public ResponseEntity<PlayerResponse> createPlayer(
            @PathVariable long leagueId,
            @PathVariable long teamId,
            @RequestBody PlayerCreateRequest request) {
        try {
            log.info("Creating player in team {} of league {}", teamId, leagueId);
            Player player = new Player();
            player.setName(request.name());

            Player createdPlayer = playerService.createPlayer(leagueId, teamId, player);
            return ResponseEntity.ok(convertToResponse(createdPlayer));
        } catch (IllegalArgumentException e) {
            log.error("Error creating player: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<PlayerResponse>> findPlayersByTeam(
            @PathVariable long leagueId,
            @PathVariable long teamId) {
        log.info("Finding players for team {} in league {}", teamId, leagueId);
        List<PlayerResponse> playerResponseList = playerService.findPlayersByTeam(leagueId, teamId)
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(playerResponseList);
    }

    @PutMapping("/{playerId}")
    public ResponseEntity<PlayerResponse> updatePlayer(
            @PathVariable long leagueId,
            @PathVariable long teamId,
            @PathVariable long playerId,
            @RequestBody PlayerCreateRequest request) {
        try {
            Player player = new Player();
            player.setName(request.name());

            Player updatedPlayer = playerService.updatePlayer(leagueId, teamId, playerId, player);
            return ResponseEntity.ok(convertToResponse(updatedPlayer));
        } catch (IllegalArgumentException e) {
            log.error("Error updating player: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deletePlayersFromTeam(
            @PathVariable long leagueId,
            @PathVariable long teamId) {
        log.info("Deleting all players from team {} in league {}", teamId, leagueId);
        playerService.deletePlayersFromTeam(leagueId, teamId);
        return ResponseEntity.ok().build();
    }

    private PlayerResponse convertToResponse(Player player) {
        TeamSummaryResponse teamSummary = new TeamSummaryResponse(
                player.getTeam().getId(),
                player.getTeam().getName()
        );

        return new PlayerResponse(
                player.getId(),
                player.getName(),
                teamSummary
        );
    }
}
