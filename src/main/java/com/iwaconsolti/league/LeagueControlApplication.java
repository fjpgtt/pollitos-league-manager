package com.iwaconsolti.league;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.iwaconsolti.league.service.LeagueService;
import org.springframework.beans.factory.annotation.Qualifier;
import java.util.Scanner;

@SpringBootApplication
public class LeagueControlApplication implements CommandLineRunner {

	private final LeagueService leagueService;

	@Autowired
	public LeagueControlApplication(LeagueService leagueService) {
		this.leagueService = leagueService;
	}

	@Override
	public void run(String... args) throws Exception {
		// Aquí preguntarás si las listas ya están llenas
		Scanner scanner = new Scanner(System.in);

		// Bucle mientras que el estado de las listas no esté completo
		boolean listsFilled = false;

		// Asegúrate de que no entre en un bucle infinito
		while (!listsFilled) {
			System.out.println("¿Las listas ya están llenas? (1 = Sí, 2 = No): ");
			int respuesta = scanner.nextInt();

			if (respuesta == 1) {
				// Si la respuesta es 1, las listas están llenas
				listsFilled = true;

				// Ahora, insertamos las ligas en los beans
				System.out.println("Insertando las ligas en los beans...");
				leagueService.insertLeague(leagueService.getSoccerLeague(),1); // Ejemplo de inserción
				leagueService.insertLeague(leagueService.getBasketballLeague(),2); // Ejemplo de inserción

				System.out.println("Ligas insertadas correctamente!");

				// Mostrar los datos de los beans insertados
				System.out.println("Liga de Fútbol: " + leagueService.getSoccerLeague());
				System.out.println("Liga de Baloncesto: " + leagueService.getBasketballLeague());

			} else if (respuesta == 2) {
				// Si la respuesta es 2, las listas no están llenas, se puede esperar
				System.out.println("Esperando que las listas se llenen...");
				Thread.sleep(2000); // Espera de 2 segundos antes de preguntar nuevamente (opcional)
			} else {
				// Respuesta inválida, repite la pregunta
				System.out.println("Opción inválida. Por favor, ingresa 1 para Sí o 2 para No.");
			}
		}

		scanner.close();
	}

	public static void main(String[] args) {
		SpringApplication.run(LeagueControlApplication.class, args);
	}
}
