package com.github.eddyosos.game_of_life;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GameOfLifeApplication implements CommandLineRunner {

	private static Logger LOG = LoggerFactory.getLogger(GameOfLifeApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(GameOfLifeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var a = "abc";
		LOG.info(a);
	}
}
