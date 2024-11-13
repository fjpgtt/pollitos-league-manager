package com.iwaconsolti.league.manager.controller;

import com.iwaconsolti.league.manager.model.Matches;
import com.iwaconsolti.league.manager.model.Players;
import com.iwaconsolti.league.manager.model.Teams;
import com.iwaconsolti.league.manager.service.ILeagues;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Profile("populated")
@RestController
@RequestMapping(value = "/leaguesP/{leagueType}")
@Slf4j
public class PopulatedLeaguesController {

    private ILeagues soccerLeagues;
    private ILeagues baseBallLeague;

    public PopulatedLeaguesController(@Qualifier("soccerLeague") ILeagues soccerLeague, @Qualifier("baseBallLeague") ILeagues baseBallLeague) {
        this.soccerLeagues = soccerLeague;
        this.baseBallLeague = baseBallLeague;
        log.info("Populated LeaguesController created");
    }

    @PostMapping("/team")
    public ResponseEntity<Teams> createTeam(@PathVariable String leagueType, @RequestBody Teams newTeam) {
        if (leagueType.equals("soccer")) {
            Teams teamSoccer = soccerLeagues.saveTeams(newTeam);
            log.info("Team created");
            return ResponseEntity.ok(teamSoccer);
        } else {
            Teams teamBase = baseBallLeague.saveTeams(newTeam);
            return ResponseEntity.ok(teamBase);
        }
    }

    @PostMapping("/player")
    public ResponseEntity<Players> createPlayer(@PathVariable String leagueType, @RequestBody Players newPlayer) {
        if (leagueType.equals("soccer")) {
            Players playerSoccer = soccerLeagues.savePlayers(newPlayer);
            log.info("Player created");
            return ResponseEntity.ok(playerSoccer);
        } else {
            Players playerBase = baseBallLeague.savePlayers(newPlayer);
            return ResponseEntity.ok(playerBase);
        }
    }

    @PostMapping("/match")
    public ResponseEntity<Matches> createMatch(@PathVariable String leagueType, @RequestBody Matches newMatch) {
        if (leagueType.equals("soccer")) {
            Matches matchSoccer = soccerLeagues.saveMatches(newMatch);
            return ResponseEntity.ok(matchSoccer);
        } else {
            Matches matchBase = baseBallLeague.saveMatches(newMatch);
            return ResponseEntity.ok(matchBase);
        }
    }

    @GetMapping("/team")
    public ResponseEntity<List<Teams>> getAllTeams(@PathVariable String leagueType) {
        if (leagueType.equalsIgnoreCase("soccer")) {
            return ResponseEntity.ok(soccerLeagues.getAllTeams());
        } else {
            return ResponseEntity.ok(baseBallLeague.getAllTeams());
        }
    }

    @GetMapping("/player/{teamId}")
    public ResponseEntity<List<Players>> getPlayer(@PathVariable String leagueType, @PathVariable int teamId) {
        if (leagueType.equalsIgnoreCase("soccer")) {
            return ResponseEntity.ok(soccerLeagues.getPlayersTeam(teamId));
        } else {
            return ResponseEntity.ok(baseBallLeague.getPlayersTeam(teamId));
        }
    }

    @GetMapping("/match/{teamName}")
    public ResponseEntity<List<Matches>> getMatch(@PathVariable String leagueType, @PathVariable String teamName) {
        if (leagueType.equalsIgnoreCase("soccer")) {
            return ResponseEntity.ok(soccerLeagues.getMatchesTeam(teamName));
        } else {
            return ResponseEntity.ok(baseBallLeague.getMatchesTeam(teamName));
        }
    }

    @PutMapping("/player/{id}")
    public ResponseEntity<Players> updatePlayer(@PathVariable String leagueType, @PathVariable int id, @RequestBody Players newPlayer) {
        if (leagueType.equalsIgnoreCase("soccer")) {
            return ResponseEntity.ok(soccerLeagues.updatePlayer(id, newPlayer));
        } else {
            return ResponseEntity.ok(baseBallLeague.updatePlayer(id, newPlayer));
        }
    }

    @PutMapping("/team/{id}")
    public ResponseEntity<Teams> updateTeam(@PathVariable String leagueType, @PathVariable int id, @RequestBody Teams newTeam) {
        if (leagueType.equalsIgnoreCase("soccer")) {
            return ResponseEntity.ok(soccerLeagues.updateTeam(id, newTeam));
        } else {
            return ResponseEntity.ok(baseBallLeague.updateTeam(id, newTeam));
        }
    }

    @DeleteMapping("/player/{teamId}")
    public ResponseEntity<Teams> deletePlayer(@PathVariable String leagueType, @PathVariable int teamId) {
        if (leagueType.equalsIgnoreCase("soccer")) {
            return ResponseEntity.ok(soccerLeagues.deletePlayersTeam(teamId));
        } else {
            return ResponseEntity.ok(baseBallLeague.deletePlayersTeam(teamId));
        }
    }

    @DeleteMapping("/matches")
    public ResponseEntity<List<Matches>> deleteMatch(@PathVariable String leagueType) {
        if (leagueType.equalsIgnoreCase("soccer")) {
            return ResponseEntity.ok(soccerLeagues.deleteAllMatches());
        } else {
            return ResponseEntity.ok(baseBallLeague.deleteAllMatches());
        }
    }

    @PostConstruct
    public void postConstruct() {

        Teams team1 = new Teams("Los Atlas");
        Teams team2 = new Teams("Equipo Maravilla");

        soccerLeagues.saveTeams(team1);
        soccerLeagues.saveTeams(team2);

        Teams team3 = new Teams("Dogers");
        Teams team4 = new Teams("Yankis");

        baseBallLeague.saveTeams(team3);
        baseBallLeague.saveTeams(team4);

        log.info("Teams Leagues created");

        Players player1 = new Players("Maradona", team1.getId());
        Players player2 = new Players("Leonel Messi", team1.getId());
        Players player3 = new Players("Cristiano Ronaldo", team2.getId());
        Players player4 = new Players("Piolin", team2.getId());

        soccerLeagues.savePlayers(player1);
        soccerLeagues.savePlayers(player2);
        soccerLeagues.savePlayers(player3);
        soccerLeagues.savePlayers(player4);

        Players player5 = new Players("Valenzuela", team3.getId());
        Players player6 = new Players("Terrenator", team3.getId());
        Players player7 = new Players("Fiera", team3.getId());
        Players player8 = new Players("Nakamura", team3.getId());

        baseBallLeague.savePlayers(player5);
        baseBallLeague.savePlayers(player6);
        baseBallLeague.savePlayers(player7);
        baseBallLeague.savePlayers(player8);

        log.info("Players Leagues created");

    }

}
