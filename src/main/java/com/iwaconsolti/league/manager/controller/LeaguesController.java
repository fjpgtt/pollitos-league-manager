package com.iwaconsolti.league.manager.controller;

import com.iwaconsolti.league.manager.exceptions.LeagueNotFoundException;
import com.iwaconsolti.league.manager.persistence.model.Matches;
import com.iwaconsolti.league.manager.persistence.model.Players;
import com.iwaconsolti.league.manager.persistence.model.Teams;
import com.iwaconsolti.league.manager.response.MatchesRequest;
import com.iwaconsolti.league.manager.response.PlayersRequest;
import com.iwaconsolti.league.manager.response.TeamsRequest;
import com.iwaconsolti.league.manager.service.ILeagues;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/leagues/{leagueType}")
@Slf4j
public class LeaguesController {

    private final ILeagues soccerLeague;
    private final ILeagues baseBallLeague;

    public LeaguesController(@Qualifier("soccerLeague") ILeagues soccerLeague, @Qualifier("baseBallLeague") ILeagues baseBallLeague) {
        this.soccerLeague = soccerLeague;
        this.baseBallLeague = baseBallLeague;
        log.info("Default LeaguesController created");
    }

    @PostMapping("/team")
    public ResponseEntity<Teams> createTeam(@PathVariable String leagueType, @RequestBody TeamsRequest newTeam) {
        if (leagueType.equals("soccer")) {
            return ResponseEntity.ok(soccerLeague.saveTeams(leagueType, newTeam));
        } else if (leagueType.equals("baseball")) {
            return ResponseEntity.ok(baseBallLeague.saveTeams(leagueType, newTeam));
        } else {
            throw new LeagueNotFoundException("League not found");
        }
    }

    @PostMapping("/player")
    public ResponseEntity<Players> createPlayer(@PathVariable String leagueType, @RequestBody PlayersRequest newPlayer) {
        if (leagueType.equals("soccer")) {
            return ResponseEntity.ok(soccerLeague.savePlayers(leagueType, newPlayer));
        } else if (leagueType.equals("baseball")) {
            return ResponseEntity.ok(baseBallLeague.savePlayers(leagueType, newPlayer));
        } else {
            throw new LeagueNotFoundException("League not found");
        }
    }

    @PostMapping("/match")
    public ResponseEntity<Matches> createMatch(@PathVariable String leagueType, @RequestBody MatchesRequest newMatch) {
        if (leagueType.equals("soccer")) {
            return ResponseEntity.ok(soccerLeague.saveMatches(leagueType, newMatch));
        } else if (leagueType.equals("baseball")) {
            return ResponseEntity.ok(baseBallLeague.saveMatches(leagueType, newMatch));
        } else {
            throw new LeagueNotFoundException("League not found");
        }
    }

    @GetMapping("/team")
    public ResponseEntity<List<TeamsRequest>> getAllTeams(@PathVariable String leagueType) {
        if (leagueType.equalsIgnoreCase("soccer")) {
            return ResponseEntity.ok(soccerLeague.getAllTeams(leagueType));
        } else if (leagueType.equalsIgnoreCase("baseball")) {
            return ResponseEntity.ok(baseBallLeague.getAllTeams(leagueType));
        } else {
            throw new LeagueNotFoundException("League not found");
        }
    }

    @GetMapping("/team/{teamId}")
    public ResponseEntity<TeamsRequest> getTeamById(@PathVariable String leagueType, @PathVariable int teamId) {
        if (leagueType.equalsIgnoreCase("soccer")) {
            return ResponseEntity.ok(soccerLeague.getTeamById(leagueType, teamId));
        } else if (leagueType.equalsIgnoreCase("baseball")) {
            return ResponseEntity.ok(baseBallLeague.getTeamById(leagueType, teamId));
        } else {
            throw new LeagueNotFoundException("League not found");
        }
    }

    @GetMapping("/player/{playerId}")
    public ResponseEntity<PlayersRequest> getPlayerById(@PathVariable String leagueType, @PathVariable int playerId) {
        if (leagueType.equalsIgnoreCase("soccer")) {
            return ResponseEntity.ok(soccerLeague.getPlayerById(leagueType, playerId));
        } else if (leagueType.equalsIgnoreCase("baseball")) {
            return ResponseEntity.ok(baseBallLeague.getPlayerById(leagueType, playerId));
        } else {
            throw new LeagueNotFoundException("League not found");
        }
    }

    @GetMapping("/players/{teamId}")
    public ResponseEntity<List<PlayersRequest>> getPlayersByTeam(@PathVariable String leagueType, @PathVariable int teamId) {
        if (leagueType.equalsIgnoreCase("soccer")) {
            return ResponseEntity.ok(soccerLeague.getPlayersTeam(leagueType, teamId));
        } else if (leagueType.equalsIgnoreCase("baseball")) {
            return ResponseEntity.ok(baseBallLeague.getPlayersTeam(leagueType, teamId));
        } else {
            throw new LeagueNotFoundException("League not found");
        }
    }

    @GetMapping("/match/{teamId}")
    public ResponseEntity<List<MatchesRequest>> getMatchesByTeam(@PathVariable String leagueType, @PathVariable int teamId) {
        if (leagueType.equalsIgnoreCase("soccer")) {
            return ResponseEntity.ok(soccerLeague.getMatchesByTeam(leagueType, teamId, teamId));
        } else if (leagueType.equalsIgnoreCase("baseball")) {
            return ResponseEntity.ok(baseBallLeague.getMatchesByTeam(leagueType, teamId, teamId));
        } else {
            throw new LeagueNotFoundException("League not found");
        }
    }

    @PutMapping("/player/{id}")
    public ResponseEntity<Players> updatePlayer(@PathVariable String leagueType, @PathVariable int id, @RequestBody Players newPlayer) {
        if (leagueType.equalsIgnoreCase("soccer")) {
            return ResponseEntity.ok(soccerLeague.updatePlayer(leagueType, id, newPlayer));
        } else if (leagueType.equalsIgnoreCase("baseball")) {
            return ResponseEntity.ok(baseBallLeague.updatePlayer(leagueType, id, newPlayer));
        } else {
            throw new LeagueNotFoundException("League not found");
        }
    }

    @PutMapping("/team/{id}")
    public ResponseEntity<Teams> updateTeam(@PathVariable String leagueType, @PathVariable int id, @RequestBody Teams newTeam) {
        if (leagueType.equalsIgnoreCase("soccer")) {
            return ResponseEntity.ok(soccerLeague.updateTeam(leagueType, id, newTeam));
        } else if (leagueType.equalsIgnoreCase("baseball")) {
            return ResponseEntity.ok(baseBallLeague.updateTeam(leagueType, id, newTeam));
        } else {
            throw new LeagueNotFoundException("League not found");
        }
    }

    @DeleteMapping("/player/{teamId}")
    public ResponseEntity<Teams> deletePlayer(@PathVariable String leagueType, @PathVariable int teamId) {
        if (leagueType.equalsIgnoreCase("soccer")) {
            return ResponseEntity.ok(soccerLeague.deletePlayersByTeam(leagueType, teamId));
        } else if (leagueType.equalsIgnoreCase("baseball")) {
            return ResponseEntity.ok(baseBallLeague.deletePlayersByTeam(leagueType, teamId));
        } else {
            throw new LeagueNotFoundException("League not found");
        }
    }

    @DeleteMapping("/matches")
    public ResponseEntity<String> deleteMatch(@PathVariable String leagueType) {
        if (leagueType.equalsIgnoreCase("soccer")) {
            soccerLeague.deleteAllMatches(leagueType);
        } else if (leagueType.equalsIgnoreCase("baseball")) {
            baseBallLeague.deleteAllMatches(leagueType);
        } else {
            throw new LeagueNotFoundException("League not found");
        }
        return ResponseEntity.ok("All matches from" + leagueType + " league have been deleted.");
    }

}


