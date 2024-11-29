package com.iwaconsolti.league.manager.persistence.repository;

import com.iwaconsolti.league.manager.persistence.model.Teams;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITeamsRepository extends JpaRepository<Teams, Integer> {
}
