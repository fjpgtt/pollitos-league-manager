package com.iwaconsolti.league.manager.controller;

import com.iwaconsolti.league.manager.model.Teams;
import com.iwaconsolti.league.manager.service.ILeagues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/soccer")
public class SoccerLeagueController {

    private final ILeagues soccerLeagues;

    @Autowired
    public SoccerLeagueController(ILeagues soccerLeagues) {
        this.soccerLeagues = soccerLeagues;
    }

    @PostMapping("/teams")
    public ResponseEntity<Teams> createTeam(@RequestBody Teams newTeam) {
        Teams team = soccerLeagues.saveTeams(newTeam);
        return ResponseEntity.ok(team);
    }
}


