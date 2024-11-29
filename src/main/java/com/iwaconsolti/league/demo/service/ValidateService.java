package com.iwaconsolti.league.demo.service;

import com.iwaconsolti.league.demo.repository.TeamRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import static com.iwaconsolti.league.demo.model.LeagueType.isValidLeague;

@Service
@Slf4j
public class ValidateService {
    private final TeamRepository teamRepository;
    private final LeagueInterface soccerLeague;
    private final LeagueInterface basketLeague;

    @Autowired
    public ValidateService(TeamRepository teamRepository, @Qualifier("soccerleague") LeagueInterface soccerLeague, @Qualifier("basketleague")LeagueInterface basketLeague) {
        this.teamRepository = teamRepository;
        this.soccerLeague = soccerLeague;
        this.basketLeague = basketLeague;
    }

    public boolean validationLeagueName(String leagueName) {
        if (!isValidLeague(leagueName)) {
            log.error("League {} doesn't exist", leagueName);
            return false;
        }
        log.info("VS.21 - League {} exist", leagueName);
        return true;
    }

    public boolean validationTeamName(String teamName, String leagueName) {
        if (validationLeagueName(leagueName)) {
            boolean teamExist = teamRepository.existsByName(teamName.toLowerCase());
            if (teamExist) {
                log.info("Team {} exist in {}", teamName, leagueName);
                return true;
            }
            log.info("Team '{}' does not exists in league '{}'", teamName, leagueName);
            return false;
        }
        return false;
    }

//    public String getLeague(String leagueName) {
//        return Arrays.stream(LeagueType.values())
//                .map(LeagueType::getValue)
//                .filter(leagueValue -> leagueValue.equalsIgnoreCase(leagueName))
//                .findFirst()
//                .orElseThrow(() -> new IllegalArgumentException("Invalid league name: " + leagueName));
//
//    }

    public LeagueInterface getLeagueService(String leagueName) {
        if("basketleague".equalsIgnoreCase(leagueName)){
            return basketLeague;
        }else
            return soccerLeague;

    }
}

