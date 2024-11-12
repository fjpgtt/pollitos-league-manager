package com.iwaconsolti.league.demo.controller;

import com.iwaconsolti.league.demo.model.Player;
import com.iwaconsolti.league.demo.model.Team;
import com.iwaconsolti.league.demo.model.Match;
import com.iwaconsolti.league.demo.service.BasketLeague;
import com.iwaconsolti.league.demo.service.SoccerLeague;
import com.iwaconsolti.league.demo.service.ValidateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/league")
@Slf4j
public class LeagueController {

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
            log.info("Team added to {}", leagueName);
            return "Team added to " + leagueName;
        } else if ("soccerleague".equalsIgnoreCase(leagueName)) {
            soccerLeague.createTeam(team);
            log.info("Team added to {}", leagueName);
            return "Team added to " + leagueName;
        }
        log.error("League {} doesn't exist", leagueName);
        return "League doesn't exist: Try soccerleague or basketleague";
    }

    @PostMapping("/{leagueName}/{team}/player")
    public String createPlayer(@PathVariable String leagueName, @PathVariable String team, @RequestBody Player player) {
        // Validate league
        if (!validateService.leagueNameValidation(leagueName)) {
            log.error("Incorrect league");
            return "Please try with \"soccerleague\" or \"basketleague\"";
        }
        // Validate team
        if (!validateService.teamNameValidation(team, leagueName)) {
            log.error("Incorrect Team");
            return "Please try with a valid team name";
        }
        // Creating player
        if ("basketleague".equalsIgnoreCase(leagueName)) {
            basketLeague.createPlayer(player);
            log.info("Player added to {} in team {}", leagueName, team);
            return "Player added to " + leagueName;
        } else if ("soccerleague".equalsIgnoreCase(leagueName)) {
            soccerLeague.createPlayer(player);
            log.info("Player added to {} in team {}", leagueName, team);
            return "Player added to " + leagueName;
        }

        log.error("League {} doesn't exist", leagueName);
        return "League doesn't exist: Try soccerleague or basketleague";
    }

    @PostMapping("/{leagueName}/match")
    public String createMatch(@PathVariable String leagueName, @RequestBody Match match) {
        if (!validateService.leagueNameValidation(leagueName)) {
            log.error("Incorrect league: {}", leagueName);
            return "Incorrect league " + leagueName;
        }

        if ("basketleague".equalsIgnoreCase(leagueName)) {
            basketLeague.createMatch(match.getTeam1(), match.getTeam2());
            log.info("Match created {} vs {} in {}", match.getTeam1().getName(), match.getTeam2().getName(), leagueName);
            return "Match created in " + leagueName;
        } else if ("soccerleague".equalsIgnoreCase(leagueName)) {
            soccerLeague.createMatch(match.getTeam1(), match.getTeam2());
            log.info("Match created {} vs {} in {}", match.getTeam1().getName(), match.getTeam2().getName(), leagueName);
            return "Match created in " + leagueName;
        }

        log.error("League {} doesn't exist", leagueName);
        return "League doesn't exist: Try soccerleague or basketleague";
    }

    @GetMapping("/{leagueName}/team")
    public List<Team> getAllTeams(@PathVariable String leagueName) {
        if (!validateService.leagueNameValidation(leagueName)) {
            log.error("Incorrect league: {}", leagueName);
            return new ArrayList<>();
        }

        if ("basketleague".equalsIgnoreCase(leagueName)) {
            return basketLeague.getTeams();
        } else if ("soccerleague".equalsIgnoreCase(leagueName)) {
            return soccerLeague.getTeams();
        }

        log.error("League {} doesn't exist", leagueName);
        return new ArrayList<>();
    }

    @GetMapping("/{leagueName}/team/{teamName}/players")
    public List<Player> getAllPlayers(@PathVariable String leagueName, @PathVariable String teamName) {
        if (!validateService.leagueNameValidation(leagueName)) {
            log.error("Incorrect league: {}", leagueName);
            return new ArrayList<>();
        }

        if ("basketleague".equalsIgnoreCase(leagueName)) {
            return basketLeague.getPlayers(teamName);
        } else if ("soccerleague".equalsIgnoreCase(leagueName)) {
            return soccerLeague.getPlayers(teamName);
        }
        log.error("League {} doesn't exist", leagueName);
        return new ArrayList<>();
    }

    @GetMapping("/{leagueName}/matches")
    public List<Match> getMatchesFromLeagues(@PathVariable String leagueName) {
        if (!validateService.leagueNameValidation(leagueName)) {
            log.error("Incorrect league: {}", leagueName);
            return new ArrayList<>();
        }

        if ("basketleague".equalsIgnoreCase(leagueName)) {
            return basketLeague.getMatches();
        } else if ("soccerleague".equalsIgnoreCase(leagueName)) {
            return soccerLeague.getMatches();
        }

        log.error("League {} doesn't exist", leagueName);
        return new ArrayList<>();
    }

    @PutMapping("/{leagueName}/{teamName}/player/{playerId}")
    public String editPlayer(@PathVariable String leagueName, @PathVariable String teamName, @PathVariable int playerId, @RequestBody Player player) {
        if (!validateService.leagueNameValidation(leagueName)) {
            log.error("Incorrect league: {}", leagueName);
            return "Incorrect league " + leagueName;
        }

        if (!validateService.teamNameValidation(teamName, leagueName)) {
            log.error("Incorrect Team {}", teamName);
            return "Please try with a valid team name";
        }

        if ("basketleague".equalsIgnoreCase(leagueName)) {
            basketLeague.editPlayer(playerId, player);
            log.info("Player info updated in {} and {}", leagueName, teamName);
            return "Player information updated in " + leagueName + " and " + teamName;
        } else if ("soccerleague".equalsIgnoreCase(leagueName)) {
            soccerLeague.editPlayer(playerId, player);
            log.info("Player info updated in {} and {}", leagueName, teamName);
            return "Player information updated in " + leagueName + " and " + teamName;
        }

        log.error("League {} doesn't exist", leagueName);
        return "League doesn't exist: Try soccerleague or basketleague";
    }

    @PutMapping("/{leagueName}/{teamID}/team")
    public String editTeam(@PathVariable String leagueName, @PathVariable String teamName, @RequestBody Team newteam) {
        if (!validateService.leagueNameValidation(leagueName)) {
            log.error("Incorrect league: {}", leagueName);
            return "Incorrect league " + leagueName;
        }

        if (!validateService.teamNameValidation(teamName, leagueName)) {
            log.error("Incorrect Team {}", teamName);
            return "Please try with a valid team name";
        }

        if ("basketleague".equalsIgnoreCase(leagueName)) {
            basketLeague.editTeam(teamName, newteam);
            log.info("Team info updated in {} changed to {}", teamName, newteam);
            return "Team information updated in " + leagueName + " and " + teamName;
        } else if ("soccerleague".equalsIgnoreCase(leagueName)) {
            soccerLeague.editTeam(teamName, newteam);
            log.info("Team info updated in {} changed to {}", teamName, newteam);
            return "Team information updated in " + leagueName + " and " + teamName;
        }

        log.error("League {} doesn't exist", leagueName);
        return "League doesn't exist: Try soccerleague or basketleague";
    }

    @DeleteMapping("/{leagueName}/delete/matches")
    public String deleteAllMatches(@PathVariable String leagueName) {
        if (!validateService.leagueNameValidation(leagueName)) {
            log.error("Incorrect league: {}", leagueName);
            return "Incorrect league: " + leagueName;
        }

        if ("basketleague".equalsIgnoreCase(leagueName)) {
            basketLeague.deleteAllMatches();
            log.info("All matches deleted from {}", leagueName);
            return "All matches deleted from " + leagueName;
        } else if ("soccerleague".equalsIgnoreCase(leagueName)) {
            soccerLeague.deleteAllMatches();
            log.info("All matches deleted from {}", leagueName);
            return "All matches deleted from " + leagueName;
        }

        log.error("League {} doesn't exist", leagueName);
        return "League doesn't exist: Try soccerleague or basketleague";
    }

    @DeleteMapping("/{leagueName}/team/{teamName}/player/{playerId}")
    public String deletePlayer(@PathVariable String leagueName, @PathVariable String teamName, @PathVariable int playerId) {
        if (!validateService.leagueNameValidation(leagueName)) {
            log.error("Incorrect league: {}", leagueName);
            return "Incorrect league: " + leagueName;
        }

        if (!validateService.teamNameValidation(teamName, leagueName)) {
            log.error("Incorrect Team {}", teamName);
            return "Please try with a valid team name";
        }

        if ("basketleague".equalsIgnoreCase(leagueName)) {
            basketLeague.deletePlayer(playerId);
            log.info("Player with ID {} deleted from {} and {}", playerId, teamName, leagueName);
            return "Player with ID " + playerId + " deleted from " + leagueName + " and " + teamName;
        } else if ("soccerleague".equalsIgnoreCase(leagueName)) {
            soccerLeague.deletePlayer(playerId);
            log.info("Player with ID {} deleted from {} and {}", playerId, teamName, leagueName);
            return "Player with ID " + playerId + " deleted from " + leagueName + " and " + teamName;
        }

        log.error("League {} doesn't exist", leagueName);
        return "League doesn't exist: Try soccerleague or basketleague";
    }
}
