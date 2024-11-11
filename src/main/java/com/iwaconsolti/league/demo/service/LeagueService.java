package com.iwaconsolti.league.demo.service;

import com.iwaconsolti.league.demo.model.League;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LeagueService {
    private static final Logger logger = LoggerFactory.getLogger(LeagueService.class);
    private final List<League> leagues = new ArrayList<>();
    private Long nextId = 1L;

    public League createLeague(League league) {
        if (league.getMaxTeams() <= 0) {
            league.setMaxTeams(10); // default value
        }
        league.setId(nextId++);
        leagues.add(league);
        return league;
    }

    public Optional<League> getLeagueById(Long id) {
        return leagues.stream()
                .filter(league -> league.getId().equals(id))
                .findFirst();
    }

    public List<League> getAllLeagues() {
        return new ArrayList<>(leagues);
    }

    public boolean canAddTeamToLeague(League league) {
        return league.getTeams().size() < league.getMaxTeams();
    }
}
