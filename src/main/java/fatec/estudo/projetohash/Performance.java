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
    String[] weakpassList = geradorSenhas.get10kList();

    // NORDPASS BENCHMARK

    //@Benchmark
    public void hashWithBcrypt_NORDPASS() {
        gerenciadorHashers.hashPasswords(gerenciadorHashers.bcryptHasher_DEFAULT, nordpassList);
    }

    @Benchmark
    public void hashWithScrypt_NORDPASS() {
        gerenciadorHashers.hashPasswords(gerenciadorHashers.scryptHasher_DEFAULT, nordpassList);
    }

    //@Benchmark
    public void hashWithPbkdf2_NORDPASS() {
        gerenciadorHashers.hashPasswords(gerenciadorHashers.pbkdf2Hasher_DEFAULT, nordpassList);
    }

    //@Benchmark
    public void hashWithArgon2_NORDPASS() {
        gerenciadorHashers.hashPasswords(gerenciadorHashers.argon2Hasher_DEFAULT, nordpassList);
    }

    // WEAKPASS BENCHMARK

    //@Benchmark
    public void hashWithBcrypt_WEAKPASS() {
        gerenciadorHashers.hashPasswords(gerenciadorHashers.bcryptHasher_DEFAULT, weakpassList);
    }

    @Benchmark
    public void hashWithScrypt_WEAKPASS() {
        gerenciadorHashers.hashPasswords(gerenciadorHashers.scryptHasher_DEFAULT, weakpassList);
    }

    @Benchmark
    public void hashWithPbkdf2_WEAKPASS() {
        gerenciadorHashers.hashPasswords(gerenciadorHashers.pbkdf2Hasher_DEFAULT, weakpassList);
    }

    //@Benchmark
    public void hashWithArgon2_WEAKPASS() {
        gerenciadorHashers.hashPasswords(gerenciadorHashers.argon2Hasher_DEFAULT, weakpassList);
    }

    public void run() throws RunnerException {

        Options opt = new OptionsBuilder()
                .include(Performance.class.getSimpleName())
                .forks(1)
                .warmupIterations(2)
                .measurementIterations(3)
                .addProfiler(GCProfiler.class) // <--- allocations
                .addProfiler("jfr")
                .build();

        Collection<RunResult> results = new Runner(opt).run();

        // Map benchmark name -> time & allocated bytes
        Map<String, String> summary = new HashMap<>();

        for (RunResult result : results) {
            String name = result.getParams().getBenchmark();
            double avgTimeMs = result.getPrimaryResult().getScore(); // average time in ms
            double allocatedBytes = result.getSecondaryResults().get("gc.alloc.rate.norm").getScore();
            summary.put(name, String.format("AvgTime: %.2f ms, Memory Allocated per operation: %.0f bytes", avgTimeMs, allocatedBytes));
        }

        System.out.println("\n=== Hashing Benchmark Summary ===");
        summary.forEach((benchmark, stats) -> System.out.println(benchmark + " -> " + stats));

    }


}
