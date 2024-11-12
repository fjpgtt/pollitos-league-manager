package com.iwaconsolti.league.Config;

import com.iwaconsolti.league.model.LeagueModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LeagueConfiguration {
    @Bean
    @Qualifier("soccer")
    public LeagueModel leagueSoccer() {
        return new LeagueModel();
    }
    @Bean
    @Qualifier("Basketball")
    public LeagueModel leagueBasketball(){
        return new LeagueModel();
    }
}
