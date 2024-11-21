package com.iwaconsolti.league.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.iwaconsolti.league.demo.entity.MatchEntity;

public interface MatchRepository extends JpaRepository<MatchEntity, Long> {

}
