package com.iwaconsolti.league.Config;

import com.iwaconsolti.league.model.TeamModel;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "teams")
public class TeamConfig {

    private List<TeamModel> lista;

    public List<TeamModel> getLista() {
        return lista;
    }

    public void setLista(List<TeamModel> lista) {
        this.lista = lista;
    }
}
