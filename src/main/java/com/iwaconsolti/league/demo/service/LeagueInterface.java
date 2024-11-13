package com.iwaconsolti.league.demo.service;

import com.iwaconsolti.league.demo.model.Player;
import com.iwaconsolti.league.demo.model.Team;

import java.util.List;

public interface LeagueInterface {

    //The creating methods
    void createTeam(Team team);
    void createPlayer(Player player);
    void createMatch(Team team1, Team team2);

    List<Team> getAllTeams();
    List<Player> getAllPlayers(String teamName);

    void editPlayer(int ID, Player player);
    void editTeam(int teamID, Team team);

    void deleteAllMatches();
    void deletePlayersOfATeam(String teamName);

}
