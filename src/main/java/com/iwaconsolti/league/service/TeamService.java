package com.iwaconsolti.league.service;

import com.iwaconsolti.league.Config.PlayerConfig;
import com.iwaconsolti.league.Config.TeamConfig;
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
    private TeamConfig teamConfig;


    @Autowired
    public TeamService(PlayerService playerService, TeamConfig teamConfig) {
        this.playerService = playerService;
        this.teamConfig = teamConfig;
    }


    public TeamModel insertTeam(TeamModel team) {
        List<PlayerModel> players = playerService.getPlayer().stream()
                .filter(player -> player.getIdteam() == team.getIdteam())
                .collect(Collectors.toList());
        team.setPlayers(players);
        teamList.add(team);
        teamConfig.setLista(teamList);
        return team;
    }

    public List<TeamModel> getTeam() {
        return teamList;
    }

    public TeamModel updateTeam(int id, TeamModel team) {
        for (TeamModel aux : teamList) {
            if (aux.getIdteam() == id) {
                aux.setIdteam(team.getIdteam());
                aux.setTeamname(team.getTeamname());
                aux.setPlayers(team.getPlayers());
                return aux;
            }
        }
        return null;
    }

    public boolean deleteTeam(int id) {
        for (TeamModel aux : teamList) {
            if (aux.getIdteam() == id) {
                aux.setPlayers(Collections.emptyList());
                return true;
            }
        }
        return true;
    }


    public TeamModel getTeamById(int teamId) {
        return teamList.stream()
                .filter(team -> team.getIdteam() == teamId)
                .findFirst()
                .orElse(null);
    }
}
