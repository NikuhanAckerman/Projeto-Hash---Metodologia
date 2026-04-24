package fatec.estudo.projetohash;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.profile.GCProfiler;
import org.openjdk.jmh.results.RunResult;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.*;
import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class Performance {

    GeradorSenhas geradorSenhas = new GeradorSenhas();
    GerenciadorHashers gerenciadorHashers = new GerenciadorHashers();
    String[] nordpassList = geradorSenhas.senhasNordpass;
    String[] list10k = geradorSenhas.get10kList();

    String[] random20Length8Array = geradorSenhas.getHighEntropyList(8);
    String[] random20Length12Array = geradorSenhas.getHighEntropyList(12);
    String[] random20Length15Array = geradorSenhas.getHighEntropyList(15);

    public String[] getRandom20Length8Array() {
        return random20Length8Array;
    }

    public void setRandom20Length8Array(String[] random20Length8Array) {
        this.random20Length8Array = random20Length8Array;
    }

    public String[] getRandom20Length12Array() {
        return random20Length12Array;
    }

    public void setRandom20Length12Array(String[] random20Length12Array) {
        this.random20Length12Array = random20Length12Array;
    }

    public String[] getRandom20Length15Array() {
        return random20Length15Array;
    }

    public void setRandom20Length15Array(String[] random20Length15Array) {
        this.random20Length15Array = random20Length15Array;
    }

    /* NORDPASS LIST

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

    // 10k LIST

    @Benchmark
    public void hashWithBcrypt_10kList() {
        gerenciadorHashers.hashPasswords(gerenciadorHashers.bcryptHasher_DEFAULT, list10k);
    }

    @Benchmark
    public void hashWithScrypt_10kList() {
        gerenciadorHashers.hashPasswords(gerenciadorHashers.scryptHasher_DEFAULT, list10k);
    }

    @Benchmark
    public void hashWithPbkdf2_10kList() {
        gerenciadorHashers.hashPasswords(gerenciadorHashers.pbkdf2Hasher_DEFAULT, list10k);
    }

    @Benchmark
    public void hashWithArgon2_10kList() {
        gerenciadorHashers.hashPasswords(gerenciadorHashers.argon2Hasher_DEFAULT, list10k);
    }


     */


    // CHAOTIC PASSWORDS LIST

    // 20 LENGTH 8

    @Benchmark
    public void hashWithBcrypt_20Length8Array() {
        gerenciadorHashers.hashPasswords(gerenciadorHashers.bcryptHasher_DEFAULT, getRandom20Length8Array());
    }

    @Benchmark
    public void hashWithScrypt_20Length8Array() {
        gerenciadorHashers.hashPasswords(gerenciadorHashers.scryptHasher_DEFAULT, getRandom20Length8Array());
    }

    @Benchmark
    public void hashWithPbkdf2_20Length8Array() {
        gerenciadorHashers.hashPasswords(gerenciadorHashers.pbkdf2Hasher_DEFAULT, getRandom20Length8Array());
    }

    @Benchmark
    public void hashWithArgon2_20Length8Array() {
        gerenciadorHashers.hashPasswords(gerenciadorHashers.argon2Hasher_DEFAULT, getRandom20Length8Array());
    }

    // 20 LENGTH 12

    @Benchmark
    public void hashWithBcrypt_20Length12Array() {
        gerenciadorHashers.hashPasswords(gerenciadorHashers.bcryptHasher_DEFAULT, getRandom20Length12Array());
    }

    @Benchmark
    public void hashWithScrypt_20Length12Array() {
        gerenciadorHashers.hashPasswords(gerenciadorHashers.scryptHasher_DEFAULT, getRandom20Length12Array());
    }

    @Benchmark
    public void hashWithPbkdf2_20Length12Array() {
        gerenciadorHashers.hashPasswords(gerenciadorHashers.pbkdf2Hasher_DEFAULT, getRandom20Length12Array());
    }

    @Benchmark
    public void hashWithArgon2_20Length12Array() {
        gerenciadorHashers.hashPasswords(gerenciadorHashers.argon2Hasher_DEFAULT, getRandom20Length12Array());
    }

    // 20 LENGTH 15

    @Benchmark
    public void hashWithBcrypt_20Length15Array() {
        gerenciadorHashers.hashPasswords(gerenciadorHashers.bcryptHasher_DEFAULT, getRandom20Length15Array());
    }

    @Benchmark
    public void hashWithScrypt_20Length15Array() {
        gerenciadorHashers.hashPasswords(gerenciadorHashers.scryptHasher_DEFAULT, getRandom20Length15Array());
    }

    @Benchmark
    public void hashWithPbkdf2_20Length15Array() {
        gerenciadorHashers.hashPasswords(gerenciadorHashers.pbkdf2Hasher_DEFAULT, getRandom20Length15Array());
    }

    @Benchmark
    public void hashWithArgon2_20Length15Array() {
        gerenciadorHashers.hashPasswords(gerenciadorHashers.argon2Hasher_DEFAULT, getRandom20Length15Array());
    }

    public void run() throws RunnerException {


        // NORDPASS LIST

        Options opt = new OptionsBuilder()
                .include(Performance.class.getSimpleName())
                .forks(/*2*/1)
                .warmupIterations(/*3*/2)
                .measurementIterations(/*5*/3)
                .addProfiler(GCProfiler.class) // <--- tracks allocations
                .build();

        Collection<RunResult> results = new Runner(opt).run();

        // Map benchmark name -> time & allocated bytes
        Map<String, String> summary = new HashMap<>();

        for (RunResult result : results) {
            String name = result.getParams().getBenchmark();
            double avgTimeMs = result.getPrimaryResult().getScore(); // average time in ms
            double allocatedBytes = result.getSecondaryResults().get("gc.alloc.rate.norm").getScore();
            summary.put(name, String.format("Time: %.2f ms, Alocado: %.0f bytes", avgTimeMs, allocatedBytes));
        }

        System.out.println("\n=== RESULTADOS DO BENCHMARK DE HASHING ===");
        summary.forEach((benchmark, stats) -> System.out.println(benchmark + " -> " + stats));

    }


}
