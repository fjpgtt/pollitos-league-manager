package com.iwaconsolti.league.demo.service;

import com.iwaconsolti.league.demo.dto.PlayerDTO;
import com.iwaconsolti.league.demo.entity.PlayerEntity;
import com.iwaconsolti.league.demo.entity.TeamEntity;
import com.iwaconsolti.league.demo.repository.PlayerRepository;
import com.iwaconsolti.league.demo.repository.TeamRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository, TeamRepository teamRepository) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
    }

    public List<PlayerEntity> getPlayersByTeam(String teamName) {
        return playerRepository.findByTeam(teamName);
    }
    //adding transactional in order to delete
    @Transactional
    public void deletePlayersByTeam(String teamName) {
        playerRepository.deleteByTeam(teamName);
        log.info("All player from team: {} were deleted", teamName);
    }

    public void createPlayer(PlayerDTO playerDTO, String league) {
        TeamEntity teamEntity = teamRepository.findByName(playerDTO.getTeam());


        if (teamEntity == null) {
            teamEntity = new TeamEntity();
            teamEntity.setName(playerDTO.getTeam());
            teamEntity.setScore(0);
            teamEntity.setLeague(league);
            teamRepository.save(teamEntity);
            log.info("Team not found but created: {}", playerDTO.getTeam());
        }
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
            TeamEntity teamEntity = teamRepository.findByName(playerDTO.getTeam());
            if (teamEntity == null) {
                teamEntity = new TeamEntity();
                teamEntity.setName(playerDTO.getTeam());
                teamEntity.setScore(0);
                teamEntity.setLeague(playerEntity.getTeam().getLeague());
                if (teamEntity.getLeague() == null) {
                    teamEntity.setLeague(playerEntity.getTeam().getLeague());
                    teamRepository.save(teamEntity);
                    log.info("Team {} updated with league {}", teamEntity.getName(), teamEntity.getLeague());
                }
                teamRepository.save(teamEntity);
                log.info("Team not found but created: {}", playerDTO.getTeam());
            }
            //Updating player
            playerEntity.setName(playerDTO.getName());
            playerEntity.setTeam(teamEntity);
            playerRepository.save(playerEntity);
            log.info("Player updated: {}", playerDTO.getName());
        } else {
            log.error("Player with ID {} not found", playerID);
        }
    }

    public List<PlayerDTO> getAllPlayers(String teamName) {
        log.info("Returning all players from Team: {}", teamName);

        List<PlayerEntity> playerEntities = playerRepository.findAll();
        List<PlayerDTO> playersDTO = new ArrayList<>();
        for (PlayerEntity playerEntity : playerEntities) {
            if(playerEntity.getTeam().getName().equalsIgnoreCase(teamName)){
            playersDTO.add(new PlayerDTO(playerEntity.getID(), playerEntity.getName(), playerEntity.getTeam().getName()));
            }
        }
        return playersDTO;
    }
}
