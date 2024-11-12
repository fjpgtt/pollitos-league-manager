//package com.iwaconsolti.league.demo.service;
//
//import com.iwaconsolti.league.demo.model.Player;
//import com.iwaconsolti.league.demo.service.repository.LeaguesRepository;
//import com.iwaconsolti.league.demo.model.Team;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//@Slf4j
//public class LeagueService {
//    private final LeaguesRepository leaguesRepository;
//
//    @Autowired
//    public LeagueService(LeaguesRepository leaguesRepository) {
//        this.leaguesRepository = leaguesRepository;
//    }
//
//    public List<Team> getTeamsFromLeague(String leagueName) {
//        return leaguesRepository.getTeamsFromLeague(leagueName);
//    }
//
//    public void addTeam(String leagueName, Team team) {
//        leaguesRepository.addTeamToLeague(leagueName, team);
//    }
//
//    public List<Player> getPlayersFromTeam(String leagueName, String teamName) {
//        League league = leaguesRepository.findLeague(leagueName);
//        if (league != null) {
//            for (Team team : league.getTeams()) {
//                if (team.getName().equalsIgnoreCase(teamName)) {
//                    return team.getPlayers();  // Returns player from team
//                }
//            }
//        }
//        return new ArrayList<>();
//    }
//
//    public void addPlayer(String leagueName, String teamName, Player player) {
//        League league = leaguesRepository.findLeague(leagueName);
//        if (league != null) {
//            for (Team team : league.getTeams()) {
//                if (team.getName().equalsIgnoreCase(teamName)) {
//                    team.addPlayer(player);
//                    return;
//                }
//            }
//            log.error("Team not found:"+teamName);
//        } else {
//            log.error("League not found"+leagueName);
//        }
//    }
//}
