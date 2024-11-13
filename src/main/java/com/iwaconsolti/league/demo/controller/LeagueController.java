package com.iwaconsolti.league.demo.controller;

import com.iwaconsolti.league.demo.model.Player;
import com.iwaconsolti.league.demo.model.Team;
import com.iwaconsolti.league.demo.model.Match;
import com.iwaconsolti.league.demo.service.BasketLeague;
import com.iwaconsolti.league.demo.service.SoccerLeague;
import com.iwaconsolti.league.demo.service.ValidateService;
import jakarta.annotation.Resource;
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

    @Resource
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
            return "Team added to " + leagueName;
        } else if ("soccerleague".equalsIgnoreCase(leagueName)) {
            soccerLeague.createTeam(team);
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
            return "Player added to " + leagueName + " in team: " + team;
        } else if ("soccerleague".equalsIgnoreCase(leagueName)) {
            soccerLeague.createPlayer(player);
            return "Player added to " + leagueName + " in team: " + team;
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
            return "Match created in " + leagueName + " " + match.getTeam1() + " vs " + match.getTeam2();
        } else if ("soccerleague".equalsIgnoreCase(leagueName)) {
            soccerLeague.createMatch(match.getTeam1(), match.getTeam2());
            return "Match created in " + leagueName + " " + match.getTeam1() + " vs " + match.getTeam2();
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
            return basketLeague.getAllPlayers(teamName);
        } else if ("soccerleague".equalsIgnoreCase(leagueName)) {
            return soccerLeague.getAllPlayers(teamName);
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
            return "Player information updated in " + leagueName + " and " + teamName;
        } else if ("soccerleague".equalsIgnoreCase(leagueName)) {
            soccerLeague.editPlayer(playerId, player);
            return "Player information updated in " + leagueName + " and " + teamName;
        }

        log.error("League {} doesn't exist", leagueName);
        return "League doesn't exist: Try soccerleague or basketleague";
    }

    @PutMapping("/{leagueName}/team/{teamID}")
    public String editTeam(@PathVariable String leagueName, @PathVariable int teamID, @RequestBody Team newTeam) {
        if (!validateService.leagueNameValidation(leagueName)) {
            log.error("Incorrect league: {}", leagueName);
            return "Incorrect league: " + leagueName;
        }

        if ("basketleague".equalsIgnoreCase(leagueName)) {
            basketLeague.editTeam(teamID, newTeam);
            return "Team updated in " + leagueName;
        } else if ("soccerleague".equalsIgnoreCase(leagueName)) {
            soccerLeague.editTeam(teamID, newTeam);
            return "Team updated in " + leagueName;
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
            return "All matches deleted from " + leagueName;
        } else if ("soccerleague".equalsIgnoreCase(leagueName)) {
            soccerLeague.deleteAllMatches();
            return "All matches deleted from " + leagueName;
        }

        log.error("League {} doesn't exist", leagueName);
        return "League doesn't exist: Try soccerleague or basketleague";
    }

    @DeleteMapping("/{leagueName}/team/{teamID}/players")
    public String deletePlayersOfATeam(@PathVariable String leagueName, @PathVariable String teamName) {
        if (!validateService.leagueNameValidation(leagueName)) {
            log.error("Incorrect league: {}", leagueName);
            return "Incorrect league: " + leagueName;
        }

        if ("basketleague".equalsIgnoreCase(leagueName)) {
            basketLeague.deletePlayersOfATeam(teamName);
            return "All players from team ID " + teamName + " deleted in " + leagueName;
        } else if ("soccerleague".equalsIgnoreCase(leagueName)) {
            soccerLeague.deletePlayersOfATeam(teamName);
            log.info("All players from team ID {} deleted in {}", teamName, leagueName);
            return "All players from team ID " + teamName + " deleted in " + leagueName;
        }

        log.error("League {} doesn't exist", leagueName);
        return "League doesn't exist: Try soccerleague or basketleague";
    }
}
