package fatec.estudo.projetohash;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjetohashApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetohashApplication.class, args);

		



		String senha = "senha123";

		GerenciadorHashers gerenciadorHasher = new GerenciadorHashers();
		String result = gerenciadorHasher.getArgon2Hasher_DEFAULT().encode(senha);
		String result2 = gerenciadorHasher.getScryptHasher_DEFAULT().encode(senha);
		String result3 = gerenciadorHasher.getPbkdf2Hasher_DEFAULT().encode(senha);
		String result4 = gerenciadorHasher.getBcryptHasher_DEFAULT().encode(senha);

		System.out.println(result);
		System.out.println(result2);
		System.out.println(result3);
		System.out.println(result4);

	}

}
