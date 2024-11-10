package com.iwaconsolti.league.service;

import com.iwaconsolti.league.model.PlayerModel;
import com.iwaconsolti.league.model.TeamModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeamService {

    private final List<TeamModel> teamList = new ArrayList<>();
    private final PlayerService playerService;  // Instancia de PlayerService inyectada

    // Constructor para inyección de dependencias
    @Autowired
    public TeamService(PlayerService playerService) {
        this.playerService = playerService;
    }

    // Insertar un nuevo equipo
    public TeamModel insertTeam(TeamModel team) {
        List<PlayerModel> players = playerService.getPlayer().stream()
                .filter(player -> player.getIdEquipo() == team.getId())  // Filtrar jugadores con el mismo idEquipo
                .collect(Collectors.toList());  // Recoger los jugadores filtrados en una lista
        team.setJugadores(players);
        teamList.add(team);

        return team;
    }

    // Obtener todos los equipos
    public List<TeamModel> getTeam() {
        return teamList;
    }

    // Actualizar un equipo
    public TeamModel updateTeam(int id, TeamModel team) {
        for (TeamModel aux : teamList) {
            if (aux.getId() == id) {
                aux.setId(team.getId());
                aux.setNombre(team.getNombre());
                aux.setJugadores(team.getJugadores());
                return aux;
            }
        }
        return null;
    }

    // Eliminar un equipo (borrar jugadores del equipo)
    public boolean deleteTeam(int id) {
        for (TeamModel aux : teamList) {
            if (aux.getId() == id) {
                aux.setJugadores(Collections.emptyList());
                return true;
            }
        }
        return true;
    }

    // Agregar un jugador a un equipo
    public TeamModel addPlayerToTeam(int teamId, int playerId) {
        // Buscar el equipo por ID
        Optional<TeamModel> teamOptional = teamList.stream()
                .filter(team -> team.getId() == teamId)
                .findFirst();

        if (!teamOptional.isPresent()) {
            return null;  // Si no se encuentra el equipo
        }
        TeamModel team = teamOptional.get();
        // Buscar el jugador por ID utilizando el servicio PlayerService
        PlayerModel player = playerService.getPlayerById(playerId);  // Usamos la instancia del servicio correctamente
        if (player == null) {
            return null;  // Si no se encuentra el jugador
        }
        // Agregar el jugador a la lista de jugadores del equipo
        team.getJugadores().add(player);
        return team;
    }

    public TeamModel getTeamById(int teamId) {
        return teamList.stream()
                .filter(team -> team.getId() == teamId)
                .findFirst()
                .orElse(null);
    }
}
