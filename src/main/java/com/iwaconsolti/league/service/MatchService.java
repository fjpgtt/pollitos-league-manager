package com.iwaconsolti.league.service;

import com.iwaconsolti.league.Config.MatchConfig;
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
    MatchConfig matchConfig;
    @Autowired
    public MatchService(TeamService teamService,MatchConfig matchConfig) {
        this.teamService = teamService;
        this.matchConfig = matchConfig;

    }


    public MatchModel insertMatch(MatchModel match) {
      this.equipoLocal = teamService.getTeamById(match.getLocalteam().getIdteam());
        this.equipoVisitante = teamService.getTeamById(match.getVisitteam().getIdteam());
        if (equipoLocal != null && equipoVisitante != null) {
            match.setLocalteam(equipoLocal);
            match.setVisitteam(equipoVisitante);
            matchList.add(match);
            matchConfig.setLista(matchList);
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
            if (match.getIdmatch() == matchId) {
                this.equipoLocal = match.getLocalteam();
                this.equipoVisitante = match.getVisitteam();
                return match;
            }
        }

        return null;
    }
}
