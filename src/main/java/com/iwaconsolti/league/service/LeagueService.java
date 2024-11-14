package com.iwaconsolti.league.service;

import com.iwaconsolti.league.Config.MatchConfig;
import com.iwaconsolti.league.Config.PlayerConfig;
import com.iwaconsolti.league.Config.TeamConfig;
import com.iwaconsolti.league.model.LeagueModel;
import com.iwaconsolti.league.model.MatchModel;
import com.iwaconsolti.league.model.PlayerModel;
import com.iwaconsolti.league.model.TeamModel;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Profile("populated")
public class LeagueService {
    @Getter
    private final LeagueModel soccerLeague;      //Bean
    @Getter
    private final LeagueModel basketballLeague;  //Bean
    private final MatchConfig matchConfig;
    private final TeamConfig teamConfig;
    private final PlayerConfig playerConfig;

    @Autowired
    public LeagueService(
            @Qualifier("soccer") LeagueModel soccerLeague,
            @Qualifier("Basketball") LeagueModel basketballLeague,
            MatchConfig matchConfig,
            TeamConfig teamConfig,
            PlayerConfig playerConfig
    ) {
        this.soccerLeague = soccerLeague;
        this.basketballLeague = basketballLeague;
        this.matchConfig = matchConfig;
        this.teamConfig = teamConfig;
        this.playerConfig = playerConfig;
    }

    public void insertLeague(LeagueModel league, int i) {
        league.setIdLeague(i);
        List<MatchModel> matches = matchConfig.getMatchconfiglist().stream()
                .filter(match -> match.getIdleague() == i)
                .collect(Collectors.toList());
        league.setMatchesleague(matches);

        List<TeamModel> teams = teamConfig.getTeamsconfiglist().stream()
                .filter(team -> team.getIdLeague() == i)
                .collect(Collectors.toList());
        league.setTeamsleague(teams);

        List<PlayerModel> players = playerConfig.getPlayerconfiglist().stream()
                .filter(player -> player.getIdleague() == i)
                .collect(Collectors.toList());
        league.setPlayersleague(players);

        if (league.getIdLeague() == 1) {
            soccerLeague.setIdLeague(1);
            soccerLeague.setNameleague("Soccer League");
            soccerLeague.setTeamsleague(league.getTeamsleague());
            soccerLeague.setPlayersleague(league.getPlayersleague());
            soccerLeague.setMatchesleague(league.getMatchesleague());
        } else if (league.getIdLeague() == 2) {
            basketballLeague.setIdLeague(2);
            basketballLeague.setNameleague("Basketball League");
            basketballLeague.setTeamsleague(league.getTeamsleague());
            basketballLeague.setPlayersleague(league.getPlayersleague());
            basketballLeague.setMatchesleague(league.getMatchesleague());
        }
    }
}