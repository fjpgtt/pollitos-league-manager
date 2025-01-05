package com.iwaconsolti.league.demo.controller;

import com.iwaconsolti.league.demo.dto.MatchDTO;
import com.iwaconsolti.league.demo.dto.PlayerDTO;
import com.iwaconsolti.league.demo.dto.TeamDTO;
import com.iwaconsolti.league.demo.service.LeagueInterface;
import com.iwaconsolti.league.demo.service.ValidateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.HashMap;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/{leagueName}")
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class LeagueController {

    private final LeagueInterface basketLeague;
    private final LeagueInterface soccerLeague;
    private final ValidateService validateService;

    @Autowired
    public LeagueController(@Qualifier("basketleague") LeagueInterface basketLeague, @Qualifier("soccerleague") LeagueInterface soccerLeague, ValidateService validateService) {
        this.basketLeague = basketLeague;
        this.soccerLeague = soccerLeague;
        this.validateService = validateService;
    }

    @GetMapping("/api/saludo")
    public Map<String, String> obtenerSaludo() {
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Hola desde el backend con Spring Boot!");
        return respuesta;
    }

    @PostMapping("/team")
    public ResponseEntity<Map<String, String>> createTeam(@PathVariable String leagueName, @RequestBody TeamDTO teamDTO) {
        if (validateService.validationLeagueName(leagueName)) {
            LeagueInterface leagueService = validateService.getLeagueService(leagueName);
            leagueService.createTeam(teamDTO);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Team " + teamDTO.getName() + " added to " + leagueName);
            return ResponseEntity.ok(response); // Devolvemos JSON
        }
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", "League does not exists");
        return ResponseEntity.badRequest().body(errorResponse); // Devolvemos JSON
    }

    @PostMapping("/{team}/player")
    public ResponseEntity<Map<String,String>> createPlayer(@PathVariable String leagueName, @PathVariable String team, @RequestBody PlayerDTO playerDTO) {
        // Validate league
        Map<String, String> response = new HashMap<>();

        if (!validateService.validationLeagueName(leagueName)) {
            log.error("Incorrect league");
            response.put("message:","Please try with \"soccerleague\" or \"basketleague\"");
            return ResponseEntity.badRequest().body(response);
        }
        // Validate team
        if (validateService.validationTeamName(team, leagueName)) {
            log.error("Incorrect Team");
            response.put("message:","Incorrect team");
            return ResponseEntity.badRequest().body(response);
        }

        // Creating playerDTO
        if ("basketleague".equalsIgnoreCase(leagueName)) {
            basketLeague.createPlayer(playerDTO);
            response.put("message", "Player " + playerDTO.getName() + " added to " + leagueName + " in team: " + team);
            return ResponseEntity.ok(response);
        } else if ("soccerleague".equalsIgnoreCase(leagueName))
            soccerLeague.createPlayer(playerDTO);
            response.put("message", "Player " + playerDTO.getName() + " added to " + leagueName + " in team: " + team);
        return ResponseEntity.ok(response);

    }

    @PostMapping("/match")
    public ResponseEntity<Map<String, String>> createMatch(@PathVariable String leagueName, @RequestBody MatchDTO matchDTO) {
        Map<String, String> response = new HashMap<>();
        if (!validateService.validationLeagueName(leagueName)) {
            response.put("Message", "Incorrect league" + leagueName);
            return ResponseEntity.badRequest().body(response);
        }

        matchDTO.getTeamDTO2().setLeague(leagueName);
        matchDTO.getTeamDTO1().setLeague(leagueName);

        if ("basketleague".equalsIgnoreCase(leagueName)) {
            basketLeague.createMatch(matchDTO.getTeamDTO1(), matchDTO.getTeamDTO2());
            response.put("message", "Match created in " + leagueName + " " + matchDTO.getTeamDTO1() + " + " + matchDTO.getTeamDTO2());
            return ResponseEntity.ok(response);
        } else if ("soccerleague".equalsIgnoreCase(leagueName)) {
            soccerLeague.createMatch(matchDTO.getTeamDTO1(), matchDTO.getTeamDTO2());
            response.put("message", "Match created in " + leagueName + " " + matchDTO.getTeamDTO1() + " + " + matchDTO.getTeamDTO2());
            return ResponseEntity.ok(response);
        }
        response.put("message", "League doesn't exist: Try soccerleague or basketleague");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/teams")
    public ResponseEntity<List<TeamDTO>> getAllTeams(@PathVariable String leagueName) {

        if (!validateService.validationLeagueName(leagueName)) {
            log.error("Incorrect league: {}", leagueName);
            return ResponseEntity.badRequest().body(new ArrayList<>());
        }


        List<TeamDTO> teams = ("basketleague".equalsIgnoreCase(leagueName) ? basketLeague.getAllTeams() : soccerLeague.getAllTeams());

        if (teams == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(teams);
        }
        return ResponseEntity.ok(teams);
    }

    @GetMapping("/team/{teamName}/players")
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
    public ResponseEntity<List<PlayerDTO>> getPlayersByTeam(@PathVariable String teamName) {
        List<PlayerDTO> players = basketLeague.getPlayersByTeam(teamName);
        if (players.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(players);
        }
        return ResponseEntity.ok(players);
    }

    @GetMapping("/matches")
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

    @PutMapping("/{teamName}/player/{playerId}")
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

    @PutMapping("/team/{teamID}")
    public ResponseEntity<Map<String, String>> editTeam(@PathVariable String leagueName, @PathVariable long teamID, @RequestBody TeamDTO newTeamDTO) {
        Map<String, String> response = new HashMap<>();

        if (!validateService.validationLeagueName(leagueName)) {
            response.put("message", "Incorrect league: " + leagueName);
            return ResponseEntity.badRequest().body(response);
        }

        if ("basketleague".equalsIgnoreCase(leagueName)) {
            basketLeague.editTeam(teamID, newTeamDTO);
        } else if ("soccerleague".equalsIgnoreCase(leagueName)) {
            soccerLeague.editTeam(teamID, newTeamDTO);
        }

        response.put("message", "Team " + newTeamDTO.getName() + " updated in " + leagueName);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/matches")
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

    @DeleteMapping("/team/{teamName}/players")
    public ResponseEntity<Map<String,String>> deletePlayersFromTeam(@PathVariable String leagueName, @PathVariable String teamName) {
        Map<String, String> response = new HashMap<>();
        if (!validateService.validationLeagueName(leagueName)) {
            response.put("message:","Incorrect league: "+ leagueName);
            return ResponseEntity.badRequest().body(response);
        }

        if ("basketleague".equalsIgnoreCase(leagueName)) {
            basketLeague.deletePlayersByTeam(teamName);
        } else if ("soccerleague".equalsIgnoreCase(leagueName)) {
            soccerLeague.deletePlayersByTeam(teamName);
        }
        response.put("message", "All players from team: " + teamName + " were deleted correctly");
        return ResponseEntity.ok(response);
    }
}
