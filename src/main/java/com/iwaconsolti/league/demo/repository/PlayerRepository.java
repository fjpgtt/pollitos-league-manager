package com.iwaconsolti.league.demo.repository;

import com.iwaconsolti.league.demo.entity.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlayerRepository extends JpaRepository<PlayerEntity, Long> {

    @Query("SELECT p FROM PlayerEntity p WHERE p.team = :teamName")
    List<PlayerEntity> findByTeam(@Param("teamName") String teamName);

    @Query("DELETE FROM PlayerEntity p WHERE p.team = :teamName")
    void deleteByTeam(@Param("teamName") String teamName);
}
