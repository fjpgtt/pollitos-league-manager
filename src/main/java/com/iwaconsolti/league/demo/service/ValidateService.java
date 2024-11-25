package com.iwaconsolti.league.demo.service;

import com.iwaconsolti.league.demo.repository.TeamRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ValidateService {
    private final TeamRepository teamRepository;


    @Autowired
    public ValidateService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public boolean validationLeagueName(String leagueName) {
        if ("basketleague".equalsIgnoreCase(leagueName) || "soccerleague".equalsIgnoreCase(leagueName)) {
            return true;
        }
        log.error("League {} doesn't exist", leagueName);
        return false;
    }

    public boolean validationTeamName(String teamName, String leagueName) {
        if (validationLeagueName(leagueName)) {
            log.info("Team '{}' exists in league '{}'", teamName, leagueName);
            return teamRepository.existsByName(teamName.toLowerCase());
        }
        log.error("Invalid league name: {}", leagueName);
        return false;
    }

    }

