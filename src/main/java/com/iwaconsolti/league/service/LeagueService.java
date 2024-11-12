package com.iwaconsolti.league.service;

import com.iwaconsolti.league.model.LeagueModel;
import com.iwaconsolti.league.model.TeamModel;
import com.iwaconsolti.league.model.PlayerModel;
import com.iwaconsolti.league.model.MatchModel;
import com.iwaconsolti.league.Config.MatchConfig;
import com.iwaconsolti.league.Config.TeamConfig;
import com.iwaconsolti.league.Config.PlayerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeagueService {

    private final LeagueModel soccerLeague;      //Bean
    private final LeagueModel basketballLeague;  //Bean

    private final MatchConfig matchConfig;
    private final TeamConfig teamConfig;
    private final PlayerConfig playerConfig;

    @Autowired
    public LeagueService(
            @Qualifier("soccer") LeagueModel soccerLeague,
            @Qualifier("Basketball") LeagueModel basketballLeague,
            MatchConfig matchConfig,
            TeamConfig teamConfig,
            PlayerConfig playerConfig
    ) {
        this.soccerLeague = soccerLeague;
        this.basketballLeague = basketballLeague;
        this.matchConfig = matchConfig;
        this.teamConfig = teamConfig;
        this.playerConfig = playerConfig;
    }

    public LeagueModel insertLeague(LeagueModel league) {
        // Insert Matches
        List<MatchModel> matches = matchConfig.getLista();
        league.setPartidos(matches);

        // Insert Teams
        List<TeamModel> teams = teamConfig.getLista();
        league.setEquipos(teams);

        // Insertar Players
        List<PlayerModel> players = playerConfig.getLista();
        league.setJugadores(players);
league.setIdLeague(1);
        // Insertar en la liga específica según el idLeague
        if (league.getIdLeague() == 1) { // Suponiendo que '1' es para Soccer
            soccerLeague.setNombre(league.getNombre());
            soccerLeague.setEquipos(league.getEquipos());
            soccerLeague.setJugadores(league.getJugadores());
            soccerLeague.setPartidos(league.getPartidos());
            return soccerLeague;
        } else if (league.getIdLeague() == 2) { // Suponiendo que '2' es para Basketball
            basketballLeague.setNombre(league.getNombre());
            basketballLeague.setEquipos(league.getEquipos());
            basketballLeague.setJugadores(league.getJugadores());
            basketballLeague.setPartidos(league.getPartidos());
            return basketballLeague;
        }

        return null;
    }

    public LeagueModel getSoccerLeague() {
        return soccerLeague;
    }

    public LeagueModel getBasketballLeague() {
        return basketballLeague;
    }
}
