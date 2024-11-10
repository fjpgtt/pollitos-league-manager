package com.iwaconsolti.league.demo.controller;

import com.iwaconsolti.league.demo.model.Player;
import com.iwaconsolti.league.demo.repository.Team;
import com.iwaconsolti.league.demo.repository.TeamRepository;
import com.iwaconsolti.league.demo.service.DemoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/league")
public class LeagueController {
    //In controller we only communicate with the service
    private final DemoService demoService;

    public LeagueController(DemoService demoService) {
        this.demoService = demoService;
    }

    @PostMapping("/{leagueName}/team")
    public String createTeam(@PathVariable String leagueName, @RequestBody Team team) {
        demoService.addTeam(leagueName, team);
        return "Team added to " + leagueName;
    }

    @PostMapping("/{leagueName}/team/{teamName}/player")
    public String createPlayer(@PathVariable String leagueName, @PathVariable String teamName, @RequestBody Player player) {
        demoService.addPlayer(leagueName, teamName, player);
        return "Player added to team " + teamName + " in " + leagueName;
    }

    @GetMapping("/{leagueName}/teams")
    public List<Team> getTeams(@PathVariable String leagueName) {
        return demoService.getTeamsFromLeague(leagueName);
    }

    @GetMapping("/{leagueName}/team/{teamName}/players")
    public List<Player> getPlayers(@PathVariable String leagueName, @PathVariable String teamName) {
        return demoService.getPlayersFromTeam(leagueName, teamName);
    }

}
