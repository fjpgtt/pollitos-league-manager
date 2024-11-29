package com.iwaconsolti.league.service;

import com.iwaconsolti.league.DTO.LeagueDetailsDTO;
import com.iwaconsolti.league.model.LeagueModel;
import com.iwaconsolti.league.model.MatchModel;
import com.iwaconsolti.league.model.PlayerModel;
import com.iwaconsolti.league.model.TeamModel;
import com.iwaconsolti.league.repository.LeagueRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Profile("populated")
public class LeagueService {

    @Getter
    private final LeagueModel soccerLeague;      // Bean
    @Getter
    private final LeagueModel basketballLeague;  // Bean

    private final LeagueRepository leagueRepository;

    public LeagueService(
            @Qualifier("soccer") LeagueModel soccerLeague,
            @Qualifier("Basketball") LeagueModel basketballLeague,
            LeagueRepository leagueRepository
    ) {
        this.soccerLeague = soccerLeague;
        this.basketballLeague = basketballLeague;
        this.leagueRepository = leagueRepository;
    }

    @Transactional
    public void insertLeague(LeagueModel league) {
        leagueRepository.save(league);
    }

    public List<Object[]> findAllByLeagueId(int idLeague) {
        return leagueRepository.findAllByLeagueId(idLeague);
    }


    public LeagueDetailsDTO getLeagueDetailsDTO(int idLeague) {
        List<Object[]> results = findAllByLeagueId(idLeague);

        List<PlayerModel> players = new ArrayList<>();
        List<MatchModel> matches = new ArrayList<>();
        List<TeamModel> teams = new ArrayList<>();


        for (Object[] result : results) {
            PlayerModel player = (PlayerModel) result[0];
            MatchModel match = (MatchModel) result[1];
            TeamModel team = (TeamModel) result[2];

            players.add(player);
            matches.add(match);
            teams.add(team);
        }

        LeagueDetailsDTO leagueDetailsDTO = new LeagueDetailsDTO(players, matches, teams);
        return leagueDetailsDTO;
    }
}
