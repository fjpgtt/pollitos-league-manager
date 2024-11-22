package com.iwaconsolti.league.demo.service;

import com.iwaconsolti.league.demo.model.League;
import com.iwaconsolti.league.demo.repository.LeagueRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LeagueService {
    private final LeagueRepository leagueRepository;

    public LeagueService(LeagueRepository leagueRepository) {
        this.leagueRepository = leagueRepository;
    }

    public League createLeague(League league) {
        if (league.getMaxTeams() <= 0) {
            league.setMaxTeams(10);
        }
        return leagueRepository.save(league);
    }

    public Optional<League> findLeagueById(long id) {
        return leagueRepository.findById(id);
    }

    public List<League> findAllLeagues() {
        return leagueRepository.findAll();
    }

    public boolean canAddTeamToLeague(League league) {
        return league.getTeamList().size() < league.getMaxTeams();
    }
}
