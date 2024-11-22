package com.iwaconsolti.league.demo.repository;

import com.iwaconsolti.league.demo.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Long> {
    List<Match> findByLeagueId(long leagueId);
    List<Match> findByHomeTeamIdOrAwayTeamId(long homeTeamId, long awayTeamId);
}
