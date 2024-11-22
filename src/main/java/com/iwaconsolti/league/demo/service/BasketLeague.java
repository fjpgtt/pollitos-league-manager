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
import java.util.SequencedCollection;

@Profile("populated")
@Service("basketleague")
@Slf4j
@Getter
@Setter
@ToString
public class BasketLeague implements LeagueInterface {
    @Value("${league.teamLimit:10}")
    private int TEAMLIMIT;

    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;
    private final MatchRepository matchRepository;

    @Autowired
    public BasketLeague(TeamRepository teamRepository, PlayerRepository playerRepository, MatchRepository matchRepository) {
        this.teamRepository = teamRepository;
        this.playerRepository = playerRepository;
        this.matchRepository = matchRepository;
    }

    @Override
    public void createTeam(TeamDTO teamDTO) {
        if (teamRepository.count() >= TEAMLIMIT) {
            log.error("You reached the max of team per league, max: {}", TEAMLIMIT);
        } else {
            TeamEntity teamEntity = new TeamEntity();
            teamEntity.setName(teamDTO.getName());
            teamEntity.setScore(teamDTO.getScore());
            teamRepository.save(teamEntity);
            log.info("Team {} added to Basket League", teamDTO.getName());
        }
    }

    @Override
    public void createPlayer(PlayerDTO playerDTO) {
        PlayerEntity playerEntity = new PlayerEntity();
        playerEntity.setName(playerDTO.getName());
        playerEntity.setTeam(playerDTO.getTeam());
        playerEntity.setID(playerDTO.getId());
        playerRepository.save(playerEntity);

        log.info("Player created: {}", playerDTO.getName());
        log.info("Player added to Basket league in team {}", playerDTO.getTeam());

    }

    @Override
    public void createMatch(TeamDTO teamDTO1, TeamDTO teamDTO2) {
        TeamEntity team1 = new TeamEntity(teamDTO1.getID(), teamDTO1.getName(), teamDTO1.getScore());
        TeamEntity team2 = new TeamEntity(teamDTO1.getID(), teamDTO1.getName(), teamDTO1.getScore());
        MatchEntity matchEntity = new MatchEntity();
        matchEntity.setTeam1(team1);
        matchEntity.setTeam2(team2);
        matchRepository.save(matchEntity);
        log.info("Match created in Basket League: {} vs {} ", teamDTO1.getName(), teamDTO2.getName());

    }

    @Override
    public List<TeamDTO> getAllTeams() {
        List<TeamEntity> teamEntities = teamRepository.findAll();
        List<TeamDTO> teamDTOS = new ArrayList<>();
        for (TeamEntity teamEntity : teamEntities) {
            teamDTOS.add(new TeamDTO(teamEntity.getName(), teamEntity.getScore(), teamEntity.getID()));
        }
        log.info("Returning all the teams in the repository");
        return teamDTOS;

    }

    @Override
    public List<PlayerDTO> getAllPlayers(String teamName) {
        log.info("Returning all players from Basket League Team: {}", teamName);

        List<PlayerEntity> playerEntities = playerRepository.findAll();
        List<PlayerDTO> playersDTO = new ArrayList<>();
        for (PlayerEntity playerEntity : playerEntities) {
            playersDTO.add(new PlayerDTO(playerEntity.getID(), playerEntity.getName(), playerEntity.getTeam()));
        }
        return playersDTO;
    }

    @Override
    public void editPlayer(long playerID, PlayerDTO playerDTO) {
        PlayerEntity playerEntity = playerRepository.findById(playerID).orElse(null);
        if (playerEntity != null) {
            playerEntity.setName(playerDTO.getName());
            playerEntity.setTeam(playerDTO.getTeam());
            playerRepository.save(playerEntity);
            log.info("Player updated: {}", playerDTO.getName());
        } else {
            log.error("Player with ID {} not found", playerID);
        }
    }


    @Override
    public void editTeam(long teamID, TeamDTO newTeamDTO) {
        TeamEntity teamEntity = teamRepository.findById(teamID).orElse(null);
        if (teamEntity != null) {
            teamEntity.setName(newTeamDTO.getName());
            teamEntity.setScore(newTeamDTO.getScore());
            teamRepository.save(teamEntity);
            log.info("Team with ID: {} has been updated.", teamID);
        }
        log.info("Team with ID: {} not found", teamID);

    }

    @Override
    public void deleteAllMatches() {
        matchRepository.deleteAll();
        log.info("All match deleted from Basket League");

    }

    @Override
    public void deletePlayersOfATeam(String teamName) {
        playerRepository.deleteAll();
        log.info("All player from team: {} were deleted in Basket League", teamName);
    }

    @Override
    public List<MatchDTO> getMatchDTOS() {
        return matchRepository.findAll().stream()
                .map(match -> new MatchDTO(
                        new TeamDTO(match.getTeam1().getName(), match.getTeam1().getScore(), match.getTeam1().getID()),
                        new TeamDTO(match.getTeam2().getName(), match.getTeam2().getScore(), match.getTeam2().getID())))
                .toList();
    }

    public List<TeamEntity> getPlayersByTeam(String teamName) {
        log.info("Teams found: {}", teamName);
        return teamRepository.findByName(teamName);
    }


    @PostConstruct
    public void fillBasketLeagues() {
// Creating players for Team 1
        PlayerEntity player1 = new PlayerEntity();
        player1.setName("Player1");

        PlayerEntity player2 = new PlayerEntity();
        player2.setName("Player2");

// Creating players for Team 2
        PlayerEntity player3 = new PlayerEntity();
        player3.setName("Player3");

        PlayerEntity player4 = new PlayerEntity();
        player4.setName("Player4");

// Creating teams
        TeamEntity team1 = new TeamEntity(1, "Team1", 0, new ArrayList<>(List.of(player1, player2)));
        TeamEntity team2 = new TeamEntity(2, "Team2", 0, new ArrayList<>(List.of(player3, player4)));

        log.info("Teams created: {} and {}", team1.getName(), team2.getName());

// Setting team names for each player
        player1.setTeam(team1.getName());
        player2.setTeam(team1.getName());

        player3.setTeam(team2.getName());
        player4.setTeam(team2.getName());

// Saving teams in the team repository
        teamRepository.save(team1);
        teamRepository.save(team2);

// Saving players in the player repository
        playerRepository.save(player1);
        playerRepository.save(player2);
        playerRepository.save(player3);
        playerRepository.save(player4);

        log.info("Players created and added to teams: {} and {}", team1.getName(), team2.getName());

// Creating matches between the teams
        MatchEntity match1 = new MatchEntity();
        match1.setTeam1(team1);
        match1.setTeam2(team2);

// Saving the match in the match repository
        matchRepository.save(match1);

        log.info("Match created: {} vs {}", team1.getName(), team2.getName());
        log.info("Basket league created and filled with teams, players, and matches.");
    }
}
