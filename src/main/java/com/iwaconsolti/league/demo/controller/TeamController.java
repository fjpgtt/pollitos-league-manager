package com.iwaconsolti.league.demo.controller;

import com.iwaconsolti.league.demo.model.Team;
import com.iwaconsolti.league.demo.service.TeamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/team")
@Slf4j
@RequiredArgsConstructor
public class TeamController {

    private static final Logger logger = LoggerFactory.getLogger(TeamController.class);
    private final TeamService teamService;


    @PostMapping("/")
    public ResponseEntity<?> addPlayer(@RequestBody final Team team){
        logger.info("Init new team {}", team);
        Team created = teamService.addTeam(team);
        logger.info("Finish: create new team {}", team);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> getTeamByName(@PathVariable String name){
        logger.info("Get team by name {}", name);
        return teamService.getTeamByName(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTeamById(@PathVariable Long id){
        logger.info("Get team by id {}", id);
        return teamService.getTeamById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
