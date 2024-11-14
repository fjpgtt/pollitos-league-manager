package com.iwaconsolti.league.Config;


import com.iwaconsolti.league.model.MatchModel;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "match")
@Data
public class MatchConfig {

    private List<MatchModel> matchconfiglist;
}