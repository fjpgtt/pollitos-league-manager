package com.iwaconsolti.league.service;

import com.iwaconsolti.league.model.MatchModel;
import com.iwaconsolti.league.model.TeamModel;
import com.iwaconsolti.league.repository.MatchRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public MatchService() {
    }

    public List<MatchModel> getMatches() {
        return entityManager.createQuery("SELECT m FROM MatchModel m", MatchModel.class)
                .getResultList();
    }

    @Transactional
    public MatchModel insertMatch(MatchModel match) {
        String sql = "INSERT INTO MATCH (IDMATCH, GOAL_VISIT, GOALLOCAL, IDLEAGUE, LOCALTEAMID, VISITTEAMID) " +
                "VALUES (:idMatch, :goalVisit, :goalLocal, :idLeague, :localTeamId, :visitTeamId)";

        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("idMatch", match.getIdmatch());
        query.setParameter("goalVisit", match.getGoalVisit());
        query.setParameter("goalLocal", match.getGoallocal());
        query.setParameter("idLeague", match.getIdleague());
        query.setParameter("localTeamId", match.getLocalteamid());
        query.setParameter("visitTeamId", match.getVisitteamid());
        query.executeUpdate();
        return match;
    }


    @Transactional
    public boolean deleteAllMatches() {
        int deletedCount = entityManager.createQuery("DELETE FROM MatchModel m")
                .executeUpdate();
        return deletedCount > 0;
    }

    public List<MatchModel> getMatchById(int teamId) {
        try {
            List<MatchModel> matches = entityManager.createQuery(
                            "SELECT m FROM MatchModel  m WHERE m.localteamid = :teamId OR m.visitteamid = :teamId",
                            MatchModel.class)
                    .setParameter("teamId", teamId)
                    .getResultList();

            return matches;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}