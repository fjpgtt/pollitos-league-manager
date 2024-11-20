package com.iwaconsolti.league.repository;


import com.iwaconsolti.league.model.LeagueModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeagueRepository extends JpaRepository <LeagueModel, Long> {
}
