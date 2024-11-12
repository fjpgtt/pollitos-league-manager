package com.iwaconsolti.league.service;
import com.iwaconsolti.league.Config.PlayerConfig;
import com.iwaconsolti.league.model.PlayerModel;
import com.iwaconsolti.league.model.TeamModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerService {
    private PlayerConfig playerConfig;

    private final List<PlayerModel> playerList = new ArrayList<>();

public PlayerService(PlayerConfig playerConfig){
this.playerConfig = playerConfig;

}

    public PlayerModel insertPlayer(PlayerModel player) {
        int teamid = player.getIdteam();
        this.playerList.add(player);
        playerConfig.setLista(playerList);
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



    public PlayerModel getPlayerById(Integer playerTeam) {
        if (playerTeam == null) {
            System.out.println("El playerId proporcionado es null");
            return null;
        }

        for (PlayerModel player : playerList) {
            if (player.getIdteam() == playerTeam) {
                return player;
            }
        }
        return null;

    }
    public List<PlayerModel> getPlayers() {
        return playerList;
    }

    }
