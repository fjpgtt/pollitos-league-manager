package com.iwaconsolti.league.service;

import com.iwaconsolti.league.Config.PlayerConfig;
import com.iwaconsolti.league.model.PlayerModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class PlayerService {
    private final PlayerConfig playerConfig;
    private final List<PlayerModel> playerList = new ArrayList<>();

    public PlayerService(PlayerConfig playerConfig) {
        this.playerConfig = playerConfig;
    }

    public PlayerModel insertPlayer(PlayerModel player) {
        this.playerList.add(player);
        playerConfig.setPlayerconfiglist(playerList);
        return player;
    }

    public List<PlayerModel> getPlayer() {
        return playerList;
    }

    public PlayerModel updatePlayer(int id, PlayerModel Player) {
        for (PlayerModel aux : playerList) {
            if (aux.getIdpLayer() == id) {
                aux.setIdpLayer(Player.getIdpLayer());
                aux.setNameplayer(Player.getNameplayer());
                return aux;
            }
        }
        return null;
    }

        public List<PlayerModel> getPlayersByTeamId(Integer playerTeam) {
            if (playerTeam == null) {
                System.out.println("The provided playerTeam ID is null");
                return Collections.emptyList();
            }

            List<PlayerModel> playersWithTeam = new ArrayList<>();
            for (PlayerModel player : playerList) {
                if (player.getIdteam() == playerTeam) {
                    playersWithTeam.add(player);
                }
            }
            return playersWithTeam;
        }
    }