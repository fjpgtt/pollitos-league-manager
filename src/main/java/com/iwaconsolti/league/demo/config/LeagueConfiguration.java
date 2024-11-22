package com.iwaconsolti.league.demo.config;
import com.iwaconsolti.league.demo.repository.LeagueRepository;
import com.iwaconsolti.league.demo.repository.PlayerRepository;
import com.iwaconsolti.league.demo.repository.TeamRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class LeagueConfiguration {

    @Profile("populated")
    @Bean
    public LeagueInitializer leagueInitializer(LeagueRepository leagueRepository, TeamRepository teamRepository, PlayerRepository playerRepository) {
        return new LeagueInitializer(leagueRepository, teamRepository, playerRepository);
    }
}
