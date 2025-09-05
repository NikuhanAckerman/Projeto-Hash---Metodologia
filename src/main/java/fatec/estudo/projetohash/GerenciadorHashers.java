package fatec.estudo.projetohash;

import org.apache.commons.lang3.time.StopWatch;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import java.util.ArrayList;

public class GerenciadorHashers {

    public Argon2PasswordEncoder argon2Hasher_DEFAULT = Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8();

    public SCryptPasswordEncoder scryptHasher_DEFAULT = SCryptPasswordEncoder.defaultsForSpringSecurity_v5_8();

    public Pbkdf2PasswordEncoder pbkdf2Hasher_DEFAULT = Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8();

    public BCryptPasswordEncoder bcryptHasher_DEFAULT = new BCryptPasswordEncoder();

    public Argon2PasswordEncoder getArgon2Hasher_DEFAULT() {
        return argon2Hasher_DEFAULT;
    }

    public void setArgon2Hasher_DEFAULT(Argon2PasswordEncoder argon2Hasher_DEFAULT) {
        this.argon2Hasher_DEFAULT = argon2Hasher_DEFAULT;
    }

    public SCryptPasswordEncoder getScryptHasher_DEFAULT() {
        return scryptHasher_DEFAULT;
    }

    public void setScryptHasher_DEFAULT(SCryptPasswordEncoder scryptHasher_DEFAULT) {
        this.scryptHasher_DEFAULT = scryptHasher_DEFAULT;
    }

    public Pbkdf2PasswordEncoder getPbkdf2Hasher_DEFAULT() {
        return pbkdf2Hasher_DEFAULT;
    }

    public void setPbkdf2Hasher_DEFAULT(Pbkdf2PasswordEncoder pbkdf2Hasher_DEFAULT) {
        this.pbkdf2Hasher_DEFAULT = pbkdf2Hasher_DEFAULT;
    }

    public BCryptPasswordEncoder getBcryptHasher_DEFAULT() {
        return bcryptHasher_DEFAULT;
    }

    public void setBcryptHasher_DEFAULT(BCryptPasswordEncoder bcryptHasher_DEFAULT) {
        this.bcryptHasher_DEFAULT = bcryptHasher_DEFAULT;
    }

    public long hashPasswords(PasswordEncoder passwordEncoder, String[] passwords) {
        StopWatch watch = new StopWatch();

        watch.start();
        for (String password : passwords) {
            passwordEncoder.encode(password);
        }
        watch.stop();

        return watch.getTime();
    }

    public ArrayList<String> insertPasswords(PasswordEncoder passwordEncoder, String[] passwords) {
        ArrayList<String> hashedPasswords = new ArrayList<>();
        for (String password : passwords) {
            hashedPasswords.add(passwordEncoder.encode(password));
        }
        return hashedPasswords;
    }

}
