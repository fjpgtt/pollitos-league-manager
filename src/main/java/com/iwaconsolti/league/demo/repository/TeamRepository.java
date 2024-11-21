package com.iwaconsolti.league.demo.repository;

import com.iwaconsolti.league.demo.entity.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<TeamEntity, Long> {
}
