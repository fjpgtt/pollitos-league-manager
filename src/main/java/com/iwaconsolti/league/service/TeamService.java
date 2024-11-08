package com.iwaconsolti.league.service;

import com.iwaconsolti.league.model.TeamModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class TeamService {

    private final List<TeamModel> team = new ArrayList<>();


    public TeamModel insertTeam(TeamModel Team) {
        team.add(Team);
        return Team;
    }
    public List<TeamModel> getTeam() {
        return team;
    }

    public TeamModel updateTeam(int id, TeamModel Team) {
        for (TeamModel aux : team) {
            if (aux.getId() == id) {
                aux.setId(Team.getId());
                aux.setNombre(Team.getNombre());
                aux.setJugadores(Team.getJugadores());
                return aux;
            }
        }
        return null;
    }

    public boolean deleteTeam(int id) {
        return team.removeIf(p -> p.getId() == id);
    }
    }


