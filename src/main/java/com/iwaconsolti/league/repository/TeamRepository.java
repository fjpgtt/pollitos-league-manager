package com.iwaconsolti.league.repository;


import com.iwaconsolti.league.model.TeamModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository <TeamModel, Long> {
}
