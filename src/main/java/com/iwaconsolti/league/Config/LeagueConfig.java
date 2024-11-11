package com.iwaconsolti.league.Config;

import com.iwaconsolti.league.model.LeagueModel;
import com.iwaconsolti.league.model.TeamModel;
import com.iwaconsolti.league.model.PlayerModel;
import com.iwaconsolti.league.model.MatchModel;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "league")
public class LeagueConfig {

    private List<LeagueModel> lista;

    public List<LeagueModel> getLista() {
        return lista;
    }

    public void setLista(List<LeagueModel> lista) {
        this.lista = lista;
    }
}


