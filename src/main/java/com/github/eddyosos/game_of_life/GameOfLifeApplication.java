package com.github.eddyosos.game_of_life;

import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.diogonunes.jcolor.AnsiFormat;
import com.diogonunes.jcolor.Attribute;

import static com.diogonunes.jcolor.Ansi.colorize;
import static com.diogonunes.jcolor.Attribute.*;

@SpringBootApplication
public class GameOfLifeApplication implements CommandLineRunner {

	private static Logger LOG = LoggerFactory.getLogger(GameOfLifeApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(GameOfLifeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		System.out.println("Olá, quais vão ser o tamanho de Largura e Altura que você quer que tenha sua grade?");
		Integer largura = null;
		while (largura == null) {
			largura = getLargura().orElse(null);
		}
		Integer altura = null;
		while (altura == null) {
			altura = getAltura().orElse(null);
		}
		grade(largura, altura);
	}
	

	void grade(int largura, int altura) {
		for (int c = 0; c < altura; c++) {
			linha(largura);
			System.out.print("\n");
		}
	}

	void linha(int largura) {
		for (int c = 0; c < largura; c++) {
			quadrado(true);
		}
	}

	void quadrado(boolean quadrado) {
		if (quadrado == true) {
			preenchido();
		} else {
			vazio();
		}

	}

	void preenchido() {
		System.out.print(colorize(" ", GREEN_BACK()));
	}

	void vazio() {
		System.out.print(colorize(" ", RED_BACK()));
	}

	Optional<Integer> getLargura() {
		try {
			System.out.println("Largura?");

			Scanner scanner = new Scanner(System.in);
			int largura = scanner.nextInt();
			if (largura < 1) {
				throw new InputMismatchException("O valor deve ser positivo");
			}
			LOG.info("largura: " + largura);
			return Optional.of(largura);
		} catch (InputMismatchException ex) {
			System.out.println("Coloque um valor positivo Ex:1,2,3");
			return Optional.empty();
		}
	}

	Optional<Integer> getAltura() {
		try {
			System.out.println("Altura?");

			Scanner scanner = new Scanner(System.in);
			int altura = scanner.nextInt();
			if (altura < 1) {
				throw new InputMismatchException("O valor deve ser positivo");
			}
			LOG.info("altura: " + altura);
			return Optional.of(altura);
		} catch (InputMismatchException ex) {
			System.out.println("Coloque um numero inteiro!!!");
			return Optional.empty();
		}

	}

}
