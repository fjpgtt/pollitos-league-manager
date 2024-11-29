package com.iwaconsolti.league.manager.service.impl;

import com.iwaconsolti.league.manager.persistence.repository.IMatchesRepository;
import com.iwaconsolti.league.manager.persistence.repository.IPlayersRepository;
import com.iwaconsolti.league.manager.persistence.repository.ITeamsRepository;
import org.springframework.stereotype.Service;


@Service("soccerLeague")
public class SoccerLeagueImpl extends ALeague {

    public SoccerLeagueImpl(IPlayersRepository playersRepository, ITeamsRepository teamsRepository, IMatchesRepository matchesRepository) {
        super(playersRepository, teamsRepository, matchesRepository);
    }

}
