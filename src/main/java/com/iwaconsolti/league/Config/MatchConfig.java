package com.iwaconsolti.league.Config;


import com.iwaconsolti.league.model.MatchModel;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "match")
public class MatchConfig {

    private List<MatchModel> lista;

    public List<MatchModel> getLista() {
        return lista;
    }

    public void setLista(List<MatchModel> lista) {
        this.lista = lista;
    }
}
