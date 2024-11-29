package com.iwaconsolti.league.repository;

import com.iwaconsolti.league.model.MatchModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<MatchModel, Long> {

    @Query("SELECT m FROM MatchModel m")
    List<MatchModel> findAllMatches();

    @Query("SELECT m FROM MatchModel m WHERE m.localteamid = :teamId OR m.visitteamid = :teamId")
    List<MatchModel> findMatchesByTeamId(int teamId);

    @Modifying
    @Transactional
    @Query("DELETE FROM MatchModel m")
    void deleteAllMatches();
}
