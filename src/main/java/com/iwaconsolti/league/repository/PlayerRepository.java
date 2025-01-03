package com.iwaconsolti.league.repository;

import com.iwaconsolti.league.model.PlayerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<PlayerModel, Integer> {
    @Query("SELECT p FROM PlayerModel p WHERE p.idLeague = :idLeague")
    List<PlayerModel> findPlayersByIdleague(Integer idLeague);

    @Query("SELECT p FROM PlayerModel p WHERE p.idTeam = :teamId AND p.idLeague = :idLeague")
    List<PlayerModel> findPlayersByTeamAndLeagueId(Integer teamId, Integer idLeague);
}
