package com.iwaconsolti.league.controller;

import com.iwaconsolti.league.model.TeamModel;
import com.iwaconsolti.league.service.TeamService;
import lombok.RequiredArgsConstructor;
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
        return ResponseEntity.ok(service.getTeam());
    }
    @PostMapping("/")
    public TeamModel addTeam(@RequestBody TeamModel teammodel) {
        return service.insertTeam(teammodel );
    }
    @PutMapping("/{id}")
    public TeamModel putTeam(@PathVariable int id, @RequestBody TeamModel team) {
        return service.updateTeam(id, team);
    }
    @DeleteMapping("/{id}")
    public boolean DeletePlayer(@PathVariable int id) {
        return service.deleteTeam(id);
    }

}
