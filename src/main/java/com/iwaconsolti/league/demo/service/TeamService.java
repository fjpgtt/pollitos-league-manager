package com.iwaconsolti.league.demo.service;

import com.iwaconsolti.league.demo.dto.PlayerDTO;
import com.iwaconsolti.league.demo.dto.TeamDTO;
import com.iwaconsolti.league.demo.entity.PlayerEntity;
import com.iwaconsolti.league.demo.entity.TeamEntity;
import com.iwaconsolti.league.demo.repository.TeamRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class TeamService {
    @Value("league.teamLimit:10")
    private final int TEAMLIMIT;
    private final TeamRepository teamRepository;

    @Autowired
    public TeamService(int teamlimit, TeamRepository teamRepository) {
        TEAMLIMIT = teamlimit;
        this.teamRepository = teamRepository;
    }

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

    public List<TeamDTO> getAllTeams() {
        List<TeamEntity> teamEntities = teamRepository.findAll();
        List<TeamDTO> teamDTOS = new ArrayList<>();
        for (TeamEntity teamEntity : teamEntities) {
            // Since we cannot add TeamEntity because we need DTOS we created:
            List<PlayerDTO> playerDTOS = new ArrayList<>();
            for (PlayerEntity playerEntity : teamEntity.getPlayers()) {
                playerDTOS.add(new PlayerDTO(playerEntity.getID(), playerEntity.getName(), playerEntity.getTeam().getName()));
            }

            teamDTOS.add(new TeamDTO(teamEntity.getID(),
                    teamEntity.getScore(),
                    teamEntity.getName(),
                    playerDTOS));  //Adding the players
        }
        log.info("Returning all the teams in the repository");
        return teamDTOS;
    }

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

    public List<PlayerDTO> getPlayersByTeam(String teamName){
        TeamEntity team = teamRepository.findByName(teamName);
        List<PlayerDTO> playerDTOS = new ArrayList<>();
        
        for(PlayerEntity playerEntity : team.getPlayers()){
            playerDTOS.add(new PlayerDTO(playerEntity.getID(), playerEntity.getName()));
        }

        log.info("Players found in team {} : {}", teamName, playerDTOS );
        return playerDTOS;
    }
}
