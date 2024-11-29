package com.iwaconsolti.league.manager.persistence.repository;

import com.iwaconsolti.league.manager.persistence.model.Players;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPlayersRepository extends JpaRepository<Players, Integer> {

    public List<Players> findPlayersByTeamId(int teamId);
}
