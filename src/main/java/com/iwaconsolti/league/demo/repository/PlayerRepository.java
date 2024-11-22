package com.iwaconsolti.league.demo.repository;

import com.iwaconsolti.league.demo.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    List<Player> findByTeamId(long teamId);

}
