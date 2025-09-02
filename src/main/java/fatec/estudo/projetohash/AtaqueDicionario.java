package fatec.estudo.projetohash;

import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

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

    String[] senhas = {
            "123456",
            "123456789",
            "12345678",
            "password",
            "qwerty123",
            "qwerty1",
            "111111",
            "12345",
            "secret",
            "123123",
            "1234567890",
            "1234567890",
            "1234567",
            "000000",
            "qwerty",
            "abc123",
            "password1",
            "iloveyou",
            "11111111",
            "dragon",
            "monkey",
            "123123123",
            "qwertyuiop",
            "00000000",
            "Password",
            "654321",
            "target123",
            "tinkle",
            "zag12wsx",
            "1g2w3e4r",
            "gwerty123",
            "gwerty",
            "666666",
            "1q2w3e4r5t",
            "Qwerty123",
            "987654321",
            "1q2w3e4r",
            "a123456",
            "1qaz2wsx",
            "121212",
            "abcd1234",
            "asdfghjkl",
            "123456a",
            "88888888",
            "Qwerty123!",
            "Qwerty1!",
            "112233",
            "q1w2e3r4t5y6",
            "football",
            "zxcvbnm",
            "princess",
            "Qwerty1",
            "aaaaaa",
            "Abcd1234",
            "Password1",
            "sunshine",
            "147258369",
            "Qwerty1234",
            "fuckyou",
            "Qwerty12",
            "123qwe",
            "computer",
            "baseball",
            "159753",
            "superman",
            "azerty",
            "dearbook",
            "pokemon",
            "michael",
            "1234qwer",
            "1234561",
            "888888",
            "daniel",
            "111222tianya",
            "12345678910",
            "1qaz2wsx3edc",
            "123456789a",
            "123654",
            "P@ssw0rd",
            "qwer1234",
            "Qwerty1?",
            "789456123",
            "123456789",
            "Qwerty123?",
            "q1w2e3r4",
            "shadow",
            "222222",
            "soccer",
            "qwe123",
            "7777777",
            "22535",
            "asdasd",
            "admin",
            "killer",
            "testing",
            "qazwsx",
            "asdf1234",
            "1314520",
            "555555",
            "12341234",
            "michelle",
            "a123456789",
            "charlie",
            "liverpool",
            "master",
            "123abc",
            "7758521",
            "woaini",
            "asdfgh",
            "password123",
            "starwars",
            "jordan",
            "jessica",
            "999999",
            "unknown",
            "1q2w3e",
            "1111111",
            "789456",
            "pakistan",
            "12qwaszx",
            "ashley",
            "1111111111",
            "welcome",
            "aa123456",
            "jennifer",
            "11223344",
            "thomas",
            "159357",
            "asd123",
            "andrew",
            "nicole",
            "anthony",
            "147258",
            "trustno1",
            "qwerty12",
            "naruto",
            "jonathan",
            "hunter",
            "102030",
            "basketball",
            "cambiami",
            "letmein",
            "987654321",
            "hello",
            "chocolate",
            "zinch",
            "internet",
            "samsung",
            "asdfasdf",
            "Aa123456",
            "justin",
            "passw0rd",
            "purple",
            "blink182",
            "whatever",
            "g_czechout",
            "tigger",
            "Indya123",
            "samantha",
            "joshua",
            "alexander",
            "hannah",
            "qazwsxedc",
            "11111",
            "andrea",
            "minecraft",
            "matthew",
            "changeme",
            "123456!",
            "87654321",
            "jordan23",
            "qq123456",
            "1qazxsw2",
            "william",
            "1234567891",
            "123456123",
            "12344321",
            "buster",
            "cookie",
            "babygirl",
            "butterfly",
            "batman",
            "lol123",
            "qwert",
            "robert",
            "summer",
            "amanda",
            "123654789",
            "aaaaaaaa",
            "benjamin",
            "myspace1",
            "333333",
            "facebook",
            "chelsea",
            "family",
            "hello123",
            "maggie",
            "freedom",
            "cheese"
    };

    public void run() throws IOException {
        ArrayList<String> bcryptHashes = new ArrayList<>(gerenciadorHasher.hashPasswords(bCryptPasswordEncoder, senhas));
        //ArrayList<String> scryptHashes = new ArrayList<>(gerenciadorHasher.hashPasswords(sCryptPasswordEncoder, senhas));
        //ArrayList<String> pbkdf2Hashes = new ArrayList<>(gerenciadorHasher.hashPasswords(pbkdf2PasswordEncoder, senhas));
        //ArrayList<String> argon2Hashes = new ArrayList<>(gerenciadorHasher.hashPasswords(argon2PasswordEncoder, senhas));

        System.out.println(senhas.length);

        FileWriter writer = new FileWriter("C:\\hashcat-7.1.2\\bcryptHashes.txt");
        for (String hash : bcryptHashes) {
            writer.write(hash + "\n");
        }

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
