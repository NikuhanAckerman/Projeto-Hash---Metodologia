package fatec.estudo.projetohash;

import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.Scanner;

public class GeradorSenhas {

    GerenciadorHashers gerenciadorHashers = new GerenciadorHashers();
    BCryptPasswordEncoder bCryptPasswordEncoder = gerenciadorHashers.getBcryptHasher_DEFAULT();
    SCryptPasswordEncoder sCryptPasswordEncoder = gerenciadorHashers.getScryptHasher_DEFAULT();
    Pbkdf2PasswordEncoder pbkdf2PasswordEncoder = gerenciadorHashers.getPbkdf2Hasher_DEFAULT();
    Argon2PasswordEncoder argon2PasswordEncoder = gerenciadorHashers.getArgon2Hasher_DEFAULT();

    // SENHAS DA LISTA DA NORDPASS (2024)
    String[] senhasNordpass = {
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

    private static final String LOWERCASE_CHARS = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPERCASE_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGIT_CHARS = "0123456789";
    private static final String SPECIAL_CHARS = "!@#$%^&*()-_=+[]{}|;:,.<>?";

    public String generateHighEntropyPassword(int length) {
        if (length < 8) {
            throw new IllegalArgumentException("Password length should be at least 8 characters.");
        }

        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        String allChars = LOWERCASE_CHARS + UPPERCASE_CHARS + DIGIT_CHARS + SPECIAL_CHARS;

        password.append(LOWERCASE_CHARS.charAt(random.nextInt(LOWERCASE_CHARS.length())));
        password.append(UPPERCASE_CHARS.charAt(random.nextInt(UPPERCASE_CHARS.length())));
        password.append(DIGIT_CHARS.charAt(random.nextInt(DIGIT_CHARS.length())));
        password.append(SPECIAL_CHARS.charAt(random.nextInt(SPECIAL_CHARS.length())));

        for (int i = 4; i < length; i++) {
            password.append(allChars.charAt(random.nextInt(allChars.length())));
        }

        char[] passwordArray = password.toString().toCharArray();
        for (int i = 0; i < passwordArray.length; i++) {
            int randomIndex = random.nextInt(passwordArray.length);
            char temp = passwordArray[i];
            passwordArray[i] = passwordArray[randomIndex];
            passwordArray[randomIndex] = temp;
        }

        return new String(passwordArray);
    }

    public ArrayList<String> createPasswordList(Optional<ArrayList<String>> list) {
        ArrayList<String> listTemp = list.orElse(new ArrayList<>());

        if(listTemp.isEmpty()) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Digite quantas senhas quer criar: ");
            int size = scanner.nextInt();
            System.out.println("Digite o tamanho de cada senha: ");
            int length = scanner.nextInt();

            for(int i = 0; i < size; i++) {
                listTemp.add(this.generateHighEntropyPassword(length));
            }

        }

        try (FileWriter writer = new FileWriter("C:\\Users\\nicol\\OneDrive\\Desktop\\passlists\\passlist"
                + "_size_" + listTemp.size()
                + "_length_" + listTemp.get(0).length() + ".txt")) {

            for (String password : listTemp) {
                writer.write(password + "\n");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return listTemp;

    }

    public String[] get10kList() {
        String[] list10k = new String[10000];

        try {
            File list10kFile = new File("C:\\hashcat-7.1.2\\experimento_benchmark_segurança\\10_million_password_list_top_10000.txt");
            Scanner reader = new Scanner(list10kFile);
            int i = 0;
            while(reader.hasNextLine()) {
                list10k[i] = reader.nextLine();
            }
            reader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return list10k;
    }

    public ArrayList<ArrayList<String>> generate3List20Passwords() {
        ArrayList<ArrayList<String>> listOfListsOfPasswords = new ArrayList<>();

        listOfListsOfPasswords.add(createPasswordList(Optional.empty()));
        listOfListsOfPasswords.add(createPasswordList(Optional.empty()));
        listOfListsOfPasswords.add(createPasswordList(Optional.empty()));

        return listOfListsOfPasswords;
    }

    public void generateHashTxts(ArrayList<String> hashList, String txtName) {
        try (FileWriter writer = new FileWriter("C:\\hashcat-7.1.2\\" + txtName + ".txt")) {
            for (String hash : hashList) {
                writer.write(hash + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void generate5SetTxts() {
        // NORDPASS LIST

        ArrayList<String> bcryptHashes_NORDPASS = new ArrayList<>(gerenciadorHashers.insertPasswords(bCryptPasswordEncoder, senhasNordpass));
        ArrayList<String> scryptHashes_NORDPASS = new ArrayList<>(gerenciadorHashers.insertPasswords(sCryptPasswordEncoder, senhasNordpass));
        ArrayList<String> pbkdf2Hashes_NORDPASS = new ArrayList<>(gerenciadorHashers.insertPasswords(pbkdf2PasswordEncoder, senhasNordpass));
        ArrayList<String> argon2Hashes_NORDPASS = new ArrayList<>(gerenciadorHashers.insertPasswords(argon2PasswordEncoder, senhasNordpass));

        generateHashTxts(bcryptHashes_NORDPASS, "bcryptHashes_NORDPASS");
        generateHashTxts(scryptHashes_NORDPASS, "scryptHashes_NORDPASS");
        generateHashTxts(pbkdf2Hashes_NORDPASS, "pbkdf2Hashes_NORDPASS");
        generateHashTxts(argon2Hashes_NORDPASS, "argon2Hashes_NORDPASS");

        // 10K PASSWORDS LIST

        String[] list10k = new String[10000];

        try {
            File list10kFile = new File("C:\\hashcat-7.1.2\\experimento_benchmark_segurança\\10_million_password_list_top_10000.txt");
            Scanner reader = new Scanner(list10kFile);
            int i = 0;
            while(reader.hasNextLine()) {
                list10k[i] = reader.nextLine();
            }
            reader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        ArrayList<String> bcryptHashes_10K = new ArrayList<>(gerenciadorHashers.insertPasswords(bCryptPasswordEncoder, list10k));
        ArrayList<String> scryptHashes_10k = new ArrayList<>(gerenciadorHashers.insertPasswords(sCryptPasswordEncoder, list10k));
        ArrayList<String> pbkdf2Hashes_10k = new ArrayList<>(gerenciadorHashers.insertPasswords(pbkdf2PasswordEncoder, list10k));
        ArrayList<String> argon2Hashes_10k = new ArrayList<>(gerenciadorHashers.insertPasswords(argon2PasswordEncoder, list10k));

        generateHashTxts(bcryptHashes_10K, "bcryptHashes_10k");
        generateHashTxts(scryptHashes_10k, "scryptHashes_10k");
        generateHashTxts(pbkdf2Hashes_10k, "pbkdf2Hashes_10k");
        generateHashTxts(argon2Hashes_10k, "argon2Hashes_10k");

        // 3 MODERATE-HIGH ENTROPY PSEUDORANDOM ALGORITHMICALLY GENERATED PASSWORDS

        createPasswordList(Optional.empty());
        createPasswordList(Optional.empty());
        createPasswordList(Optional.empty());
    }


}
