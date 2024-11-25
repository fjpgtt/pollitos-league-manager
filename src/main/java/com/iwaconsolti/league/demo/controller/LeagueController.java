package com.iwaconsolti.league.demo.controller;

import com.iwaconsolti.league.demo.dto.MatchDTO;
import com.iwaconsolti.league.demo.dto.PlayerDTO;
import com.iwaconsolti.league.demo.dto.TeamDTO;
import com.iwaconsolti.league.demo.entity.TeamEntity;
import com.iwaconsolti.league.demo.repository.TeamRepository;
import com.iwaconsolti.league.demo.service.LeagueInterface;
import com.iwaconsolti.league.demo.service.ValidateService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/league")
@Slf4j
public class LeagueController {

    private final LeagueInterface basketLeague;
    private final LeagueInterface soccerLeague;

    @Resource
    private final ValidateService validateService;

    @Autowired
    public LeagueController(@Qualifier("basketleague") LeagueInterface basketLeague, @Qualifier("soccerleague") LeagueInterface soccerLeague, ValidateService validateService) {
        this.basketLeague = basketLeague;
        this.soccerLeague = soccerLeague;
        this.validateService = validateService;
    }


    @PostMapping("/{leagueName}/team")
    public ResponseEntity<String> createTeam(@PathVariable String leagueName, @RequestBody TeamDTO teamDTO) {
        if(validateService.validationLeagueName(leagueName)){
            if ("basketleague".equalsIgnoreCase(leagueName)) {
                basketLeague.createTeam(teamDTO);
                return ResponseEntity.ok("Team "+ teamDTO.getName() +" added to " + leagueName);
            } else if ("soccerleague".equalsIgnoreCase(leagueName))
                soccerLeague.createTeam(teamDTO);
            return ResponseEntity.ok("Team "+ teamDTO.getName() +" added to " + leagueName);
        }
        return ResponseEntity.badRequest().body("League does not exists");
    }

    @PostMapping("/{leagueName}/{team}/player")
    public ResponseEntity<String> createPlayer(@PathVariable String leagueName, @PathVariable String team, @RequestBody PlayerDTO playerDTO) {
        // Validate league
        if (!validateService.validationLeagueName(leagueName)) {
            log.error("Incorrect league");
            return ResponseEntity.badRequest().body("Please try with \"soccerleague\" or \"basketleague\"");
        }
        // Validate team
        if (validateService.validationTeamName(team, leagueName)) {
            log.error("Incorrect Team");
            return ResponseEntity.badRequest().body("Please try with a valid team name\"");
        }

        // Creating playerDTO
        if ("basketleague".equalsIgnoreCase(leagueName)) {
            basketLeague.createPlayer(playerDTO);
            return ResponseEntity.ok("Player " + playerDTO.getName() + " added to " + leagueName + " in team: " + team);
        } else if ("soccerleague".equalsIgnoreCase(leagueName))
            soccerLeague.createPlayer(playerDTO);
        return ResponseEntity.ok("Player " + playerDTO.getName() + " added to " + leagueName + " in team: " + team);

    }

    @PostMapping("/{leagueName}/match")
    public ResponseEntity<String> createMatch(@PathVariable String leagueName, @RequestBody MatchDTO matchDTO) {
        if (!validateService.validationLeagueName(leagueName)) {
            return ResponseEntity.badRequest().body("Incorrect league " + leagueName);
        }

        if ("basketleague".equalsIgnoreCase(leagueName)) {
            basketLeague.createMatch(matchDTO.getTeamDTO1(), matchDTO.getTeamDTO2());
            return ResponseEntity.ok("Match created in " + leagueName + " " + matchDTO.getTeamDTO1() + " + " + matchDTO.getTeamDTO2());
        } else if ("soccerleague".equalsIgnoreCase(leagueName)) {
            soccerLeague.createMatch(matchDTO.getTeamDTO1(), matchDTO.getTeamDTO2());
            return ResponseEntity.ok("Match created in " + leagueName + " " + matchDTO.getTeamDTO1() + " + " + matchDTO.getTeamDTO2());
        }
        return ResponseEntity.ok("League doesn't exist: Try soccerleague or basketleague");
    }

    @GetMapping("/{leagueName}/teams")
    public ResponseEntity<List<TeamDTO>> getAllTeams(@PathVariable String leagueName) {

        if (!validateService.validationLeagueName(leagueName)) {
            log.error("Incorrect league: {}", leagueName);
            return ResponseEntity.badRequest().body(new ArrayList<>());
        }
        return ("basketleague".equalsIgnoreCase(leagueName) ? ResponseEntity.ok(basketLeague.getAllTeams()) : ResponseEntity.ok(soccerLeague.getAllTeams()));
    }

    @GetMapping("/{leagueName}/team/{teamName}/players")
    public ResponseEntity<List<PlayerDTO>> getAllPlayers(@PathVariable String leagueName, @PathVariable String teamName) {
        List<PlayerDTO> players;

        if (!validateService.validationLeagueName(leagueName)) {
            return ResponseEntity.badRequest().build();
        }
        if (validateService.validationTeamName(teamName, leagueName)) {
            return ResponseEntity.badRequest().build();
        }

        if ("basketleague".equalsIgnoreCase(leagueName)) {
            players = basketLeague.getAllPlayers(teamName);
        } else if ("soccerleague".equalsIgnoreCase(leagueName)) {
            players = soccerLeague.getAllPlayers(teamName);
        } else {
            log.error("League {} doesn't exist", leagueName);
            return ResponseEntity.notFound().build();
        }

        if (players.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(players);
    }

    @GetMapping("/{teamName}")
    public List<PlayerDTO> getPlayersByTeam(@PathVariable String teamName) {
        return basketLeague.getPlayersByTeam(teamName);
    }

    @GetMapping("/{leagueName}/matches")
    public ResponseEntity<List<MatchDTO>> getMatchesFromLeagues(@PathVariable String leagueName) {
        List<MatchDTO> matches = List.of();

        if (!validateService.validationLeagueName(leagueName)) {
            return ResponseEntity.badRequest().build();
        }
        if ("basketleague".equalsIgnoreCase(leagueName)) {
            matches = basketLeague.getMatchDTOS();
        } else if ("soccerleague".equalsIgnoreCase(leagueName)) {
            matches = soccerLeague.getMatchDTOS();
        }
        return ResponseEntity.ok(matches);
    }

    @PutMapping("/{leagueName}/{teamName}/player/{playerId}")
    public ResponseEntity<String> editPlayer(@PathVariable String leagueName, @PathVariable String teamName, @PathVariable int playerId, @RequestBody PlayerDTO playerDTO) {
        if (!validateService.validationLeagueName(leagueName)) {
            log.error("Incorrect league: {}", leagueName);
            return ResponseEntity.badRequest().body("Incorrect league " + leagueName);
        }

        if (validateService.validationTeamName(teamName, leagueName)) {
            log.error("Incorrect TeamDTO {}", teamName);
            return ResponseEntity.badRequest().body("Please try with a valid team name");
        }

        if ("basketleague".equalsIgnoreCase(leagueName)) {
            basketLeague.editPlayer(playerId, playerDTO);
        } else if ("soccerleague".equalsIgnoreCase(leagueName)) {
            soccerLeague.editPlayer(playerId, playerDTO);
        }
        return ResponseEntity.ok("Player information updated in " + leagueName + " and " + teamName);

    }

    @PutMapping("/{leagueName}/team/{teamID}")
    public ResponseEntity<String> editTeam(@PathVariable String leagueName, @PathVariable int teamID, @RequestBody TeamDTO newTeamDTO) {
        if (!validateService.validationLeagueName(leagueName)) {
            return ResponseEntity.badRequest().body("Incorrect league: " + leagueName);
        }

        if ("basketleague".equalsIgnoreCase(leagueName)) {
            basketLeague.editTeam(teamID, newTeamDTO);
        } else if ("soccerleague".equalsIgnoreCase(leagueName)) {
            soccerLeague.editTeam(teamID, newTeamDTO);
        }
        return ResponseEntity.ok("Team "+ newTeamDTO.getName() +" updated in " + leagueName);
    }

    @DeleteMapping("/{leagueName}/delete/matches")
    public ResponseEntity<String> deleteAllMatches(@PathVariable String leagueName) {
        if (!validateService.validationLeagueName(leagueName)) {
            return ResponseEntity.badRequest().body("Incorrect league: " + leagueName);
        }

        if ("basketleague".equalsIgnoreCase(leagueName)) {
            basketLeague.deleteAllMatches();
        } else if ("soccerleague".equalsIgnoreCase(leagueName)) {
            soccerLeague.deleteAllMatches();
        }

        return ResponseEntity.ok("All matches deleted from " + leagueName);
    }

    @DeleteMapping("/{leagueName}/team/{teamName}/players")
    public ResponseEntity<String> deletePlayersFromTeam(@PathVariable String leagueName, @PathVariable String teamName) {
        if (!validateService.validationLeagueName(leagueName)) {
            return ResponseEntity.badRequest().body("Incorrect league: " + leagueName);
        }

        if ("basketleague".equalsIgnoreCase(leagueName)) {
            basketLeague.deletePlayersByTeam(teamName);
        } else if ("soccerleague".equalsIgnoreCase(leagueName)) {
            soccerLeague.deletePlayersByTeam(teamName);
        }
        return ResponseEntity.ok("All players from team: " + teamName + " deleted in " + leagueName);
    }
}
