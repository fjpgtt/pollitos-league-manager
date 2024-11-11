package com.iwaconsolti.league.demo.config;
import com.iwaconsolti.league.demo.service.LeagueService;
import com.iwaconsolti.league.demo.service.TeamService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class LeagueConfig {

    @Profile("populated")
    @Bean
    public LeagueInitializer leagueInitializer(LeagueService leagueService, TeamService teamService) {
        return new LeagueInitializer(leagueService, teamService);
    }
}
