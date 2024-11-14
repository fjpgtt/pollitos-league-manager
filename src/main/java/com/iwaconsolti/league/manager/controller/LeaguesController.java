package com.iwaconsolti.league.manager.controller;

import com.iwaconsolti.league.manager.model.Matches;
import com.iwaconsolti.league.manager.model.Players;
import com.iwaconsolti.league.manager.model.Teams;
import com.iwaconsolti.league.manager.service.ILeagues;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping(value = "/leagues/{leagueType}")
@Slf4j
public class LeaguesController {

    private ILeagues soccerLeagues;
    private ILeagues baseBallLeague;

    public LeaguesController(@Qualifier("soccerLeague") ILeagues soccerLeague, @Qualifier("baseBallLeague") ILeagues baseBallLeague) {
        this.soccerLeagues = soccerLeague;
        this.baseBallLeague = baseBallLeague;
        log.info("Default LeaguesController created");
    }

    @PostMapping("/team")
    public ResponseEntity<Teams> createTeam(@PathVariable String leagueType, @RequestBody Teams newTeam) {
        if (leagueType.equals("soccer")) {
            return ResponseEntity.ok(soccerLeagues.saveTeams(newTeam));
        } else if (leagueType.equals("baseball")) {
            return ResponseEntity.ok(baseBallLeague.saveTeams(newTeam));
        } else {
            log.warn("League not found");
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/player")
    public ResponseEntity<Players> createPlayer(@PathVariable String leagueType, @RequestBody Players newPlayer) {
        if (leagueType.equals("soccer")) {
            return ResponseEntity.ok(soccerLeagues.savePlayers(newPlayer));
        } else if (leagueType.equals("baseball")) {
            return ResponseEntity.ok(baseBallLeague.savePlayers(newPlayer));
        } else {
            log.warn("League not found");
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/match")
    public ResponseEntity<Matches> createMatch(@PathVariable String leagueType, @RequestBody Matches newMatch) {
        if (leagueType.equals("soccer")) {
            return ResponseEntity.ok(soccerLeagues.saveMatches(newMatch));
        } else if (leagueType.equals("baseball")) {
            return ResponseEntity.ok(baseBallLeague.saveMatches(newMatch));
        } else {
            log.warn("League not found");
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/team")
    public ResponseEntity<List<Teams>> getAllTeams(@PathVariable String leagueType) {
        if (leagueType.equalsIgnoreCase("soccer")) {
            return ResponseEntity.ok(soccerLeagues.getAllTeams());
        } else if (leagueType.equalsIgnoreCase("baseball")) {
            return ResponseEntity.ok(baseBallLeague.getAllTeams());
        } else {
            log.warn("League not found");
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/player/{teamId}")
    public ResponseEntity<List<Players>> getPlayerByTeam(@PathVariable String leagueType, @PathVariable int teamId) {
        if (leagueType.equalsIgnoreCase("soccer")) {
            return ResponseEntity.ok(soccerLeagues.getPlayersTeam(teamId));
        } else if (leagueType.equalsIgnoreCase("baseball")) {
            return ResponseEntity.ok(baseBallLeague.getPlayersTeam(teamId));
        } else {
            log.warn("League not found");
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/match/{teamName}")
    public ResponseEntity<List<Matches>> getMatchTeam(@PathVariable String leagueType, @PathVariable String teamName) {
        if (leagueType.equalsIgnoreCase("soccer")) {
            return ResponseEntity.ok(soccerLeagues.getMatchesTeam(teamName));
        } else if (leagueType.equalsIgnoreCase("baseball")) {
            return ResponseEntity.ok(baseBallLeague.getMatchesTeam(teamName));
        } else {
            log.warn("League not found");
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/player/{id}")
    public ResponseEntity<Players> updatePlayer(@PathVariable String leagueType, @PathVariable int id, @RequestBody Players newPlayer) {
        if (leagueType.equalsIgnoreCase("soccer")) {
            return ResponseEntity.ok(soccerLeagues.updatePlayer(id, newPlayer));
        } else if (leagueType.equalsIgnoreCase("baseball")) {
            return ResponseEntity.ok(baseBallLeague.updatePlayer(id, newPlayer));
        } else {
            log.warn("League not found");
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/team/{id}")
    public ResponseEntity<Teams> updateTeam(@PathVariable String leagueType, @PathVariable int id, @RequestBody Teams newTeam) {
        if (leagueType.equalsIgnoreCase("soccer")) {
            return ResponseEntity.ok(soccerLeagues.updateTeam(id, newTeam));
        } else if (leagueType.equalsIgnoreCase("baseball")) {
            return ResponseEntity.ok(baseBallLeague.updateTeam(id, newTeam));
        } else {
            log.warn("League not found");
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/player/{teamId}")
    public ResponseEntity<Teams> deletePlayer(@PathVariable String leagueType, @PathVariable int teamId) {
        if (leagueType.equalsIgnoreCase("soccer")) {
            return ResponseEntity.ok(soccerLeagues.deletePlayersTeam(teamId));
        } else if (leagueType.equalsIgnoreCase("baseball")) {
            return ResponseEntity.ok(baseBallLeague.deletePlayersTeam(teamId));
        } else {
            log.warn("League not found");
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/matches")
    public ResponseEntity<List<Matches>> deleteMatch(@PathVariable String leagueType) {
        if (leagueType.equalsIgnoreCase("soccer")) {
            return ResponseEntity.ok(soccerLeagues.deleteAllMatches());
        } else if (leagueType.equalsIgnoreCase("baseball")) {
            return ResponseEntity.ok(baseBallLeague.deleteAllMatches());
        } else {
            log.warn("League not found");
            return ResponseEntity.notFound().build();
        }
    }

}


