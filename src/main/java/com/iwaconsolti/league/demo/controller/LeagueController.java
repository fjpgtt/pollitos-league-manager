package com.iwaconsolti.league.demo.controller;

import com.iwaconsolti.league.demo.dto.request.LeagueCreateRequest;
import com.iwaconsolti.league.demo.dto.response.LeagueResponse;
import com.iwaconsolti.league.demo.dto.response.TeamSummaryResponse;
import com.iwaconsolti.league.demo.dto.response.MatchSummaryResponse;
import com.iwaconsolti.league.demo.model.League;
import com.iwaconsolti.league.demo.service.LeagueService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/leagues")
@Slf4j
@RequiredArgsConstructor
public class LeagueController {
    private final LeagueService leagueService;

    @PostMapping
    public ResponseEntity<LeagueResponse> createLeague(@RequestBody LeagueCreateRequest request) {
        log.info("Creating new league: {}", request);

        League league = new League();
        league.setName(request.name());
        league.setType(request.type());
        league.setMaxTeams(request.maxTeams());

        League createdLeague = leagueService.createLeague(league);
        return ResponseEntity.ok(convertToResponse(createdLeague));
    }

    @GetMapping
    public ResponseEntity<List<LeagueResponse>> findAllLeagues() {
        log.info("Finding all leagues");
        List<LeagueResponse> leagueResponseList = leagueService.findAllLeagues()
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(leagueResponseList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LeagueResponse> findLeagueById(@PathVariable long id) {
        log.info("Finding league by id: {}", id);
        return leagueService.findLeagueById(id)
                .map(league -> ResponseEntity.ok(convertToResponse(league)))
                .orElse(ResponseEntity.notFound().build());
    }

    private LeagueResponse convertToResponse(League league) {
        List<TeamSummaryResponse> teamSummaryList = league.getTeamList()
                .stream()
                .map(team -> new TeamSummaryResponse(
                        team.getId(),
                        team.getName()
                ))
                .collect(Collectors.toList());

        List<MatchSummaryResponse> matchSummaryList = league.getMatchList()
                .stream()
                .map(match -> new MatchSummaryResponse(
                        match.getId(),
                        new TeamSummaryResponse(match.getHomeTeam().getId(), match.getHomeTeam().getName()),
                        new TeamSummaryResponse(match.getAwayTeam().getId(), match.getAwayTeam().getName()),
                        match.getHomeScore(),
                        match.getAwayScore()
                ))
                .collect(Collectors.toList());

        return new LeagueResponse(
                league.getId(),
                league.getName(),
                league.getType(),
                league.getMaxTeams(),
                teamSummaryList,
                matchSummaryList
        );
    }
}