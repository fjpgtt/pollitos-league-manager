package com.iwaconsolti.league.demo.service;

import com.iwaconsolti.league.demo.service.repository.LeaguesRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ValidateService {

    private final BasketLeague basketLeague;
    private final SoccerLeague soccerLeague;

    @Autowired
    public ValidateService(BasketLeague basketLeague, SoccerLeague soccerLeague) {
        this.basketLeague = basketLeague;
        this.soccerLeague = soccerLeague;
    }

    public boolean leagueNameValidation(String leagueName) {
        if ("basketleague".equalsIgnoreCase(leagueName) || "soccerleague".equalsIgnoreCase(leagueName)) {
            return true;
        }
        log.error("League {} doesn't exist", leagueName);
        return false;
    }

    public boolean teamNameValidation(String teamName, String leagueName) {

        if ("basketleague".equalsIgnoreCase(leagueName)) {
            log.info(basketLeague.getTeams().toString());
            return basketLeague.getTeams().stream().anyMatch((team -> team.getName().equalsIgnoreCase(teamName)));
        } else if ("soccerleague".equalsIgnoreCase(leagueName)) {

            return soccerLeague.getTeams().stream().anyMatch((team -> team.getName().equalsIgnoreCase(teamName)));
        }
        return false;
    }
}
