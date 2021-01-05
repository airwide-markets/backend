package za.co.airwide.marketplace.util;

import org.mindrot.BCrypt;

/**
 * https://stackoverflow.com/questions/36480375/whats-the-java-equivalent-for-phps-password-hash-and-password-verify
 *
 *  I made a few tweaks to the lib, to allow the use of a random salt,
 *  instead of passing a new one each time you call hashpw method, and to support multiple versions of salt.
 */
public class HashUtil {

    /**
     * To replicate the password_hash you can use:
     * String hash = BCrypt.hashpw("password");
     */
    public static String hash(String password) {
        return BCrypt.hashpw( password );
    }

    /**
     * And to replicate password_verify use:
     * boolean s = BCrypt.checkpw("password", hash);
     */
    public static boolean passwordVerify(String password, String hash) {
        return BCrypt.checkpw(password, hash);
    }

    public static void main(String[] args) {

        String password = "changeit";
        String hash = hash(password);
        System.out.println("hash: " + hash);

        boolean isValid = passwordVerify(password, hash);
        System.out.println("isValid: " + isValid);

        isValid = passwordVerify("changeitXXXXXXX", hash);
        System.out.println("isValid: " + isValid);

        String hash1 = "$2y$10$omZGqdLeM/vcOf/D/dF2ZOW3kCbV/0rFzFBpyXPpDshgFZE9X7q5e";
        isValid = passwordVerify("password", hash1);
        System.out.println("isValid: " + isValid);
    }
}
