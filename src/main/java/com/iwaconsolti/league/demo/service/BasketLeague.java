package com.iwaconsolti.league.demo.service;

import com.iwaconsolti.league.demo.dto.MatchDTO;
import com.iwaconsolti.league.demo.dto.PlayerDTO;
import com.iwaconsolti.league.demo.dto.TeamDTO;
import com.iwaconsolti.league.demo.model.LeagueType;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("basketleague")
@Slf4j
@Getter
@Setter
@ToString
public class BasketLeague implements LeagueInterface {
    //Services
    private final TeamService teamService;
    private final PlayerService playerService;
    private final MatchService matchService;
    private static final LeagueType league = LeagueType.BASKET_LEAGUE;


    @Autowired
    public BasketLeague(TeamService teamService, PlayerService playerService, MatchService matchService) {
        this.teamService = teamService;
        this.playerService = playerService;
        this.matchService = matchService;
    }

    public void addingLeagueToTeam(TeamDTO teamDTO){
        teamDTO.setLeague(league.getValue());
    }

    //-----done
    @Override
    public void createTeam(TeamDTO teamDTO) {
        addingLeagueToTeam(teamDTO);
        teamService.createTeam(teamDTO);
    }


    //----done
    @Override
    public void createPlayer(PlayerDTO playerDTO) {
        playerService.createPlayer(playerDTO, league.getValue());
    }

    //-----done
    @Override
    public void createMatch(TeamDTO teamDTO1, TeamDTO teamDTO2) {
        addingLeagueToTeam(teamDTO1);
        addingLeagueToTeam(teamDTO2);

        matchService.createMatch(teamDTO1, teamDTO2);
    }

    //-----done
    @Override
    public List<TeamDTO> getAllTeams() {
        return teamService.getAllTeams();
    }

    //-----donde
    @Override
    public List<PlayerDTO> getAllPlayers(String teamName) {
        return playerService.getAllPlayersByTeam(teamName);
    }

    @Override
    public List<PlayerDTO> getPlayersByTeam(String teamName) {
        return teamService.getPlayersByTeam(teamName);
    }

    //-----done
    @Override
    public void editPlayer(long id, PlayerDTO playerDTO) {
        playerService.editPlayer(id, playerDTO);
    }

    //-----done
    @Override
    public void editTeam(long teamID, TeamDTO newTeamDTO) {
        addingLeagueToTeam(newTeamDTO);
        teamService.editTeam(teamID, newTeamDTO);
    }

    //-----done
    @Override
    public void deleteAllMatches() {
        matchService.deleteAllMatches();
    }

    //-----done
    @Override
    public void deletePlayersByTeam(String teamName) {
        playerService.deletePlayersByTeam(teamName);
    }

    //---done
    @Override
    public List<MatchDTO> getMatchDTOS() {
        return matchService.getAllMatches();
    }

}
