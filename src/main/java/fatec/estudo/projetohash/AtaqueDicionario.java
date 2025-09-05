package fatec.estudo.projetohash;

import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class AtaqueDicionario {

    // O presente código busca avaliar qual o tempo levado
    // para o término de execução dos 4 algoritmos de hashing avaliados contra um ataque de dicionário,
    // onde um array de 200 strings das senhas mais comumente usadas segundo um artigo do NordPass de 2024
    // é percorrido. Essas senhas passam por hashing e os hashes são comparados com
    // o hash senha verdadeira, que é a última senha do array, de posição 199.

    GerenciadorHashers gerenciadorHasher = new GerenciadorHashers();
    BCryptPasswordEncoder bCryptPasswordEncoder = gerenciadorHasher.getBcryptHasher_DEFAULT();
    SCryptPasswordEncoder sCryptPasswordEncoder = gerenciadorHasher.getScryptHasher_DEFAULT();
    Pbkdf2PasswordEncoder pbkdf2PasswordEncoder = gerenciadorHasher.getPbkdf2Hasher_DEFAULT();
    Argon2PasswordEncoder argon2PasswordEncoder = gerenciadorHasher.getArgon2Hasher_DEFAULT();

    GeradorSenhas geradorSenhas = new GeradorSenhas();

    public void run() {



    }

    /*
    int indice = (int) (Math.random() * senhas.length);
		System.out.println(indice);
    indice = 0;
    String senhaEscolhida = senhas[indice];
		System.out.println(senhaEscolhida);
    String hash = encoder.encode(senhaEscolhida);
		System.out.println(hash);

    long beforeTime = System.currentTimeMillis();

		for(String senhaTentativa : senhas) {
        if (encoder.matches(senhaTentativa, hash)) {
            System.out.println("Senha encontrada: " + senhaTentativa);
            break;
        }
    }

    long nowTime = System.currentTimeMillis();

		System.out.println("Tempo demorado: " + (nowTime - beforeTime) + "ms");
    */
}
