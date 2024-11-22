package com.iwaconsolti.league.demo.service;

import com.iwaconsolti.league.demo.dto.MatchDTO;
import com.iwaconsolti.league.demo.dto.PlayerDTO;
import com.iwaconsolti.league.demo.dto.TeamDTO;

import java.util.List;

public interface LeagueInterface {

    //The creating methods
    void createTeam(TeamDTO teamDTO);
    void createPlayer(PlayerDTO playerDTO);
    void createMatch(TeamDTO teamDTO1, TeamDTO teamDTO2);

    List<TeamDTO> getAllTeams();
    List<PlayerDTO> getAllPlayers(String teamName);

    void editPlayer(long ID, PlayerDTO playerDTO);
    void editTeam(long teamID, TeamDTO teamDTO);

    void deleteAllMatches();
    void deletePlayersOfATeam(String teamName);

    List<MatchDTO> getMatchDTOS();
}
