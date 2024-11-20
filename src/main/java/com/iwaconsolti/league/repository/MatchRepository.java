package com.iwaconsolti.league.repository;


import com.iwaconsolti.league.model.MatchModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRepository extends JpaRepository <MatchModel, Long> {
}
