package com.iwaconsolti.league.manager.controller;

import com.iwaconsolti.league.manager.persistence.model.Matches;
import com.iwaconsolti.league.manager.persistence.model.Players;
import com.iwaconsolti.league.manager.persistence.model.Teams;
import com.iwaconsolti.league.manager.response.MatchesRequest;
import com.iwaconsolti.league.manager.response.PlayersRequest;
import com.iwaconsolti.league.manager.response.TeamsRequest;
import com.iwaconsolti.league.manager.service.ILeagues;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/leagues/{leagueType}")
@Slf4j
public class LeaguesController {

    private final ILeagues soccerLeagues;
    private final ILeagues baseBallLeague;

    public LeaguesController(@Qualifier("soccerLeague") ILeagues soccerLeague, @Qualifier("baseBallLeague") ILeagues baseBallLeague) {
        this.soccerLeagues = soccerLeague;
        this.baseBallLeague = baseBallLeague;
        log.info("Default LeaguesController created");
    }

    @PostMapping("/team")
    public ResponseEntity<Teams> createTeam(@PathVariable String leagueType, @RequestBody TeamsRequest newTeam) {
        if (leagueType.equals("soccer")) {
            return ResponseEntity.ok(soccerLeagues.saveTeams(leagueType,newTeam));
        } else if (leagueType.equals("baseball")) {
            return ResponseEntity.ok(baseBallLeague.saveTeams(leagueType,newTeam));
        } else {
            log.warn("League not found");
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/player")
    public ResponseEntity<Players> createPlayer(@PathVariable String leagueType, @RequestBody PlayersRequest newPlayer) {
        if (leagueType.equals("soccer")) {
            return ResponseEntity.ok(soccerLeagues.savePlayers(leagueType, newPlayer));
        } else if (leagueType.equals("baseball")) {
            return ResponseEntity.ok(baseBallLeague.savePlayers(leagueType,newPlayer));
        } else {
            log.warn("League not found");
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/match")
    public ResponseEntity<Matches> createMatch(@PathVariable String leagueType, @RequestBody MatchesRequest newMatch) {
        if (leagueType.equals("soccer")) {
            return ResponseEntity.ok(soccerLeagues.saveMatches(leagueType,newMatch));
        } else if (leagueType.equals("baseball")) {
            return ResponseEntity.ok(baseBallLeague.saveMatches(leagueType,newMatch));
        } else {
            log.warn("League not found");
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/team")
    public ResponseEntity<List<TeamsRequest>> getAllTeams(@PathVariable String leagueType) {
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
    public ResponseEntity<List<PlayersRequest>> getPlayersByTeam(@PathVariable String leagueType, @PathVariable int teamId) {
        if (leagueType.equalsIgnoreCase("soccer")) {
            return ResponseEntity.ok(soccerLeagues.getPlayersTeam(leagueType,teamId));
        } else if (leagueType.equalsIgnoreCase("baseball")) {
            return ResponseEntity.ok(baseBallLeague.getPlayersTeam(leagueType,teamId));
        } else {
            log.warn("League not found");
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/match/{teamId}")
    public ResponseEntity<List<MatchesRequest>> getMatchesByTeam(@PathVariable String leagueType, @PathVariable int teamId) {
        if (leagueType.equalsIgnoreCase("soccer")) {
            return ResponseEntity.ok(soccerLeagues.getMatchesByTeam(leagueType,teamId, teamId));
        } else if (leagueType.equalsIgnoreCase("baseball")) {
            return ResponseEntity.ok(baseBallLeague.getMatchesByTeam(leagueType,teamId, teamId));
        } else {
            log.warn("League not found");
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/player/{id}")
    public ResponseEntity<Players> updatePlayer(@PathVariable String leagueType, @PathVariable int id, @RequestBody Players newPlayer) {
        if (leagueType.equalsIgnoreCase("soccer")) {
            return ResponseEntity.ok(soccerLeagues.updatePlayer(leagueType,id, newPlayer));
        } else if (leagueType.equalsIgnoreCase("baseball")) {
            return ResponseEntity.ok(baseBallLeague.updatePlayer(leagueType,id, newPlayer));
        } else {
            log.warn("League not found");
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/team/{id}")
    public ResponseEntity<Teams> updateTeam(@PathVariable String leagueType, @PathVariable int id, @RequestBody Teams newTeam) {
        if (leagueType.equalsIgnoreCase("soccer")) {
            return ResponseEntity.ok(soccerLeagues.updateTeam(leagueType,id, newTeam));
        } else if (leagueType.equalsIgnoreCase("baseball")) {
            return ResponseEntity.ok(baseBallLeague.updateTeam(leagueType,id, newTeam));
        } else {
            log.warn("League not found");
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/player/{teamId}")
    public ResponseEntity<Teams> deletePlayer(@PathVariable String leagueType, @PathVariable int teamId) {
        if (leagueType.equalsIgnoreCase("soccer")) {
            return ResponseEntity.ok(soccerLeagues.deletePlayersTeam(leagueType,teamId));
        } else if (leagueType.equalsIgnoreCase("baseball")) {
            return ResponseEntity.ok(baseBallLeague.deletePlayersTeam(leagueType,teamId));
        } else {
            log.warn("League not found");
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/matches")
    public ResponseEntity<String> deleteMatch(@PathVariable String leagueType) {
        if (leagueType.equalsIgnoreCase("soccer")) {
            soccerLeagues.deleteAllMatches();
            return ResponseEntity.ok("All matches from Soccer league have been deleted.");
        } else if (leagueType.equalsIgnoreCase("baseball")) {
            baseBallLeague.deleteAllMatches();
            return ResponseEntity.ok("All matches from Baseball league have been deleted.");
        } else {
            log.warn("League not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("League not found.");
        }
    }

}


