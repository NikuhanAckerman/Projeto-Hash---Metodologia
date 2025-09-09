package fatec.estudo.projetohash;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.profile.GCProfiler;
import org.openjdk.jmh.results.RunResult;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class Performance {

    GeradorSenhas geradorSenhas = new GeradorSenhas();
    GerenciadorHashers gerenciadorHashers = new GerenciadorHashers();
    String[] nordpassList = geradorSenhas.senhasNordpass;

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

    @Benchmark
    public void hashWithBcrypt_NORDPASS() {
        gerenciadorHashers.hashPasswords(gerenciadorHashers.bcryptHasher_DEFAULT, nordpassList);
    }

    @Benchmark
    public void hashWithScrypt_NORDPASS() {
        gerenciadorHashers.hashPasswords(gerenciadorHashers.scryptHasher_DEFAULT, nordpassList);
    }

    @Benchmark
    public void hashWithPbkdf2_NORDPASS() {
        gerenciadorHashers.hashPasswords(gerenciadorHashers.pbkdf2Hasher_DEFAULT, nordpassList);
    }

    @Benchmark
    public void hashWithArgon2_NORDPASS() {
        gerenciadorHashers.hashPasswords(gerenciadorHashers.argon2Hasher_DEFAULT, nordpassList);
    }


    public void run() throws RunnerException {

        // NORDPASS LIST

        Options opt = new OptionsBuilder()
                .include(Performance.class.getSimpleName())
                .forks(/*2*/1)
                .warmupIterations(/*3*/1)
                .measurementIterations(/*5*/2)
                .addProfiler(GCProfiler.class) // <--- tracks allocations
                .addProfiler("jfr")
                .build();

        Collection<RunResult> results = new Runner(opt).run();

        // Map benchmark name -> time & allocated bytes
        Map<String, String> summary = new HashMap<>();

        for (RunResult result : results) {
            String name = result.getParams().getBenchmark();
            double avgTimeMs = result.getPrimaryResult().getScore(); // average time in ms
            double allocatedBytes = result.getSecondaryResults().get("gc.alloc.rate.norm").getScore();
            summary.put(name, String.format("Time: %.2f ms, Allocated: %.0f bytes", avgTimeMs, allocatedBytes));
        }

        System.out.println("\n=== NordPass Hashing Benchmark Summary ===");
        summary.forEach((benchmark, stats) -> System.out.println(benchmark + " -> " + stats));

/*
        long scryptNordpass = gerenciadorHashers.hashPasswords(gerenciadorHashers.getScryptHasher_DEFAULT(), geradorSenhas.senhasNordpass);
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

    }


}
