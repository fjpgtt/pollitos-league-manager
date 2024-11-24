package com.iwaconsolti.league.demo.service;

import com.iwaconsolti.league.demo.dto.MatchDTO;
import com.iwaconsolti.league.demo.dto.PlayerDTO;
import com.iwaconsolti.league.demo.dto.TeamDTO;
import com.iwaconsolti.league.demo.entity.TeamEntity;
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

    private final List<MatchDTO> matchDTOS = new ArrayList<>();
    private final List<TeamDTO> teamDTOS = new ArrayList<>();
    private final List<PlayerDTO> playerDTOS = new ArrayList<>();
    private int ID;

    public SoccerLeague(int ID) {
        this.ID = ID;
    }

    @Override
    public void createTeam(TeamDTO teamDTO) {
        if (this.teamDTOS.size() == TEAMLIMIT) {
            log.error("You reached the max of team per league");
        } else {
            this.teamDTOS.add(teamDTO);
            log.info("Team {} added to Soccer League", teamDTO.getName());
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
        log.info("Match created in Soccer League: {} vs {} ", teamDTO1.getName(), teamDTO2.getName());

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
    public void editPlayer(long playerID, PlayerDTO playerDTO) {
        playerDTOS.stream()
                .filter(p -> p.getId() == playerID)  // Filter te playerDTO by id
                .findFirst()
                .ifPresentOrElse(foundPlayer -> {
                    foundPlayer.setName(playerDTO.getName());
                    foundPlayer.setTeam(playerDTO.getTeam());
                    log.info("Player updated, new name: {}, new team: {}", foundPlayer.getName(), foundPlayer.getTeam());
                }, () -> {
                    //if we could not found the playerDTO
                    log.error("Player with ID {} not found", playerID);
                });
    }

    @Override
    public void editTeam(long teamID, TeamDTO newTeamDTO) {
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
        log.info("All match deleted from Soccer League");

    }

    @Override
    public void deletePlayersOfATeam(String teamName) {
        playerDTOS.removeIf(player -> player.getTeam().equalsIgnoreCase(teamName));
        log.info("All player from team: {} were deleted in Basket League", teamName);
    }

    @PostConstruct
    public void fillSoccerLeagues() {

        // Creating teamDTOS
        TeamDTO teamDTO3 = new TeamDTO("team3", 0, 1);
        TeamDTO teamDTO4 = new TeamDTO("team4", 0, 2);

        //Creating playerDTOS
        PlayerDTO playerDTO1 = new PlayerDTO(1, "Player1", teamDTO3.getName());
        PlayerDTO playerDTO2 = new PlayerDTO(2, "Player2", teamDTO4.getName());

        //Adding Players to teamDTOS
        teamDTO3.addPlayer(playerDTO1);
        teamDTO4.addPlayer(playerDTO2);

        //Adding Teams to league basket
        teamDTOS.add(teamDTO3);
        teamDTOS.add(teamDTO4);

        //Adding PlayerDTO to TeamDTO
        playerDTOS.add(playerDTO1);
        playerDTOS.add(playerDTO2);

        //Creating MatchDTO for basket
        MatchDTO soccerMatchDTO = new MatchDTO(teamDTO3, teamDTO4);
        matchDTOS.add(soccerMatchDTO);

        log.info("Soccer league created and filled.");
    }

}
