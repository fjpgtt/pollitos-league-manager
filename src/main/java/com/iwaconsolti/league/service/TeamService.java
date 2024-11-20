package com.iwaconsolti.league.service;

import com.iwaconsolti.league.Config.TeamConfig;
import com.iwaconsolti.league.model.PlayerModel;
import com.iwaconsolti.league.model.TeamModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;


@Service
public class TeamService {

    @PersistenceContext
    private EntityManager entityManager;

    private final PlayerService playerService;
    private final TeamConfig teamConfig;

    @Autowired
    public TeamService(PlayerService playerService, TeamConfig teamConfig) {
        this.playerService = playerService;
        this.teamConfig = teamConfig;
    }

    @Transactional
    public TeamModel insertTeam(TeamModel team) {
        String insertTeamSql = "INSERT INTO team (IDTEAM, ID_LEAGUE, TEAMNAME) VALUES (:idteam, :idleague, :name)";
        Query query = entityManager.createNativeQuery(insertTeamSql);
        query.setParameter("idteam", team.getIdteam());
        query.setParameter("idleague", team.getIdLeague());
        query.setParameter("name", team.getTeamname());
        query.executeUpdate();
        return team;
    }

    public List<TeamModel> getTeam() {
        return entityManager.createQuery("SELECT t FROM TeamModel t", TeamModel.class)
                .getResultList();
    }

    @Transactional
    public TeamModel updateTeam(int id, TeamModel team) {
        TeamModel existingTeam = entityManager.createQuery(
                        "SELECT t FROM TeamModel t WHERE t.idteam = :id", TeamModel.class)
                .setParameter("id", id)
                .getSingleResult();

        if (existingTeam != null) {
            existingTeam.setIdteam(team.getIdteam());
            existingTeam.setTeamname(team.getTeamname());
            entityManager.merge(existingTeam);
            return existingTeam;
        }
        return null;
    }

    @Transactional
    public boolean deleteTeam(int id) {
        TeamModel existingTeam = entityManager.createQuery(
                        "SELECT t FROM TeamModel t WHERE t.idteam = :id", TeamModel.class)
                .setParameter("id", id)
                .getSingleResult();

        if (existingTeam != null) {
            entityManager.remove(existingTeam);
            return true;
        }
        return false;
    }

    public TeamModel getTeamById(int teamId) {
        try {
            return entityManager.createQuery(
                            "SELECT t FROM TeamModel t WHERE t.idteam = :teamId", TeamModel.class)
                    .setParameter("teamId", teamId)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
