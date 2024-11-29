package com.iwaconsolti.league.service;

import com.iwaconsolti.league.model.MatchModel;
import com.iwaconsolti.league.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MatchService {

    private final MatchRepository matchRepository;

    @Autowired
    public MatchService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }
    public List<MatchModel> getMatches() {
        return matchRepository.findAllMatches();
    }
    @Transactional
    public MatchModel insertMatch(MatchModel match) {
        return matchRepository.save(match);
    }
    @Transactional
    public boolean deleteAllMatches() {
        matchRepository.deleteAllMatches();
        return true;
    }
    public List<MatchModel> getMatchById(int teamId) {
        return matchRepository.findMatchesByTeamId(teamId);
    }
}
