package com.iwaconsolti.league.demo.repository;

import com.iwaconsolti.league.demo.entity.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlayerRepository extends JpaRepository<PlayerEntity, Long> {

    List<PlayerEntity> findByTeamName(@Param("teamName") String teamName);

    @Modifying
    @Query("DELETE FROM PlayerEntity p WHERE p.team.name = :teamName")
    void deleteByTeam_Name(@Param("teamName") String teamName);
}
