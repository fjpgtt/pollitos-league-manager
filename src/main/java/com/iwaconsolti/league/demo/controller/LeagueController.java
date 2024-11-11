package com.iwaconsolti.league.demo.controller;

import com.iwaconsolti.league.demo.model.League;
import com.iwaconsolti.league.demo.service.LeagueService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leagues")
@Slf4j
@RequiredArgsConstructor
public class LeagueController {
    private static final Logger logger = LoggerFactory.getLogger(LeagueController.class);

    private final LeagueService leagueService;

    @PostMapping
    public ResponseEntity<League> createLeague(@RequestBody League league) {
        logger.info("Creating new league: {}", league);
        return ResponseEntity.ok(leagueService.createLeague(league));
    }

    @GetMapping
    public ResponseEntity<List<League>> getAllLeagues() {
        logger.info("Get all leagues");
        return ResponseEntity.ok(leagueService.getAllLeagues());
    }

    @GetMapping("/{id}")
    public ResponseEntity<League> getLeagueById(@PathVariable Long id) {
        logger.info("Get league by id: {}", id);
        return leagueService.getLeagueById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
