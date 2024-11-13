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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Profile("populated")
@Service("soccerleague")
@Slf4j
@Getter
@Setter
@ToString
@NoArgsConstructor
public class SoccerLeague implements LeagueInterface {
    @Value("${league.teamLimit}")
    private int TEAMLIMIT;

    private final List<Match> matches = new ArrayList<>();
    private final List<Team> teams = new ArrayList<>();
    private final List<Player> players = new ArrayList<>();
    private int ID;

    public SoccerLeague(int ID) {
        this.ID = ID;
    }

    @Override
    public void createTeam(Team team) {
        if (teams.size() == TEAMLIMIT) {
            log.error("You reached the max of teams per league");
        } else {
            teams.add(team);
            log.info("Team {} added to Soccer League", team.getName());
        }
    }

    @Override
    public void createPlayer(Player player) {
        players.add(player);
        log.info("Player created: {}", player.getName());
        log.info("Player added to Basket league in team {}", player.getTeam());
    }

    @Override
    public void createMatch(Team team1, Team team2) {
        //Here we created a match with two teams and then we added that match to the list of matches.
        Match match = new Match(team1, team2);
        matches.add(match);
        log.info("Match created in Soccer League: {} vs {} ", team1.getName(), team2.getName());

    }

    @Override
    public List<Team> getAllTeams() {
        return new ArrayList<>(teams);

    }

    @Override
    public List<Player> getAllPlayers(String teamName) {
        return players.stream()
                .filter(player -> player.getTeam().equalsIgnoreCase(teamName))
                .toList();
    }

    @Override
    public void editPlayer(int playerID, Player player) {
        players.stream()
                .filter(p -> p.getId() == playerID)  // Filter te player by id
                .findFirst()
                .ifPresentOrElse(foundPlayer -> {
                    foundPlayer.setName(player.getName());
                    foundPlayer.setTeam(player.getTeam());
                    log.info("Player updated, new name: {}, new team: {}", foundPlayer.getName(), foundPlayer.getTeam());
                }, () -> {
                    //if we could not found the player
                    log.error("Player with ID {} not found", playerID);
                });
    }

    @Override
    public void editTeam(int teamID, Team newTeam) {
        teams.stream()
                .filter(team -> team.getID() == teamID)
                .findFirst()
                .ifPresentOrElse(foundTeam -> {
                    foundTeam.setName(newTeam.getName());
                    foundTeam.setScore(newTeam.getScore());
                    log.info("Team with id {} updated, new name: {}, new score {}", teamID, foundTeam.getName(), foundTeam.getScore());
                    log.info("Finish updating team with ID {} updated in Basket League", teamID);
                }, () -> log.error("Team with ID {} not found", teamID));
    }

    @Override
    public void deleteAllMatches() {
        matches.clear();
        log.info("All matches deleted from Soccer League");

    }

    @Override
    public void deletePlayersOfATeam(String teamName) {
        players.removeIf(player -> player.getTeam().equalsIgnoreCase(teamName));
        log.info("All players from team: {} were deleted in Basket League", teamName);
    }

    @PostConstruct
    public void fillSoccerLeagues() {

        // Creating teams
        Team team3 = new Team("team3", 0, 1);
        Team team4 = new Team("team4", 0, 2);

        //Creating players
        Player player1 = new Player(1, "Player1", team3.getName());
        Player player2 = new Player(2, "Player2", team4.getName());

        //Adding Players to teams
        team3.addPlayer(player1);
        team4.addPlayer(player2);

        //Adding Teams to league basket
        teams.add(team3);
        teams.add(team4);

        //Adding Player to Team
        players.add(player1);
        players.add(player2);

        //Creating Match for basket
        Match soccerMatch = new Match(team3, team4);
        matches.add(soccerMatch);

        log.info("Soccer league created and filled.");
    }

}
