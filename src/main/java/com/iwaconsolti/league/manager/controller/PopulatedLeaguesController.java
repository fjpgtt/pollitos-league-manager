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

import java.util.ArrayList;
import java.util.List;

@Profile("populated")
@RestController
@RequestMapping(value = "/baseBall")
@Slf4j
public class PopulatedLeaguesController {

    private ILeagues soccerLeagues;
    private ILeagues baseBallLeague;

    public PopulatedLeaguesController(@Qualifier("soccerLeague") ILeagues soccerLeague, @Qualifier("baseBallLeague") ILeagues baseBallLeague) {
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

    @PostConstruct
    public void postConstruct() {
        Players player1 = new Players(1L, "Maradona", 1L);
        Players player2 = new Players(2L, "Bob", 2L);
        Players player3 = new Players(3L, "Rene", 1L);

        soccerLeagues.savePlayers(player1);
        baseBallLeague.savePlayers(player1);
        log.info("Player created, name: {}", player1.getName());
        soccerLeagues.savePlayers(player2);
        baseBallLeague.savePlayers(player2);
        log.info("Player created, name: {}", player2.getName());
        soccerLeagues.savePlayers(player3);
        baseBallLeague.savePlayers(player3);
        log.info("Player created, name: {}", player3.getName());

        List<Players> players = new ArrayList<>();
        players.add(player1);
        players.add(player3);
        List<Players> players2 = new ArrayList<>();
        players2.add(player2);

        Teams team1 = new Teams(1L, "Los Atlas", players);
        Teams team2 = new Teams(2L, "Equipo Maravilla", players2);

        soccerLeagues.saveTeams(team1);
        baseBallLeague.saveTeams(team1);
        log.info("Team created, name: {}", team1.getName());
        soccerLeagues.saveTeams(team2);
        baseBallLeague.saveTeams(team2);
        log.info("Team created, name: {}", team2.getName());
    }

}
