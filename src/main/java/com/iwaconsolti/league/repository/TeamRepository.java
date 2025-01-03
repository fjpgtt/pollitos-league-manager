package com.iwaconsolti.league.repository;

import com.iwaconsolti.league.model.PlayerModel;
import com.iwaconsolti.league.model.TeamModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<TeamModel, Long> {

    @Query("SELECT t FROM TeamModel t")
    List<TeamModel> findAllTeams();

    @Query("SELECT t FROM TeamModel t WHERE t.idLeague = :idLeague")
    List<TeamModel> findLeague(@Param("idLeague") Long idLeague);


    @Modifying
    @Query("DELETE FROM PlayerModel p WHERE p.idTeam = :teamId")
    void deletePlayersByTeamId(@Param("teamId") Long teamId);
}
