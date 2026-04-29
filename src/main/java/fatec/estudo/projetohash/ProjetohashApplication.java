package fatec.estudo.projetohash;

import org.openjdk.jmh.runner.RunnerException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import java.io.IOException;


@SpringBootApplication
public class ProjetohashApplication {

	public static void main(String[] args) throws IOException, RunnerException {
		SpringApplication.run(ProjetohashApplication.class, args);

		System.out.println("-- Bem vindo ao Projeto Spring Security Hash --");

		Performance performance = new Performance();
		performance.run();


	}

}
