package com.iwaconsolti.league.Config;

import com.iwaconsolti.league.model.LeagueModel;
import com.iwaconsolti.league.model.MatchModel;
import com.iwaconsolti.league.model.PlayerModel;
import com.iwaconsolti.league.model.TeamModel;
import com.iwaconsolti.league.repository.LeagueRepository;
import com.iwaconsolti.league.repository.MatchRepository;
import com.iwaconsolti.league.repository.PlayerRepository;
import com.iwaconsolti.league.repository.TeamRepository;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("populated")
public class DataInitializer {

    private final LeagueRepository leagueRepository;
    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;
    private final MatchRepository matchRepository;


    public DataInitializer(LeagueRepository leagueRepository, TeamRepository teamRepository,
                           PlayerRepository playerRepository, MatchRepository matchRepository) {
        this.leagueRepository = leagueRepository;
        this.teamRepository = teamRepository;
        this.playerRepository = playerRepository;
        this.matchRepository = matchRepository;
    }

    @Transactional
    public void init() {
        System.out.println("Initializing data...");

        LeagueModel league1 = new LeagueModel(1, "Fútbol");
        LeagueModel league2 = new LeagueModel(2, "Baloncesto");
        leagueRepository.save(league1);
        leagueRepository.save(league2);

        TeamModel team1 = new TeamModel(1, 1, "Pablitos FC");
        TeamModel team2 = new TeamModel(2, 1, "Francisco FC");
        TeamModel team3 = new TeamModel(3, 2, "Gatos");
        TeamModel team4 = new TeamModel(4, 2, "Perros");
        teamRepository.save(team1);
        teamRepository.save(team2);
        teamRepository.save(team3);
        teamRepository.save(team4);

        PlayerModel player1 = new PlayerModel(1, 1, 1, "Juan");
        PlayerModel player2 = new PlayerModel(2, 1, 2, "Carlos");
        PlayerModel player3 = new PlayerModel(3, 2, 3, "pedro");
        PlayerModel player4 = new PlayerModel(4, 2, 4, "francisco");
        playerRepository.save(player1);
        playerRepository.save(player2);
        playerRepository.save(player3);
        playerRepository.save(player4);

        MatchModel match1 = new MatchModel(1, 1, 1, 2, 1, 1);
        MatchModel match2 = new MatchModel(2, 1, 2, 1, 2, 3);
        MatchModel match3 = new MatchModel(3, 2, 3, 4, 1, 1);
        MatchModel match4 = new MatchModel(4, 2, 4, 3, 2, 3);
        matchRepository.save(match1);
        matchRepository.save(match2);
        matchRepository.save(match3);
        matchRepository.save(match4);

        System.out.println("Datos iniciales insertados correctamente en la base de datos.");
    }
}
