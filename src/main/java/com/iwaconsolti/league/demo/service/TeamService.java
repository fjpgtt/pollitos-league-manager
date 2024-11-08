package com.iwaconsolti.league.demo.service;

import com.iwaconsolti.league.demo.model.Player;
import com.iwaconsolti.league.demo.model.Team;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeamService {
    private static final Logger logger = LoggerFactory.getLogger(PlayerService.class);

    private List<Team> teams = new ArrayList<>();
    private Long nextId = 1L;


    public Team addTeam(Team team) {
        team.setId(nextId++);
        logger.info("Create new team: {}", team);
        teams.add(team);
        return team;
    }

    public Optional<Team> getTeamByName(String name) {
        logger.debug("Searching for product with id: {}", name);
        return teams.stream()
                .filter(team -> team.getName().equals(name))
                .findFirst();
    }

    public Optional<Team> getTeamById(Long id) {
        logger.debug("Searching for product with id: {}", id);
        return teams.stream()
                .filter(team -> team.getId().equals(id))
                .findFirst();
    }
}
