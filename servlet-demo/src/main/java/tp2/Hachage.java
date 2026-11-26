package tp2;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class Hachage {
    public static String sha256(String texte) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] octets = md.digest(texte.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : octets) sb.append(String.format("%02x", b)); // octet -> hexadécimal
            return sb.toString();   // 64 caractères
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}