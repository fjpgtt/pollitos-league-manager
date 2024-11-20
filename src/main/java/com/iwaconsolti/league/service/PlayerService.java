package com.iwaconsolti.league.service;

import com.iwaconsolti.league.model.PlayerModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public PlayerService() {
    }

    public List<PlayerModel> getPlayer() {
        return entityManager.createQuery("SELECT p FROM PlayerModel p", PlayerModel.class)
                .getResultList();
    }

    @Transactional
    public PlayerModel insertPlayer(PlayerModel player) {
        String insertPlayerSql = "INSERT INTO PLAYER_MODEL (IDLEAGUE, IDTEAM, NAMEPLAYER) VALUES (:idleague, :idteam, :nameplayer)";
        Query query = entityManager.createNativeQuery(insertPlayerSql);
        query.setParameter("idleague", player.getIdLeague());
        query.setParameter("idteam", player.getIdTeam());
        query.setParameter("nameplayer", player.getNamePlayer());
        query.executeUpdate();
        return player;
    }

    @Transactional
    public PlayerModel updatePlayer(int id, PlayerModel player) {
        PlayerModel existingPlayer = entityManager.createQuery(
                        "SELECT p FROM PlayerModel p WHERE p.idPlayer = :id", PlayerModel.class)
                .setParameter("id", id)
                .getSingleResult();

        if (existingPlayer != null) {
            existingPlayer.setNamePlayer(player.getNamePlayer());
            existingPlayer.setIdTeam(player.getIdTeam());
            existingPlayer.setIdLeague(player.getIdLeague());
            entityManager.merge(existingPlayer);
            return existingPlayer;
        }
        return null;
    }

    @Transactional
    public int deletePlayersByTeamId(int id) {
        try {
            return entityManager.createQuery(
                            "DELETE FROM PlayerModel p WHERE p.idTeam = :teamId")
                    .setParameter("teamId", id)
                    .executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0; // Si ocurre un error, devolver 0 (ninguna fila eliminada)
        }
    }


    public PlayerModel getPlayerById(int playerId) {
        try {
            return entityManager.createQuery(
                            "SELECT p FROM PlayerModel p WHERE p.idPlayer = :playerId", PlayerModel.class)
                    .setParameter("playerId", playerId)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public List<PlayerModel> getPlayersByTeam(int teamId) {
        return entityManager.createQuery(
                        "SELECT p FROM PlayerModel p WHERE p.idTeam = :teamId", PlayerModel.class)
                .setParameter("teamId", teamId)
                .getResultList();
    }
}
