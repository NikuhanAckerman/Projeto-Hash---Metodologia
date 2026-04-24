package fatec.estudo.projetohash;

import org.openjdk.jmh.runner.RunnerException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;

@SpringBootApplication
public class ProjetohashApplication {

	public static void main(String[] args) throws IOException, RunnerException {
		SpringApplication.run(ProjetohashApplication.class, args);

		GeradorSenhas geradorSenhas = new GeradorSenhas();
		GerenciadorHashers gerenciadorHashers = new GerenciadorHashers();




/*
		geradorSenhas.generateHashTxts(
				gerenciadorHashers.hashPasswordsReturnList(gerenciadorHashers.getBcryptHasher_DEFAULT(), geradorSenhas.senhasNordpass),
				"bcrypt_NORDPASS");

		geradorSenhas.generateHashTxts(
				gerenciadorHashers.hashPasswordsReturnList(gerenciadorHashers.getScryptHasher_DEFAULT(), geradorSenhas.senhasNordpass),
				"scrypt_NORDPASS");

		geradorSenhas.generateHashTxts(
				gerenciadorHashers.hashPasswordsReturnList(gerenciadorHashers.getPbkdf2Hasher_DEFAULT(), geradorSenhas.senhasNordpass),
				"pbkdf2_NORDPASS");

		geradorSenhas.generateHashTxts(
				gerenciadorHashers.hashPasswordsReturnList(gerenciadorHashers.getArgon2Hasher_DEFAULT(), geradorSenhas.senhasNordpass),
				"argon2_NORDPASS");

		geradorSenhas.generateHashTxts(
				gerenciadorHashers.hashPasswordsReturnList(gerenciadorHashers.getBcryptHasher_DEFAULT(), geradorSenhas.get10kList()),
				"bcrypt_10kList");

		geradorSenhas.generateHashTxts(
				gerenciadorHashers.hashPasswordsReturnList(gerenciadorHashers.getScryptHasher_DEFAULT(), geradorSenhas.get10kList()),
				"scrypt_10kList");

		geradorSenhas.generateHashTxts(
				gerenciadorHashers.hashPasswordsReturnList(gerenciadorHashers.getPbkdf2Hasher_DEFAULT(), geradorSenhas.get10kList()),
				"pbkdf2_10kList");

		geradorSenhas.generateHashTxts(
				gerenciadorHashers.hashPasswordsReturnList(gerenciadorHashers.getArgon2Hasher_DEFAULT(), geradorSenhas.get10kList()),
				"argon2_10kList");
*/

		Pbkdf2PasswordEncoder pbkdf2PasswordEncoderPasswordEncoder = Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8();
		System.out.println(pbkdf2PasswordEncoderPasswordEncoder.encode("123456"));

		//System.out.println(geradorSenhas.generateHighEntropyPassword(8));

		//AtaqueDicionario ataqueDicionario = new AtaqueDicionario();
		//ataqueDicionario.run();

	}

}
