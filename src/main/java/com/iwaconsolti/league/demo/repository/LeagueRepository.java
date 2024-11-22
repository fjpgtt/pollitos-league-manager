package com.iwaconsolti.league.demo.repository;

import com.iwaconsolti.league.demo.model.League;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeagueRepository extends JpaRepository<League, Long> {
}
