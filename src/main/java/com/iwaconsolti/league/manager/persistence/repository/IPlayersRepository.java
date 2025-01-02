package com.iwaconsolti.league.manager.persistence.repository;

import com.iwaconsolti.league.manager.persistence.model.Players;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IPlayersRepository extends JpaRepository<Players, Integer> {

    Optional<Players> findPlayersByIdAndLeagueType(int id, String leagueType);

}
