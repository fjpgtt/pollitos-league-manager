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
    private List<MatchModel> matchList = new ArrayList<>();
    private final TeamService teamService;
    TeamModel teamvisit;
    TeamModel teamlocal;
    MatchConfig matchConfig;
    @Autowired
    public MatchService(TeamService teamService,MatchConfig matchConfig) {
        this.teamService = teamService;
        this.matchConfig = matchConfig;
        matchList = matchConfig.getMatchconfiglist();
    }

    public MatchModel insertMatch(MatchModel match) {
        this.teamlocal = teamService.getTeamById(match.getLocalteam().getIdteam());
        this.teamvisit = teamService.getTeamById(match.getVisitteam().getIdteam());
        if (teamlocal != null && teamvisit != null) {
            match.setLocalteam(teamlocal);
            match.setVisitteam(teamvisit);
            matchList.add(match);
            matchConfig.setMatchconfiglist(matchList);
            return match;
        }
        return null;
    }

    public boolean deleteMatch() {
        matchList.clear();
        return true;
    }

    public MatchModel getMatchById(int matchId) {
        for (MatchModel match : matchList) {
            if (match.getIdmatch() == matchId) {
                this.teamlocal = match.getLocalteam();
                this.teamvisit = match.getVisitteam();
                return match;
            }
        }
        return null;
    }
}