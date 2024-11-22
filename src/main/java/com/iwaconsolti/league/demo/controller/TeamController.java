package com.iwaconsolti.league.demo.controller;

import com.iwaconsolti.league.demo.dto.request.TeamCreateRequest;
import com.iwaconsolti.league.demo.dto.request.TeamUpdateRequest;
import com.iwaconsolti.league.demo.dto.response.PlayerResponse;
import com.iwaconsolti.league.demo.dto.response.TeamResponse;
import com.iwaconsolti.league.demo.dto.response.TeamSummaryResponse;
import com.iwaconsolti.league.demo.model.Team;
import com.iwaconsolti.league.demo.service.TeamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/leagues/{leagueId}/teams")
@Slf4j
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @PostMapping
    public ResponseEntity<TeamResponse> createTeam(
            @PathVariable long leagueId,
            @RequestBody TeamCreateRequest request) {
        try {
            log.info("Creating team in league {}", leagueId);
            Team team = new Team();
            team.setName(request.name());

            Team createdTeam = teamService.createTeam(leagueId, team);
            return ResponseEntity.ok(convertToResponse(createdTeam));
        } catch (IllegalArgumentException | IllegalStateException e) {
            log.error("Error creating team: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<TeamResponse>> findTeamsByLeague(@PathVariable long leagueId) {
        List<TeamResponse> teamResponseList = teamService.findTeamsByLeague(leagueId)
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(teamResponseList);
    }

    @GetMapping("/{teamId}")
    public ResponseEntity<TeamResponse> findTeamById(
            @PathVariable long leagueId,
            @PathVariable long teamId) {
        log.info("Find team by id {}", teamId);
        return teamService.findTeamById(leagueId, teamId)
                .map(team -> ResponseEntity.ok(convertToResponse(team)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{teamId}")
    public ResponseEntity<TeamResponse> updateTeam(
            @PathVariable long leagueId,
            @PathVariable long teamId,
            @RequestBody TeamUpdateRequest request) {
        try {
            Team updatedTeam = teamService.updateTeam(leagueId, teamId, request);
            return ResponseEntity.ok(convertToResponse(updatedTeam));
        } catch (IllegalArgumentException e) {
            log.error("Error updating team: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    private TeamResponse convertToResponse(Team team) {
        List<PlayerResponse> playerResponseList = team.getPlayerList()
                .stream()
                .map(player -> {
                    TeamSummaryResponse teamSummary = new TeamSummaryResponse(
                            team.getId(),
                            team.getName()
                    );

                    return new PlayerResponse(
                            player.getId(),
                            player.getName(),
                            teamSummary
                    );
                })
                .collect(Collectors.toList());

        return new TeamResponse(
                team.getId(),
                team.getName(),
                playerResponseList
        );
    }
}
