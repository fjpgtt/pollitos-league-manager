package com.iwaconsolti.league;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.iwaconsolti.league.service.LeagueService;
import java.util.Scanner;

@SpringBootApplication
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
				leagueService.insertLeague(leagueService.getSoccerLeague(), 1);
				leagueService.insertLeague(leagueService.getBasketballLeague(), 2);

				logger.info("Leagues inserted successfully");
				logger.debug("Soccer League: {}", leagueService.getSoccerLeague());
				logger.debug("Basketball League: {}", leagueService.getBasketballLeague());

			} else if (answer == 2) {
				logger.info("Waiting for the lists to be filled...");
				Thread.sleep(2000);
			} else {
				logger.warn("Invalid option entered: {}. Please enter 1 for Yes or 2 for No.", answer);
			}
		}
		scanner.close();
	}

	public static void main(String[] args) {
		SpringApplication.run(LeagueControlApplication.class, args);
	}
}
