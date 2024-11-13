package com.iwaconsolti.league.demo.service;

import com.iwaconsolti.league.demo.dto.MatchDTO;
import com.iwaconsolti.league.demo.dto.PlayerDTO;
import com.iwaconsolti.league.demo.dto.TeamDTO;
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
@Service("basketleague")
@Slf4j
@Getter
@Setter
@ToString
@NoArgsConstructor
public class BasketLeague implements LeagueInterface {
    @Value("${league.teamLimit}")
    private int TEAMLIMIT;

    private final List<MatchDTO> matchDTOS = new ArrayList<>();
    private final List<TeamDTO> teamDTOS = new ArrayList<>();
    private final List<PlayerDTO> playerDTOS = new ArrayList<>();
    private int ID;

    public BasketLeague(int ID, int limit) {
        this.ID = ID;
    }

    @Override
    public void createTeam(TeamDTO teamDTO) {
        if (this.teamDTOS.size() == TEAMLIMIT) {
            log.error("You reached the max of team per league");
        } else {
            this.teamDTOS.add(teamDTO);
            log.info("Team {} added to Basket League", teamDTO.getName());
        }
    }

    @Override
    public void createPlayer(PlayerDTO playerDTO) {
        playerDTOS.add(playerDTO);
        log.info("Player created: {}", playerDTO.getName());
        log.info("Player added to Basket league in team {}", playerDTO.getTeam());

    }

    @Override
    public void createMatch(TeamDTO teamDTO1, TeamDTO teamDTO2) {
        //Here we created a matchDTO with two teamDTOS and then we added that matchDTO to the list of matchDTOS.
        MatchDTO matchDTO = new MatchDTO(teamDTO1, teamDTO2);
        matchDTOS.add(matchDTO);
        log.info("Match created in Basket League: {} vs {} ", teamDTO1.getName(), teamDTO2.getName());

    }

    @Override
    public List<TeamDTO> getAllTeams() {
        return new ArrayList<>(teamDTOS);
    }

    @Override
    public List<PlayerDTO> getAllPlayers(String teamName) {
        return playerDTOS.stream()
                .filter(player -> player.getTeam().equalsIgnoreCase(teamName))
                .toList();
    }


    @Override
    public void editPlayer(int playerID, PlayerDTO playerDTO) {
        playerDTOS.stream()
                .filter(p -> p.getId() == playerID)  // Filter te playerDTO by id
                .findFirst()
                .ifPresentOrElse(foundPlayer -> {
                    foundPlayer.setName(playerDTO.getName());
                    foundPlayer.setTeam(playerDTO.getTeam());
                    log.info("Player updated, new Name: {}, new team: {}", foundPlayer.getName(), foundPlayer.getTeam());

                }, () -> {
                    //if we could not found the playerDTO
                    log.error("Player with ID {} not found", playerID);
                });
    }


    @Override
    public void editTeam(int teamID, TeamDTO newTeamDTO) {
        teamDTOS.stream()
                .filter(team -> team.getID() == teamID)
                .findFirst()
                .ifPresentOrElse(foundTeam -> {
                    foundTeam.setName(newTeamDTO.getName());
                    foundTeam.setScore(newTeamDTO.getScore());
                    log.info("Team with id {} updated, new name: {}, new score {}", teamID, foundTeam.getName(), foundTeam.getScore());
                    log.info("Finish updating team with ID {} updated in Basket League", teamID);
                }, () -> log.error("Team with ID {} not found", teamID));
    }

    @Override
    public void deleteAllMatches() {
        matchDTOS.clear();
        log.info("All match deleted from Basket League");

    }

    @Override
    public void deletePlayersOfATeam(String teamName) {
        playerDTOS.removeIf(player -> player.getTeam().equalsIgnoreCase(teamName));
        log.info("All player from team: {} were deleted in Basket League", teamName);
    }

    @PostConstruct
    public void fillBasketLeagues() {

        // Creating teamDTOS
        TeamDTO teamDTO1 = new TeamDTO("team1", 0, 1);
        TeamDTO teamDTO2 = new TeamDTO("team2", 0, 2);

        //Creating playerDTOS
        PlayerDTO playerDTO1 = new PlayerDTO(1, "Player1", teamDTO1.getName());
        PlayerDTO playerDTO2 = new PlayerDTO(2, "Player2", teamDTO2.getName());

        //Adding Players to teamDTOS
        teamDTO1.addPlayer(playerDTO1);
        teamDTO2.addPlayer(playerDTO2);

        //Adding Teams to league basket
        teamDTOS.add(teamDTO1);
        teamDTOS.add(teamDTO2);

        //Adding PlayerDTO to TeamDTO
        playerDTOS.add(playerDTO1);
        playerDTOS.add(playerDTO2);

        //Creating MatchDTO for basket
        MatchDTO basketMatchDTO = new MatchDTO(teamDTO1, teamDTO2);
        matchDTOS.add(basketMatchDTO);

        log.info("Basket league created and filled.");
    }
}
