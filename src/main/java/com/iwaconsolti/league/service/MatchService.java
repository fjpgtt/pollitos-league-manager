package com.iwaconsolti.league.service;

import com.iwaconsolti.league.model.MatchModel;
import com.iwaconsolti.league.model.TeamModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MatchService {

    private final List<MatchModel> matchList = new ArrayList<>();
    private final TeamService teamService; // Inyección de dependencias para TeamService
    TeamModel equipoVisitante;
    TeamModel equipoLocal;
    @Autowired
    public MatchService(TeamService teamService) {
        this.teamService = teamService;

    }


    public MatchModel insertMatch(MatchModel match) {
      this.equipoLocal = teamService.getTeamById(match.getEquipoLocal().getId());
        this.equipoVisitante = teamService.getTeamById(match.getEquipoVisitante().getId());
        if (equipoLocal != null && equipoVisitante != null) {
            match.setEquipoLocal(equipoLocal);
            match.setEquipoVisitante(equipoVisitante);
            matchList.add(match);
            return match;
        }
        return null;
    }

    public List<MatchModel> getMatch() {
        return matchList;
    }

    public boolean deleteMatch() {
        matchList.clear();
        return true;
    }

    public MatchModel getMatchById(int matchId) {
        for (MatchModel match : matchList) {
            if (match.getIdPartido() == matchId) {
                this.equipoLocal = match.getEquipoLocal();
                this.equipoVisitante = match.getEquipoVisitante();
                return match;
            }
        }

        return null;
    }
}
