package com.iwaconsolti.league.demo.service;

import com.iwaconsolti.league.demo.entity.PlayerEntity;
import com.iwaconsolti.league.demo.repository.PlayerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
public class PlayerService {
    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<PlayerEntity> getPlayersByTeam(String teamName) {
        return playerRepository.findByTeam(teamName);
    }

    public void deletePlayersByTeam(String teamName) {
        playerRepository.deleteByTeam(teamName);
    }

    public PlayerEntity createPlayer(PlayerEntity player) {
        log.info("Player created: {}", player.getName());
        return playerRepository.save(player);
    }
}
