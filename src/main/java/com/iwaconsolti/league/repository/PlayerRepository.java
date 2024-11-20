package com.iwaconsolti.league.repository;


import com.iwaconsolti.league.model.PlayerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository <PlayerModel, Long> {
}
