package com.iwaconsolti.league.service;

import com.iwaconsolti.league.model.PlayerModel;
import com.iwaconsolti.league.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }
    public PlayerModel insertPlayer(PlayerModel player) {
        return playerRepository.save(player);
    }
    public PlayerModel updatePlayer(Integer id, PlayerModel player) {
        return playerRepository.findById(id).map(existingPlayer -> {
            existingPlayer.setNamePlayer(player.getNamePlayer());
            existingPlayer.setIdTeam(player.getIdTeam());
            existingPlayer.setIdLeague(player.getIdLeague());
            return playerRepository.save(existingPlayer);
        }).orElse(null);
    }
    public List<PlayerModel> getPlayersByTeam(Integer teamId) {
        return playerRepository.findPlayersByTeamId(teamId);
    }
}
