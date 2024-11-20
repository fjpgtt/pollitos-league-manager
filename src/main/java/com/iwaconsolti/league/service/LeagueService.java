package com.iwaconsolti.league.service;

import com.iwaconsolti.league.model.LeagueModel;
import com.iwaconsolti.league.model.MatchModel;
import com.iwaconsolti.league.model.PlayerModel;
import com.iwaconsolti.league.model.TeamModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile("default")
public class LeagueService {
    @Getter
    private final LeagueModel soccerLeague;      // Bean
    @Getter
    private final LeagueModel basketballLeague;  // Bean

    @PersistenceContext
    private EntityManager entityManager;

    public LeagueService(
            @Qualifier("soccer") LeagueModel soccerLeague,
            @Qualifier("Basketball") LeagueModel basketballLeague
    ) {
        this.soccerLeague = soccerLeague;
        this.basketballLeague = basketballLeague;
    }

    @Transactional
    public void insertLeague(LeagueModel league, int idLeague) {


    }

    public List<LeagueModel> findAllLeagues() {
        return entityManager.createQuery(
                        "SELECT l FROM LeagueModel l", LeagueModel.class)
                .getResultList();
    }

    public LeagueModel findLeagueById(int idLeague) {
        return entityManager.createQuery(
                        "SELECT l FROM LeagueModel l WHERE l.idLeague = :idLeague", LeagueModel.class)
                .setParameter("idLeague", idLeague)
                .getSingleResult();
    }

}
