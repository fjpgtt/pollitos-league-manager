package com.iwaconsolti.league.Config;
import com.iwaconsolti.league.model.PlayerModel;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@Data
@ConfigurationProperties(prefix = "players")
public class PlayerConfig {

    private List<PlayerModel> lista;

    public List<PlayerModel> getLista() {
        return lista;
    }
}


