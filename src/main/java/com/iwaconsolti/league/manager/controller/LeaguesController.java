package com.iwaconsolti.league.manager.controller;

import com.iwaconsolti.league.manager.model.Matches;
import com.iwaconsolti.league.manager.model.Players;
import com.iwaconsolti.league.manager.model.Teams;
import com.iwaconsolti.league.manager.service.ILeagues;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/leagues")
@Slf4j
public class LeaguesController {

    private ILeagues soccerLeagues;
    private ILeagues baseBallLeague;

    public LeaguesController(@Qualifier("soccerLeague") ILeagues soccerLeague, @Qualifier("baseBallLeague") ILeagues baseBallLeague) {
        this.soccerLeagues = soccerLeague;
        this.baseBallLeague = baseBallLeague;
        log.info("LeaguesController created");
    }

    @PostMapping("/teams")
    public ResponseEntity<Teams> createTeam(@RequestParam String  league, @RequestBody Teams newTeam) {
        if(league.equals("soccer")) {
            Teams teamSoccer = soccerLeagues.saveTeams(newTeam);
            return ResponseEntity.ok(teamSoccer);
        }else{
            Teams teamBase = baseBallLeague.saveTeams(newTeam);
            return ResponseEntity.ok(teamBase);
        }
    }

    @PostMapping("/players")
    public ResponseEntity<Players> createPlayer(@RequestParam String  league, @RequestBody Players newPlayer) {
        if(league.equals("soccer")) {
            Players playerSoccer = baseBallLeague.savePlayers(newPlayer);
            return ResponseEntity.ok(playerSoccer);
        }else{
            Players playerBase = baseBallLeague.savePlayers(newPlayer);
            return ResponseEntity.ok(playerBase);
        }
    }

    @PostMapping("/matches")
    public ResponseEntity<Matches> createMatch(@RequestParam String  league, @RequestBody Matches newMatch) {
        if(league.equals("soccer")) {
            Matches matchSoccer = soccerLeagues.saveMatches(newMatch);
            return ResponseEntity.ok(matchSoccer);
        }else{
            Matches matchBase = baseBallLeague.saveMatches(newMatch);
            return ResponseEntity.ok(matchBase);
        }
    }

    @GetMapping("/teams")
    public ResponseEntity<List<Teams>> getAllTeams(@RequestParam String league) {
        if(league.equalsIgnoreCase("soccer")) {
            return ResponseEntity.ok(soccerLeagues.getAllTeams());
        }else{
            return ResponseEntity.ok(baseBallLeague.getAllTeams());
        }
    }

    @GetMapping("/players/{teamId}")
    public ResponseEntity<List<Players>> getPlayer(@RequestParam String  league, @PathVariable Long teamId) {
        if(league.equalsIgnoreCase("soccer")) {
            return ResponseEntity.ok(soccerLeagues.getPlayersTeam(teamId));
        }else{
            return ResponseEntity.ok(baseBallLeague.getPlayersTeam(teamId));
        }
    }

    @PutMapping("/players/{id}")
    public ResponseEntity<Players> updatePlayer(@RequestParam String  league, @PathVariable Long id, @RequestBody Players newPlayer) {
        if(league.equalsIgnoreCase("soccer")) {
            return ResponseEntity.ok(soccerLeagues.updatePlayer(id, newPlayer));
        }else{
            return ResponseEntity.ok(baseBallLeague.updatePlayer(id, newPlayer));
        }
    }
}


