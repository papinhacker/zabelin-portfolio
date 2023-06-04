package zabelin.portfolio.ui.selenium.common.env;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

import static java.util.Base64.getDecoder;
import static java.util.Base64.getEncoder;

public final class EncryptSettings {
    private final SecretKey passwordSecretKey;

    private Cipher ecipher;
    private Cipher dcipher;

    public EncryptSettings(byte[] key) {
        this.passwordSecretKey = new PasswordSecretKey(key);
        init();
    }

    private final static class PasswordSecretKey implements SecretKey {

        private final byte[] key;

        public PasswordSecretKey(byte[] key) {
            this.key = key;
        }

        public String getAlgorithm() {
            return "DES";
        }

        public String getFormat() {
            return "RAW";
        }

        public byte[] getEncoded() {
            return key;
        }
    }

    private void init() {
        try {
            ecipher = Cipher.getInstance("DES");
            dcipher = Cipher.getInstance("DES");
            ecipher.init(Cipher.ENCRYPT_MODE, passwordSecretKey);
            dcipher.init(Cipher.DECRYPT_MODE, passwordSecretKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Encrypt string
     *
     * @param stringToEncrypt string for encryption
     * @return encrypted string in Base64
     */
    public String encryptString(String stringToEncrypt) {
        try {
            byte[] utf8 = stringToEncrypt.getBytes(StandardCharsets.UTF_8);
            byte[] enc = ecipher.doFinal(utf8);
            return new String(getEncoder().encode(enc), StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Decrypt string
     *
     * @param encryptedString encrypted string in Base64
     * @return decrypted string
     */
    public String decryptString(String encryptedString) {
        try {
            byte[] dec = getDecoder().decode(encryptedString);
            byte[] utf8 = dcipher.doFinal(dec);
            return new String(utf8, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}