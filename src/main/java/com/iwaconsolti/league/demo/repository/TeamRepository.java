package com.iwaconsolti.league.demo.repository;

import com.iwaconsolti.league.demo.entity.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<TeamEntity, Long> {

    boolean existsByName(String teamName);
    TeamEntity findByName(@Param("name") String name);
    List<TeamEntity> findByLeague(@Param("league")String league);

}
