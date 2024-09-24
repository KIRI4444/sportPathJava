package sportpath.BCrypt;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Repository;

@Repository
public class PasswordUtils {
    public static String hashPassword(String plainTextPassword) {
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }

    public static boolean checkPassword(String plainTextPassword, String hashedPassword) {
        return BCrypt.checkpw(plainTextPassword, hashedPassword);
    }
}
