package com.iwaconsolti.league.controller;

import com.iwaconsolti.league.model.TeamModel;
import com.iwaconsolti.league.service.TeamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Team")
@Slf4j

public class TeamController {
    private final TeamService service;

    @Autowired
    public TeamController(TeamService service) {
        this.service = service;
    }

    @GetMapping("/")
    public ResponseEntity<List<TeamModel>> getTeam(){
        log.info("enter");
        return ResponseEntity.ok(service.getAllTeams());
    }

    @GetMapping("/league{idLeague}")
    public ResponseEntity<List<TeamModel>> getTeambyLeague(@PathVariable Long idLeague){
        log.info("enter");
        return ResponseEntity.ok(service.getTeambyLeague(idLeague));
    }
    @PostMapping("/")
    public TeamModel addTeam(@RequestBody TeamModel teammodel) {
        return service.addTeam(teammodel );
    }

    @PutMapping("/{id}")
    public TeamModel putTeam(@PathVariable long id, @RequestBody TeamModel team) {
        return service.updateTeam(id, team);
    }

    @DeleteMapping("/{id}")
    public boolean DeletePlayer(@PathVariable long id) {
        return service.deleteAllPlayersByTeamId(id);
    }

}