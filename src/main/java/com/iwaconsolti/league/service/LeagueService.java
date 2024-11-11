package com.iwaconsolti.league.service;

import com.iwaconsolti.league.model.LeagueModel;
import com.iwaconsolti.league.model.TeamModel;
import com.iwaconsolti.league.model.PlayerModel;
import com.iwaconsolti.league.model.MatchModel;
import com.iwaconsolti.league.Config.LeagueConfig;
import com.iwaconsolti.league.Config.TeamConfig;
import com.iwaconsolti.league.Config.PlayerConfig;
import com.iwaconsolti.league.Config.MatchConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Profile("populated")
public class LeagueService {

    private final List<LeagueModel> leagueList;

    private final MatchConfig matchConfig;
    private final TeamConfig teamConfig;
    private final PlayerConfig playerConfig;
    private final LeagueConfig leagueConfig;
    // Constructor con la inyección de dependencias
    @Autowired
    public LeagueService(MatchConfig matchConfig, TeamConfig teamConfig, PlayerConfig playerConfig, LeagueConfig leagueConfig) {
        //this.leagueList = new ArrayList<>();
        this.leagueList = leagueConfig.getLista();
        this.matchConfig = matchConfig;
        this.teamConfig = teamConfig;
        this.playerConfig = playerConfig;
        this.leagueConfig = leagueConfig;
    }

    // Método para insertar una nueva liga
    public LeagueModel insertLeague(LeagueModel league) {
        // Filtrar partidos con el mismo idLiga
        List<MatchModel> matches = matchConfig.getLista().stream()
                .filter(match -> match.getIdLiga() == league.getIdLeague())
                .collect(Collectors.toList());
        league.setPartidos(matches);

        // Filtrar equipos con el mismo idLiga
        List<TeamModel> teams = teamConfig.getLista().stream()
                .filter(team -> team.getIdLiga() == league.getIdLeague())
                .collect(Collectors.toList());
        league.setEquipos(teams);

        // Filtrar jugadores con el mismo idLiga
        List<PlayerModel> players = playerConfig.getLista().stream()
                .filter(player -> player.getIdLiga() == league.getIdLeague())
                .collect(Collectors.toList());
        league.setJugadores(players);

        // Agregar la liga a la lista
        leagueList.add(league);
        return league;
    }

    // Método para obtener todas las ligas
    public List<LeagueModel> getAllLeagues() {
        return leagueList;
    }
}
