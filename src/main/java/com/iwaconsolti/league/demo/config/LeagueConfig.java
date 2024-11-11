package com.iwaconsolti.league.demo.config;

import com.iwaconsolti.league.demo.model.League;
import com.iwaconsolti.league.demo.model.LeagueType;
import com.iwaconsolti.league.demo.model.Player;
import com.iwaconsolti.league.demo.model.Team;
import com.iwaconsolti.league.demo.service.LeagueService;
import com.iwaconsolti.league.demo.service.TeamService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class LeagueConfig {
    @Value("${league.max-teams}")
    private int maxTeams;

    @Profile("populated")
    @Bean
    public LeagueInitializer leagueInitializer(LeagueService leagueService, TeamService teamService) {
        return new LeagueInitializer(leagueService, teamService, maxTeams);
    }
}
