package com.iwaconsolti.league.manager.persistence.repository;

import com.iwaconsolti.league.manager.persistence.model.Matches;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface IMatchesRepository extends JpaRepository<Matches, Integer> {

    List<Matches> findByTeamIdAOrTeamIdB(int teamIdA, int teamIdB);

    List<Matches> findByLeagueType(String leagueType);
}
