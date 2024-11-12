package com.iwaconsolti.league.demo.controller;

import com.iwaconsolti.league.demo.model.Player;
import com.iwaconsolti.league.demo.model.Team;

import com.iwaconsolti.league.demo.service.BasketLeague;
import com.iwaconsolti.league.demo.service.SoccerLeague;
import com.iwaconsolti.league.demo.service.ValidateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/league")
@Slf4j
public class LeagueController {
    //In controller we only communicate with the service
    private final BasketLeague basketLeague;
    private final SoccerLeague soccerLeague;
    private final ValidateService validateService;

    @Autowired
    public LeagueController(BasketLeague basketLeague, SoccerLeague soccerLeague, ValidateService validateService) {
        this.basketLeague = basketLeague;
        this.soccerLeague = soccerLeague;
        this.validateService = validateService;
    }

    @PostMapping("/{leagueName}/team")
    public String createTeam(@PathVariable String leagueName, @RequestBody Team team) {
        if ("basketleague".equalsIgnoreCase(leagueName)) {
            basketLeague.createTeam(team);
            log.info("League added to {}", leagueName);
            return "League added to " + leagueName;
        } else if ("soccerleague".equalsIgnoreCase(leagueName)) {
            soccerLeague.createTeam(team);
            log.info("Team added to {}", leagueName);
            return "Team added to " + leagueName;
        }
        log.error("League {} doesn't exists", leagueName);
        return "League doesnt exists: Try soccerleague or basketleague";
    }

    @PostMapping("/{leagueName}/{team}/player")
    public String createPlayer(@PathVariable String leagueName, @PathVariable String team, @RequestBody Player player) {
//Validate
        if (!validateService.leagueNameValidation(leagueName)) {
            log.error("Incorrect league");
            return "Please try with \"soccerleague\" or \"basketleague\"";
        }
//Validate
        if (!validateService.teamNameValidation(team, leagueName)) {
            log.error("Incorrect Team");
            return "Please try with \"team1\" or \"team2\"";
        }
//Creating zone
        if ("basketleague".equalsIgnoreCase(leagueName)) {
            basketLeague.createPlayer(player);
            log.info("Player added to {} in team {}", leagueName, team);
            return "Player added to " + leagueName;
        } else if ("soccerleague".equalsIgnoreCase(leagueName)) {
            soccerLeague.createPlayer(player);
            log.info("Player added to {} in team {}", leagueName, team);
            return "Player added to " + leagueName;
        }

        log.error("League {} doesn't exists", leagueName);
        return "League doesnt exists: Try soccerleague or basketleague";
    }

//
//    @PostMapping("/{leagueName}/team/player")
//    public String createMatch(@PathVariable String leagueName, @PathVariable String teamName, @RequestBody Player player) {
//        leagueService.addPlayer(leagueName, teamName, player);
//        return "Player added to team " + teamName + " in " + leagueName;
//    }
//
//    @GetMapping("/{leagueName}/teams")
//    public List<Team> getTeams(@PathVariable String leagueName) {
//        return leagueService.getTeamsFromLeague(leagueName);
//    }
//
//    @GetMapping("/{leagueName}/team/{teamName}/players")
//    public List<Player> getPlayers(@PathVariable String leagueName, @PathVariable String teamName) {
//        return leagueService.getPlayersFromTeam(leagueName, teamName);
//    }

}
