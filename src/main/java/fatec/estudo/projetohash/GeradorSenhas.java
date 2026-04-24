package fatec.estudo.projetohash;

import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class GeradorSenhas {

    GerenciadorHashers gerenciadorHashers = new GerenciadorHashers();
    BCryptPasswordEncoder bCryptPasswordEncoder = gerenciadorHashers.getBcryptHasher_DEFAULT();
    SCryptPasswordEncoder sCryptPasswordEncoder = gerenciadorHashers.getScryptHasher_DEFAULT();
    Pbkdf2PasswordEncoder pbkdf2PasswordEncoder = gerenciadorHashers.getPbkdf2Hasher_DEFAULT();
    Argon2PasswordEncoder argon2PasswordEncoder = gerenciadorHashers.getArgon2Hasher_DEFAULT();

    // SENHAS DA LISTA DA NORDPASS WORLDWIDE (SEP-2024/SEP-2025)
    String[] senhasNordpass = {
            "123456",
            "admin",
            "12345678",
            "123456789",
            "12345",
            "password",
            "Aa123456",
            "1234567890",
            "Pass@123",
            "admin123",
            "1234567",
            "123123",
            "111111",
            "12345678910",
            "P@ssw0rd",
            "Password",
            "Aa@123456",
            "admintelecom",
            "Admin@123",
            "112233",
            "102030",
            "654321",
            "abcd1234",
            "abc123",
            "qwerty123",
            "Abcd@1234",
            "Pass@1234",
            "11223344",
            "admin@123",
            "87654321",
            "987654321",
            "qwerty",
            "123123123",
            "1q2w3e4r",
            "Aa112233",
            "12341234",
            "qwertyuiop",
            "11111111",
            "Admin",
            "Password@123",
            "asd123",
            "Aboy1234",
            "123321",
            "admin1",
            "Admin123",
            "Demo@123",
            "1q2w3e4r5t",
            "admin1234",
            "aa123456",
            "121212",
            "asdf1234",
            "888888",
            "Abcd1234",
            "123456789",
            "guru123456",
            "666666",
            "Welcome@123",
            "guest",
            "Password1",
            "123456789a",
            "Kapler123",
            "administrator",
            "1122334455",
            "Test@123",
            "qwer1234",
            "asdfghjkl",
            "Global123@",
            "10203040",
            "1234qwer",
            "India@123",
            "Abcd@123",
            "1qaz2wsx",
            "88888888",
            "123qwe",
            "12345678a",
            "secret",
            "Aa123123",
            "12344321",
            "123456aA@",
            "123456a",
            "a123456",
            "202020",
            "1234abcd",
            "admin123456",
            "qwe123",
            "101010",
            "222222",
            "12121212",
            "welcome",
            "abc12345",
            "Abc@1234",
            "admin12345",
            "Qwerty123",
            "12345678900",
            "123654",
            "555555",
            "Aa123456789",
            "1111111111",
            "12345678901",
            "q1w2e3r4",
            "password123",
            "Heslo1234",
            "22446688",
            "Abc12345",
            "vodafone",
            "999999",
            "bismillah",
            "a123456789",
            "Password123",
            "azerty",
            "user1234",
            "1234567891",
            "1234512345",
            "adminisp",
            "1234567899",
            "P@$$w0rd",
            "Aa12345678",
            "Passw0rd",
            "zxcvbnm",
            "adminadmin",
            "qwerty12345",
            "gvt12345",
            "minecraft",
            "abcd@1234",
            "pakistan",
            "ADMIN",
            "10203",
            "Welcome1",
            "theworldinyourhand",
            "aabb1122",
            "test123",
            "Asdf1234",
            "54321",
            "1111111",
            "a1b2c3d4",
            "password1",
            "student",
            "Abc@12345",
            "Aa102030",
            "Pass@12345",
            "135790",
            "123abc",
            "cisco",
            "11111",
            "Aa@12345",
            "111111111",
            "p@ssw@rd",
            "lol123456",
            "147258369",
            "123456Aa",
            "Aa@1234567",
            "Admin@1234",
            "1234554321",
            "124578",
            "12qwaszx",
            "Abc@123",
            "a12345678",
            "aa112233",
            "qwer4321",
            "a1234567",
            "Qwerty@123",
            "12345679",
            "Ab123456",
            "Aa@123456789",
            "Abcd1234@",
            "123qweasd",
            "Admin1234",
            "pakistan123",
            "A123456a",
            "qwerty1234",
            "1234567a",
            "abc123456",
            "turktelekom",
            "test1234",
            "999999999",
            "123456788",
            "Aaa111",
            "contraseña",
            "7654321",
            "1qazxsw2",
            "Password@1",
            "asdasd",
            "aaaaaa",
            "qwerty123456",
            "246810",
            "11112222",
            "aaaa1111",
            "Abc123",
            "q1w2e3r4t5",
            "987654",
            "aa123123",
            "azerty123",
            "Aa1234567",
            "abc@123",
            "changeme",
            "12345678@",
            "P@55w0rd",
            "asd12345",
            "zxcvbnm123",
            "123admin"
    };

    public String[] get10kList() {
        String[] list10k = new String[10000];

        try {
            File list10kFile = new File("C:\\hashcat-7.1.2\\experimento_benchmark_seguranca\\10_million_password_list_top_10000.txt");
            Scanner reader = new Scanner(list10kFile);
            int i = 0;
            while(reader.hasNextLine()) {
                list10k[i] = reader.nextLine();
                i++;
            }
            reader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return list10k;
    }

    public void generateTxts(ArrayList<String> hashList, String txtName) {
        try (FileWriter writer = new FileWriter("C:\\hashcat-7.1.2\\experimento_benchmark_seguranca\\" + txtName + ".txt")) {
            System.out.println(hashList.size());
            for (int i = 0; i < hashList.size(); i++) {
                if(i == hashList.size()-1) {
                    writer.write(hashList.get(i));
                }  else {
                    writer.write(hashList.get(i) + "\n");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void generate2SetTxts() {
        // NORDPASS LIST

        ArrayList<String> bcryptHashes_NORDPASS = new ArrayList<>(gerenciadorHashers.insertPasswords(bCryptPasswordEncoder, senhasNordpass));
        ArrayList<String> scryptHashes_NORDPASS = new ArrayList<>(gerenciadorHashers.insertPasswords(sCryptPasswordEncoder, senhasNordpass));
        ArrayList<String> pbkdf2Hashes_NORDPASS = new ArrayList<>(gerenciadorHashers.insertPasswords(pbkdf2PasswordEncoder, senhasNordpass));
        ArrayList<String> argon2Hashes_NORDPASS = new ArrayList<>(gerenciadorHashers.insertPasswords(argon2PasswordEncoder, senhasNordpass));

        generateTxts(bcryptHashes_NORDPASS, "bcryptHashes_NORDPASS");
        generateTxts(scryptHashes_NORDPASS, "scryptHashes_NORDPASS");
        generateTxts(pbkdf2Hashes_NORDPASS, "pbkdf2Hashes_NORDPASS");
        generateTxts(argon2Hashes_NORDPASS, "argon2Hashes_NORDPASS");

        // 10K PASSWORDS LIST

        String[] list10k = get10kList();

        ArrayList<String> bcryptHashes_10K = new ArrayList<>(gerenciadorHashers.insertPasswords(bCryptPasswordEncoder, list10k));
        ArrayList<String> scryptHashes_10k = new ArrayList<>(gerenciadorHashers.insertPasswords(sCryptPasswordEncoder, list10k));
        ArrayList<String> pbkdf2Hashes_10k = new ArrayList<>(gerenciadorHashers.insertPasswords(pbkdf2PasswordEncoder, list10k));
        ArrayList<String> argon2Hashes_10k = new ArrayList<>(gerenciadorHashers.insertPasswords(argon2PasswordEncoder, list10k));

        generateTxts(bcryptHashes_10K, "bcryptHashes_10k");
        generateTxts(scryptHashes_10k, "scryptHashes_10k");
        generateTxts(pbkdf2Hashes_10k, "pbkdf2Hashes_10k");
        generateTxts(argon2Hashes_10k, "argon2Hashes_10k");
    }


}
