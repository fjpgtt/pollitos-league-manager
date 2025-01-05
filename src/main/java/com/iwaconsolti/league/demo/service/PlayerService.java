package com.iwaconsolti.league.demo.service;

import com.iwaconsolti.league.demo.dto.PlayerDTO;
import com.iwaconsolti.league.demo.dto.TeamDTO;
import com.iwaconsolti.league.demo.entity.PlayerEntity;
import com.iwaconsolti.league.demo.entity.TeamEntity;
import com.iwaconsolti.league.demo.repository.PlayerRepository;
import com.iwaconsolti.league.demo.repository.TeamRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;
    private final TeamService teamService;

    @Autowired
    public PlayerService(PlayerRepository playerRepository, TeamRepository teamRepository, TeamService teamService) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
        this.teamService = teamService;
    }

    //adding transactional in order to delete
    @Transactional
    public void deletePlayersByTeam(String teamName) {
        playerRepository.deleteByTeam_Name(teamName);
        log.info("All players from team: {} were deleted", teamName);
    }

    public void createPlayer(PlayerDTO playerDTO, String league) {
        TeamEntity teamEntity = existTeam(playerDTO.getTeam(), league);

        PlayerEntity playerEntity = new PlayerEntity();
        playerEntity.setName(playerDTO.getName());
        playerEntity.setTeam(teamEntity);
        playerRepository.save(playerEntity);

        log.info("Player created: {}", playerDTO.getName());
        log.info("Player added to {} in team {}", league, playerDTO.getTeam());
    }


    public void editPlayer(long playerID, PlayerDTO playerDTO) {
        PlayerEntity playerEntity = playerRepository.findById(playerID).orElse(null);
        if (playerEntity != null) {
            TeamEntity teamEntity = existTeam(playerDTO.getTeam(), playerEntity.getTeam().getLeague());

            //Updating player
            playerEntity.setName(playerDTO.getName());
            playerEntity.setTeam(teamEntity);
            playerRepository.save(playerEntity);
            log.info("Player updated: {}", playerDTO.getName());
        } else {
            log.error("Player with ID {} not found", playerID);
        }
    }

    public List<PlayerDTO> getAllPlayersByTeam(String teamName) {
//        log.info("Returning all players from Team: {}", teamName);

        return playerRepository.findAll()
                .stream()
                .filter(player -> player.getTeam().getName().equalsIgnoreCase(teamName))
                .map(player -> new PlayerDTO(player.getId(), player.getName(), player.getTeam().getName()))
                .collect(Collectors.toList());
    }

    public TeamEntity existTeam(String teamName, String league) {
        TeamEntity teamEntity = teamRepository.findByName(teamName);
        if (teamEntity == null) {
            TeamDTO teamDTO = new TeamDTO();
            teamDTO.setName(teamName);
            teamDTO.setLeague(league);
            teamService.createTeam(teamDTO);
            log.info("Team not found but created: {}", teamName);

            teamEntity = teamRepository.findByName(teamName);
        }
        log.info("Team {} already exist.", teamName);
        return teamEntity;
    }

}
