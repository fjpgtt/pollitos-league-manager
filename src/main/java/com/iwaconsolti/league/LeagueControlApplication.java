package com.iwaconsolti.league;

import com.iwaconsolti.league.Config.DataInitializer;
import com.iwaconsolti.league.DTO.LeagueDetailsDTO;
import com.iwaconsolti.league.model.MatchModel;
import com.iwaconsolti.league.model.PlayerModel;
import com.iwaconsolti.league.model.TeamModel;
import com.iwaconsolti.league.service.LeagueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.iwaconsolti.league.repository")
@EntityScan(basePackages = "com.iwaconsolti.league.model")
@ComponentScan(basePackages = "com.iwaconsolti.league")
@Profile("populated")
public class LeagueControlApplication implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(LeagueControlApplication.class);
	private final LeagueService leagueService;

	@Autowired
	private ApplicationContext context;

	@Autowired
	public LeagueControlApplication(LeagueService leagueService) {
		this.leagueService = leagueService;
	}

	@Override
	public void run(String... args) throws Exception {
		DataInitializer dataInitializer = context.getBean(DataInitializer.class);
		dataInitializer.init();

		logger.info("Inserting the leagues into the beans...");
		leagueService.insertLeague(leagueService.getSoccerLeague());
		leagueService.insertLeague(leagueService.getBasketballLeague());

		logger.info("Displaying details for Soccer League...");
		LeagueDetailsDTO soccerLeagueDetails = leagueService.getLeagueDetailsDTO(1);
		printLeagueDetails(soccerLeagueDetails);

		logger.info("Displaying details for Basketball League...");
		LeagueDetailsDTO basketballLeagueDetails = leagueService.getLeagueDetailsDTO(2);
		printLeagueDetails(basketballLeagueDetails);
	}

	private void printLeagueDetails(LeagueDetailsDTO leagueDetailsDTO) {
		logger.info("Players in the league:");
		for (PlayerModel player : leagueDetailsDTO.getPlayers()) {
			logger.info("Player: {}", player);
		}

		logger.info("Matches in the league:");
		for (MatchModel match : leagueDetailsDTO.getMatches()) {
			logger.info("Match: {}", match);
		}

		logger.info("Teams in the league:");
		for (TeamModel team : leagueDetailsDTO.getTeams()) {
			logger.info("Team: {}", team);
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(LeagueControlApplication.class, args);
	}
}
