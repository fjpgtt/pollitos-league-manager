package com.iwaconsolti.league.demo.repository;

import com.iwaconsolti.league.demo.entity.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<TeamEntity, Long> {
    boolean existsByName(String teamName);

    @Query(value = "SELECT t FROM TeamEntity t WHERE t.name = :name")
    TeamEntity findByName(@Param("name") String name);


    @Query("SELECT t FROM TeamEntity t WHERE t.league = :league")
    List<TeamEntity> findTeamsByLeague(@Param("league")String league);

}
