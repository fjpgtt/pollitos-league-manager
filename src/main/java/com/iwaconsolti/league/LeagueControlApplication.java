package com.iwaconsolti.league;

import com.iwaconsolti.league.DTO.LeagueDetailsDTO;
import com.iwaconsolti.league.model.MatchModel;
import com.iwaconsolti.league.model.PlayerModel;
import com.iwaconsolti.league.model.TeamModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.iwaconsolti.league.service.LeagueService;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Scanner;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.iwaconsolti.league.repository")
@EntityScan(basePackages = "com.iwaconsolti.league.model")
public class LeagueControlApplication implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(LeagueControlApplication.class);
	private final LeagueService leagueService;

	@Autowired
	public LeagueControlApplication(LeagueService leagueService) {
		this.leagueService = leagueService;
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		boolean listsFilled = false;

		while (!listsFilled) {
			logger.debug("Waiting for user input to verify if the lists are filled...");
			logger.info("Are the lists filled? (1 = Yes, 2 = No): ");
			int answer = scanner.nextInt();

			if (answer == 1) {
				listsFilled = true;
				logger.info("Inserting the leagues into the beans...");
				leagueService.insertLeague(leagueService.getSoccerLeague());
				leagueService.insertLeague(leagueService.getBasketballLeague());

				logger.info("Leagues inserted successfully");
				logger.debug("Soccer League: {}", leagueService.getSoccerLeague());
				logger.debug("Basketball League: {}", leagueService.getBasketballLeague());


				logger.info("Displaying details for Soccer League...");
				LeagueDetailsDTO soccerLeagueDetails = leagueService.getLeagueDetailsDTO(1);
				printLeagueDetails(soccerLeagueDetails);

				logger.info("Displaying details for Basketball League...");
				LeagueDetailsDTO basketballLeagueDetails = leagueService.getLeagueDetailsDTO(2);
				printLeagueDetails(basketballLeagueDetails);

			} else if (answer == 2) {
				logger.info("Waiting for the lists to be filled...");
				Thread.sleep(2000);
			} else {
				logger.warn("Invalid option entered: {}. Please enter 1 for Yes or 2 for No.", answer);
			}
		}
		scanner.close();
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
