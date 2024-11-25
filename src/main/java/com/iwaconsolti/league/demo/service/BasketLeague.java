package com.iwaconsolti.league.demo.service;

import com.iwaconsolti.league.demo.dto.MatchDTO;
import com.iwaconsolti.league.demo.dto.PlayerDTO;
import com.iwaconsolti.league.demo.dto.TeamDTO;
import com.iwaconsolti.league.demo.entity.MatchEntity;
import com.iwaconsolti.league.demo.entity.PlayerEntity;
import com.iwaconsolti.league.demo.entity.TeamEntity;
import com.iwaconsolti.league.demo.repository.MatchRepository;
import com.iwaconsolti.league.demo.repository.PlayerRepository;
import com.iwaconsolti.league.demo.repository.TeamRepository;
import jakarta.annotation.PostConstruct;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Profile("populated")
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
    private final String league = "basket league";

    @Autowired
    public BasketLeague(TeamService teamService, PlayerService playerService, MatchService matchService) {
        this.teamService = teamService;
        this.playerService = playerService;
        this.matchService = matchService;
    }

    //-----done
    @Override
    public void createTeam(TeamDTO teamDTO) {
        teamDTO.setLeague(league);
        teamService.createTeam(teamDTO);
    }


    //----done
    @Override
    public void createPlayer(PlayerDTO playerDTO) {
        playerService.createPlayer(playerDTO, league);
    }

    //-----done
    @Override
    public void createMatch(TeamDTO teamDTO1, TeamDTO teamDTO2) {
        teamDTO1.setLeague(league);
        teamDTO2.setLeague(league);
        matchService.createMatch(teamDTO1, teamDTO2, league);
    }

    //-----done
    @Override
    public List<TeamDTO> getAllTeams() {
        return teamService.getAllTeams();
    }

    //-----donde
    @Override
    public List<PlayerDTO> getAllPlayers(String teamName) {
        return playerService.getAllPlayers(teamName);
    }

    @Override
    public List<PlayerDTO> getPlayersByTeam(String teamName) {
        return teamService.getPlayersByTeam(teamName);
    }

    //-----done
    @Override
    public void editPlayer(long playerID, PlayerDTO playerDTO) {
        playerService.editPlayer(playerID, playerDTO);
    }

    //-----done
    @Override
    public void editTeam(long teamID, TeamDTO newTeamDTO) {
        newTeamDTO.setLeague(league);
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
