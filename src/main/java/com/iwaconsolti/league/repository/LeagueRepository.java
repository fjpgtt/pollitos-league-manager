package com.iwaconsolti.league.repository;

import com.iwaconsolti.league.model.LeagueModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeagueRepository extends JpaRepository<LeagueModel, Long> {

    @Query("SELECT l FROM LeagueModel l")
    List<LeagueModel> findAllLeagues();

    @Query("SELECT l FROM LeagueModel l WHERE l.idLeague = :idLeague")
    LeagueModel findLeagueById(@Param("idLeague") int idLeague);

    @Query("SELECT p, m, t FROM PlayerModel p, MatchModel m, TeamModel t WHERE p.idLeague = :idleague AND m.idleague = :idleague AND t.idLeague = :idleague")
    List<Object[]> findAllByLeagueId(@Param("idleague") int idleague);

}
