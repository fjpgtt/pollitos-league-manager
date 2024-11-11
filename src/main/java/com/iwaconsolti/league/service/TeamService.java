package com.iwaconsolti.league.service;

import com.iwaconsolti.league.model.PlayerModel;
import com.iwaconsolti.league.model.TeamModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamService {

    private final List<TeamModel> teamList = new ArrayList<>();
    private final PlayerService playerService;


    @Autowired
    public TeamService(PlayerService playerService) {
        this.playerService = playerService;
    }


    public TeamModel insertTeam(TeamModel team) {
        List<PlayerModel> players = playerService.getPlayer().stream()
                .filter(player -> player.getIdEquipo() == team.getId())
                .collect(Collectors.toList());
        team.setJugadores(players);
        teamList.add(team);

        return team;
    }

    public List<TeamModel> getTeam() {
        return teamList;
    }

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

    public boolean deleteTeam(int id) {
        for (TeamModel aux : teamList) {
            if (aux.getId() == id) {
                aux.setJugadores(Collections.emptyList());
                return true;
            }
        }
        return true;
    }


    public TeamModel getTeamById(int teamId) {
        return teamList.stream()
                .filter(team -> team.getId() == teamId)
                .findFirst()
                .orElse(null);
    }
}
