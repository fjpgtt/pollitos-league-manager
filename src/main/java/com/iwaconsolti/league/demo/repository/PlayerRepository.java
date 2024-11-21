package com.iwaconsolti.league.demo.repository;

import com.iwaconsolti.league.demo.entity.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<PlayerEntity, Long> {

}
