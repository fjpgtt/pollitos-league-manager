package com.iwaconsolti.league.manager.persistence.repository;

import com.iwaconsolti.league.manager.persistence.model.Teams;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ITeamsRepository extends JpaRepository<Teams, Integer> {

    Optional<Teams> findByIdAndLeagueType(int id, String leagueType);

    List<Teams> findByLeagueType(String leagueType);
}
