package com.iwaconsolti.league.demo.configuration;

import com.iwaconsolti.league.demo.repository.Leagues;
import com.iwaconsolti.league.demo.repository.LeaguesRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class LeagueConfiguration {

    @Bean
    @Profile("populated")
    public LeaguesRepository populatedLeagues(){
        LeaguesRepository repository = new LeaguesRepository();
        repository.fillLeagues();
        return repository;
    }
}
