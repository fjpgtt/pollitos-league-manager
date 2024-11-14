package com.iwaconsolti.league.Config;

import com.iwaconsolti.league.model.TeamModel;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@Data
@ConfigurationProperties(prefix = "teams")
public class TeamConfig {

    private List<TeamModel> teamsconfiglist;
}
