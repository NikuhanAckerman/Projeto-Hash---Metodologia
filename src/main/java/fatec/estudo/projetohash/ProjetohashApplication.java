package fatec.estudo.projetohash;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;

@SpringBootApplication
public class ProjetohashApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(ProjetohashApplication.class, args);

		int opcao;

		System.out.println("-- Bem vindo ao Projeto Spring Security Hash --");
		System.out.println("1 - Performance (tempo de execução, uso de CPU, uso de memória");
		System.out.println("2 - Segurança (ataque de dicionário, ataque de máscara)");
		/*Scanner scanner = new Scanner(System.in);
		opcao = scanner.nextInt();
		switch (opcao) {
			case 1:
				break;
			case 2:
				break;
			default:
				break;

		}*/

		Performance performance = new Performance();
		performance.run();

		//System.out.println(geradorSenhas.generateHighEntropyPassword(8));

		//AtaqueDicionario ataqueDicionario = new AtaqueDicionario();
		//ataqueDicionario.run();

	}

}
