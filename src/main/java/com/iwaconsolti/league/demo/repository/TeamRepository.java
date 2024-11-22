package com.iwaconsolti.league.demo.repository;

import com.iwaconsolti.league.demo.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
