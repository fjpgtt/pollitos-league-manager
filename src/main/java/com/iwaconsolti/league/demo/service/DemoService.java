package com.iwaconsolti.league.demo.service;

import com.iwaconsolti.league.demo.model.Player;
import com.iwaconsolti.league.demo.repository.Leagues;
import com.iwaconsolti.league.demo.repository.LeaguesRepository;
import com.iwaconsolti.league.demo.repository.Team;
import com.iwaconsolti.league.demo.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DemoService {
    private final LeaguesRepository leaguesRepository;

    @Autowired
    public DemoService(LeaguesRepository leaguesRepository) {
        this.leaguesRepository = leaguesRepository;
    }

    public List<Team> getTeamsFromLeague(String leagueName) {
        return leaguesRepository.getTeamsFromLeague(leagueName);
    }

    public void addTeam(String leagueName, Team team) {
        leaguesRepository.addTeamToLeague(leagueName, team);
    }

    public List<Player> getPlayersFromTeam(String leagueName, String teamName) {
        Leagues league = leaguesRepository.findLeague(leagueName);
        if (league != null) {
            for (Team team : league.getTeams()) {
                if (team.getName().equalsIgnoreCase(teamName)) {
                    return team.getPlayers();  // Returns player from team
                }
            }
        }
        return new ArrayList<>();
    }

    public void addPlayer(String leagueName, String teamName, Player player) {
        Leagues league = leaguesRepository.findLeague(leagueName);
        if (league != null) {
            for (Team team : league.getTeams()) {
                if (team.getName().equalsIgnoreCase(teamName)) {
                    team.addPlayer(player);
                    return;
                }
            }
            System.out.println("Team not found: " + teamName);
        } else {
            System.out.println("League not found: " + leagueName);
        }
    }
}
