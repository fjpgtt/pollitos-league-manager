package com.iwaconsolti.league.service;
import com.iwaconsolti.league.model.PlayerModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerService {

    private final List<PlayerModel> playerList = new ArrayList<>();



    public PlayerModel insertPlayer(PlayerModel player) {
        int teamid = player.getIdEquipo();
        this.playerList.add(player);
        return player;
    }

    public List<PlayerModel> getPlayer() {
        return playerList;
    }

    public PlayerModel updatePlayer(int id, PlayerModel Player) {
        for (PlayerModel aux : playerList) {
            if (aux.getIdJugador() == id) {
                aux.setIdJugador(Player.getIdJugador());
                aux.setNombreJugador(Player.getNombreJugador());
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
            if (player.getIdEquipo() == playerTeam) {
                return player;
            }
        }
        return null;

    }


    }
