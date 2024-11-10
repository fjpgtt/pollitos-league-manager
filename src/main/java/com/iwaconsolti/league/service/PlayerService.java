package com.iwaconsolti.league.service;

import com.iwaconsolti.league.model.PlayerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerService {

    private final List<PlayerModel> playerList = new ArrayList<>();


    // Inyección de dependencias a través del constructor


    public PlayerModel insertPlayer(PlayerModel player) {
        int teamid = player.getIdEquipo();  // Suponiendo que el idJugador es el id del equipo (ajustar si es necesario)
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

    public boolean deletePlayer(int id) {
        return playerList.removeIf(p -> p.getIdJugador() == id);
    }

    public PlayerModel getPlayerById(int playerId) {
        return playerList.stream()
                .filter(player -> player.getIdJugador() == playerId)
                .findFirst()
                .orElse(null);
    }
}
