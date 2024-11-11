package com.iwaconsolti.league.service;

import com.iwaconsolti.league.model.LeagueModel;
import com.iwaconsolti.league.model.TeamModel;
import com.iwaconsolti.league.model.PlayerModel;
import com.iwaconsolti.league.model.MatchModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeagueService {

    // Lista para almacenar las ligas en memoria
    private final List<LeagueModel> leagueList;

    // Inyección de la dependencia MatchService, TeamService y PlayerService
    private final MatchService matchService;
    private final TeamService teamService;
    private final PlayerService playerService;

    // Constructor con la inyección de dependencias
    @Autowired
    public LeagueService(MatchService matchService, TeamService teamService, PlayerService playerService) {
        this.leagueList = new ArrayList<>();
        this.matchService = matchService;
        this.teamService = teamService;
        this.playerService = playerService;
    }

    // Método para insertar una nueva liga
    public LeagueModel insertLeague(LeagueModel league) {
        // Filtrar partidos con el mismo idLiga
        List<MatchModel> matches = matchService.getMatch().stream()
                .filter(match -> match.getIdLiga() == league.getIdLeague())  // Filtrar partidos con el mismo idLiga
                .collect(Collectors.toList());  // Recoger los partidos filtrados en una lista
        league.setPartidos(matches);

        // Filtrar equipos con el mismo idLiga
        List<TeamModel> teams = teamService.getTeam().stream()
                .filter(team -> team.getIdLiga() == league.getIdLeague())  // Filtrar equipos con el mismo idLiga
                .collect(Collectors.toList());  // Recoger los equipos filtrados en una lista
        league.setEquipos(teams);

        // Filtrar jugadores con el mismo idLiga
        List<PlayerModel> players = playerService.getPlayer().stream()
                .filter(player -> player.getIdLiga() == league.getIdLeague())  // Filtrar jugadores con el mismo idLiga
                .collect(Collectors.toList());  // Recoger los jugadores filtrados en una lista
        league.setJugadores(players);

        // Agregar la liga a la lista
        leagueList.add(league);
        return league;
    }

    // Método para obtener todas las ligas
    public List<LeagueModel> getAllLeagues() {
        return leagueList; // Devuelve la lista de ligas
    }
}
