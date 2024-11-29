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
import java.util.stream.Collectors;

@Service
@Slf4j
public class TeamService {
    @Value("${league.teamLimit:10}")
    private int teamLimit;

    private final TeamRepository teamRepository;

    @Autowired
    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public TeamEntity createTeam(TeamDTO teamDTO) {
        if (teamRepository.findByName(teamDTO.getName()) != null) {
            log.warn("Team {} already exist in the league", teamDTO.getName());
        }

        if (teamRepository.count() >= teamLimit) {
            log.error("You reached the max of team per league, max: {}", teamLimit);
        }

            TeamEntity teamEntity = new TeamEntity();
            teamEntity.setName(teamDTO.getName());
            teamEntity.setLeague(teamDTO.getLeague());
            teamRepository.save(teamEntity);
            log.info("Team {} added to {}", teamDTO.getName(), teamDTO.getLeague());
            return teamEntity;

    }

    public List<TeamDTO> getAllTeams() {
        List<TeamEntity> teamEntities = teamRepository.findAll();
        if (teamEntities.isEmpty()) {
            log.warn("No teams found in the repository.");
            return new ArrayList<>();
        }

        List<TeamDTO> teamDTOS = teamEntities.stream()
                .map(teamEntity -> new TeamDTO(
                        teamEntity.getId(),
                        teamEntity.getName(),
                        teamEntity.getPlayers().stream()
                                .map(player -> new PlayerDTO(player.getId(), player.getName(), player.getTeam().getName()))
                                .collect(Collectors.toList()),
                        teamEntity.getLeague()))
                .collect(Collectors.toList());

//        log.info("Returning all teams in the repository.");
        return teamDTOS;
    }

    public String editTeam(long teamID, TeamDTO newTeamDTO) {
        TeamEntity teamEntity = teamRepository.findById(teamID).orElse(null);
        if (teamEntity != null) {
            teamEntity.setName(newTeamDTO.getName());
            teamEntity.setLeague(newTeamDTO.getLeague());
            teamRepository.save(teamEntity);
            log.info("Team with ID: {} has been updated.", teamID);
            return "Team with ID " + teamID + " has been successfully updated.";

        }
        log.error("Team with ID: {} not found", teamID);
        return "Team with ID " + teamID + " not found. Please check the provided ID.";
    }

    public List<PlayerDTO> getPlayersByTeam(String teamName) {
        TeamEntity team = teamRepository.findByName(teamName);

        if (team == null) {
            log.warn("Team {} not found", teamName);
            return new ArrayList<>();
        }

        List<PlayerDTO> playerDTOS = new ArrayList<>();
        for (PlayerEntity playerEntity : team.getPlayers()) {
            playerDTOS.add(new PlayerDTO(playerEntity.getId(), playerEntity.getName()));
        }

        log.info("Players found in team {} : {}", teamName, playerDTOS);
        return playerDTOS;
    }


}
