package com.iwaconsolti.league.demo.service;

import com.iwaconsolti.league.demo.model.Match;
import com.iwaconsolti.league.demo.model.Player;
import com.iwaconsolti.league.demo.model.Team;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("basketleague")
@Slf4j
@Getter
@Setter
@ToString
@NoArgsConstructor
public class BasketLeague implements LeagueInterface {
    private final List<Match> matches = new ArrayList<>();
    private final List<Team> teams = new ArrayList<>();
    private final List<Player> players = new ArrayList<>();
    private int ID;

    public BasketLeague(int ID) {
        this.ID = ID;
    }

    @Override
    public void createTeam(Team team) {
        teams.add(team);
    }

    @Override
    public void createPlayer(Player player) {
        log.info(players.toString());
        players.add(player);
    }

    @Override
    public void createMatch(Team team1, Team team2) {
      //Here we created a match with two teams and then we added that match to the list of matches.
        Match match = new Match(team1, team2);
        matches.add(match);
    }

    @Override
    public List<Team> getAllTeams() {
        return List.of();
    }

    @Override
    public List<Team> getAllPlayers() {
        return List.of();
    }

    @Override
    public void editPlayer(int ID, Player player) {

    }

    @Override
    public void editTeam(int ID, Team team) {

    }

    @Override
    public void deleteAllMatches() {

    }

    @Override
    public void deletePlayersOfATeam(int ID) {

    }

    @Override
    public int getTeamLimit() {
        return 0;
    }
}
