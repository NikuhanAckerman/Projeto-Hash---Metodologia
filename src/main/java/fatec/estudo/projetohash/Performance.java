package fatec.estudo.projetohash;

import java.util.ArrayList;
import java.util.HashMap;

public class Performance {

    GeradorSenhas geradorSenhas = new GeradorSenhas();
    GerenciadorHashers gerenciadorHashers = new GerenciadorHashers();

    String[] random20Length8Array = new String[20];
    String[] random20Length12Array = new String[20];
    String[] random20Length15Array = new String[20];

    public void insertIntoStringArrayRandomPasswordLists() {
        ArrayList<ArrayList<String>> listOf20PasswordLists = geradorSenhas.generate3List20Passwords();
        for (ArrayList<String> list : listOf20PasswordLists) {
            int i = 0;
            for (String s : list) {
                if(s.length() == 8) {
                    random20Length8Array[i] = s;
                } else if (s.length() == 12) {
                    random20Length12Array[i] = s;
                } else {
                    random20Length15Array[i] = s;
                }
                i++;
            }
        }
    }

    public void run() {
        HashMap<BenchmarkEnum, Long> measurements = new HashMap<BenchmarkEnum, Long>();

        // NORDPASS LIST

        long bcryptNordpass = gerenciadorHashers.hashPasswords(gerenciadorHashers.getBcryptHasher_DEFAULT(), geradorSenhas.senhasNordpass);
        measurements.put(BenchmarkEnum.NORDPASS_BCRYPT, bcryptNordpass);

        /*long scryptNordpass = gerenciadorHashers.hashPasswords(gerenciadorHashers.getScryptHasher_DEFAULT(), geradorSenhas.senhasNordpass);
        measurements.put(BenchmarkEnum.NORDPASS_SCRYPT, scryptNordpass);

        long pbkdf2Nordpass = gerenciadorHashers.hashPasswords(gerenciadorHashers.getPbkdf2Hasher_DEFAULT(), geradorSenhas.senhasNordpass);
        measurements.put(BenchmarkEnum.NORDPASS_PBKDF2, pbkdf2Nordpass);

        long argon2Nordpass = gerenciadorHashers.hashPasswords(gerenciadorHashers.getArgon2Hasher_DEFAULT(), geradorSenhas.senhasNordpass);
        measurements.put(BenchmarkEnum.NORDPASS_ARGON2, argon2Nordpass);

        // PASSWORD10K LIST

        long password10kBcrypt = gerenciadorHashers.hashPasswords(gerenciadorHashers.getBcryptHasher_DEFAULT(), geradorSenhas.get10kList());
        measurements.put(BenchmarkEnum.PASSWORD10K_BCRYPT, password10kBcrypt);

        long password10kScrypt = gerenciadorHashers.hashPasswords(gerenciadorHashers.getScryptHasher_DEFAULT(), geradorSenhas.get10kList());
        measurements.put(BenchmarkEnum.PASSWORD10K_SCRYPT, password10kScrypt);

        long password10kPbkdf2 = gerenciadorHashers.hashPasswords(gerenciadorHashers.getPbkdf2Hasher_DEFAULT(), geradorSenhas.get10kList());
        measurements.put(BenchmarkEnum.PASSWORD10K_PBKDF2, password10kPbkdf2);

        long password10kArgon2 = gerenciadorHashers.hashPasswords(gerenciadorHashers.getArgon2Hasher_DEFAULT(), geradorSenhas.get10kList());
        measurements.put(BenchmarkEnum.PASSWORD10K_ARGON2, password10kArgon2);

        // RANDOM 20 PASSWORD LENGTH 8 LIST

        long random20length8Bcrypt = gerenciadorHashers.hashPasswords(gerenciadorHashers.getBcryptHasher_DEFAULT(), random20Length8Array);
        measurements.put(BenchmarkEnum.RANDOM20PASSWORDLENGTH8_BCRYPT, random20length8Bcrypt);

        long random20Length8Scrypt = gerenciadorHashers.hashPasswords(gerenciadorHashers.getScryptHasher_DEFAULT(), random20Length8Array);
        measurements.put(BenchmarkEnum.RANDOM20PASSWORDLENGTH8_SCRYPT, random20Length8Scrypt);

        long random20Length8Pbkdf2 = gerenciadorHashers.hashPasswords(gerenciadorHashers.getPbkdf2Hasher_DEFAULT(), random20Length8Array);
        measurements.put(BenchmarkEnum.RANDOM20PASSWORDLENGTH8_PBKDF2, random20Length8Pbkdf2);

        long random20Length8Argon2 = gerenciadorHashers.hashPasswords(gerenciadorHashers.getArgon2Hasher_DEFAULT(), random20Length8Array);
        measurements.put(BenchmarkEnum.RANDOM20PASSWORDLENGTH8_ARGON2, random20Length8Argon2);

        // RANDOM 20 PASSWORD LENGTH 12 LIST

        long random20Length12Bcrypt = gerenciadorHashers.hashPasswords(gerenciadorHashers.getBcryptHasher_DEFAULT(), random20Length12Array);
        measurements.put(BenchmarkEnum.RANDOM20PASSWORDLENGTH12_BCRYPT, random20Length12Bcrypt);

        long random20Length12Scrypt = gerenciadorHashers.hashPasswords(gerenciadorHashers.getScryptHasher_DEFAULT(), random20Length12Array);
        measurements.put(BenchmarkEnum.RANDOM20PASSWORDLENGTH12_SCRYPT, random20Length12Scrypt);

        long random20Length12Pbkdf2 = gerenciadorHashers.hashPasswords(gerenciadorHashers.getPbkdf2Hasher_DEFAULT(), random20Length12Array);
        measurements.put(BenchmarkEnum.RANDOM20PASSWORDLENGTH12_PBKDF2, random20Length12Pbkdf2);

        long random20Length12Argon2 = gerenciadorHashers.hashPasswords(gerenciadorHashers.getArgon2Hasher_DEFAULT(), random20Length12Array);
        measurements.put(BenchmarkEnum.RANDOM20PASSWORDLENGTH12_ARGON2, random20Length12Argon2);

        // RANDOM 20 PASSWORD LENGTH 15 LIST

        long random20Length15Bcrypt = gerenciadorHashers.hashPasswords(gerenciadorHashers.getBcryptHasher_DEFAULT(), random20Length15Array);
        measurements.put(BenchmarkEnum.RANDOM20PASSWORDLENGTH15_BCRYPT, random20Length15Bcrypt);

        long random20Length15Scrypt = gerenciadorHashers.hashPasswords(gerenciadorHashers.getScryptHasher_DEFAULT(), random20Length15Array);
        measurements.put(BenchmarkEnum.RANDOM20PASSWORDLENGTH15_SCRYPT, random20Length15Scrypt);

        long random20Length15Pbkdf2 = gerenciadorHashers.hashPasswords(gerenciadorHashers.getPbkdf2Hasher_DEFAULT(), random20Length15Array);
        measurements.put(BenchmarkEnum.RANDOM20PASSWORDLENGTH15_PBKDF2, random20Length15Pbkdf2);

        long random20Length15Argon2 = gerenciadorHashers.hashPasswords(gerenciadorHashers.getArgon2Hasher_DEFAULT(), random20Length15Array);
        measurements.put(BenchmarkEnum.RANDOM20PASSWORDLENGTH15_ARGON2, random20Length15Argon2);*/

        System.out.println("Medições:");
        System.out.println("BCRYPT - NORDPASS");
        System.out.println(measurements.get(BenchmarkEnum.NORDPASS_BCRYPT));


    }


}
