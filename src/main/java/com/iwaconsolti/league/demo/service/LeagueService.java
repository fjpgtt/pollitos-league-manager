package com.iwaconsolti.league.demo.service;

import com.iwaconsolti.league.demo.model.League;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LeagueService {
    private final List<League> leagueList = new ArrayList<>();
    private long nextId = 1L;

    public League createLeague(League league) {
        if (league.getMaxTeams() <= 0) {
            league.setMaxTeams(10);
        }
        league.setId(nextId++);
        leagueList.add(league);
        return league;
    }

    public Optional<League> findLeagueById(long id) {
        return leagueList.stream()
                .filter(league -> league.getId() == (id))
                .findFirst();
    }

    public List<League> findAllLeagues() {
        return new ArrayList<>(leagueList);
    }

    public boolean canAddTeamToLeague(League league) {
        return league.getTeamList().size() < league.getMaxTeams();
    }
}
