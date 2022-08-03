package com.github.eddyosos.game_of_life;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.SystemPropertyUtils;

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
		var grade = new boolean[largura][altura];

		printGrade(grade);
		montagemSeed(grade);

		System.out.println("Qual celula você gostaria de ver seus vizinhos? ");
		Scanner sc = new Scanner(System.in);
		
		var xCelula = 0;
		var yCelula = 0;
		var contador = 0;
		
		var x = xCelula - 1;
		var y = yCelula - 1;
		var z = xCelula + 1;
		var v = yCelula + 1;
		if (x >= 0 && x < grade.length && y >= 0 && y < grade[0].length && grade[x][y]) {
			contador += 1;
		}

		if (x >= 0 && x < grade.length && yCelula >= 0 && yCelula < grade[0].length && grade[x][yCelula]) {
			contador += 1;
		}

		if (x >= 0 && x < grade.length && v >= 0 && v < grade[0].length && grade[x][v]) {
			contador += 1;
		}
		if (xCelula >= 0 && xCelula < grade.length && y >= 0 && y < grade[0].length && grade[xCelula][y]) {
			contador += 1;
		}
		if (xCelula >= 0 && xCelula < grade.length && v >= 0 && v < grade[0].length && grade[xCelula][v]) {
			contador += 1;
		}
		if (z >= 0 && z < grade.length && y >= 0 && y < grade[0].length && grade[z][y]) {
			contador += 1;
		}
		if (z >= 0 && z < grade.length && yCelula >= 0 && yCelula < grade[0].length && grade[z][yCelula]) {
			contador += 1;
		}
		if (z >= 0 && z < grade.length && v >= 0 && v < grade[0].length && grade[z][v]) {
			contador += 1;
		}
		System.out.println(contador);

	}

	void montagemSeed(boolean[][] grade) {
		Scanner sc = new Scanner(System.in);
		char resp;
		System.out.println("Você gostaria de preencher as celulas (s/n)?");
		resp = sc.next().charAt(0);
		if (resp == 's') {
			do {
				System.out.println("Qual a localização da celula no sentido horizontal? >>>>>");
				var coluna = sc.nextInt() - 1;
				System.out.println("Qual a localização da celula no sentido vertical? V");
				var linha = sc.nextInt() - 1;

				grade[linha][coluna] = true;
				printGrade(grade);
				System.out.println("Gostaria de colocar mais uma celula?");
				resp = sc.next().charAt(0);
			} while (resp != 'n');

		}

	}

	void printGrade(boolean[][] grade) {
		for (int contador = 0; contador < grade.length; contador++) {
			printLinha(grade[contador]);
			System.out.print("\n");
		}
	}

	void printLinha(boolean[] linha) {
		for (int contador = 0; contador < linha.length; contador++) {
			printCelula(linha[contador]);
		}
	}

	void printCelula(boolean isVivo) {
		if (isVivo) {
			printVivo();
		} else {
			printMorto();
		}

	}

	void printVivo() {
		System.out.print(colorize(" ", GREEN_BACK()));
	}

	void printMorto() {
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
