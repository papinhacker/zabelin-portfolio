package zabelin.portfolio.core.ui;

import com.saucelabs.saucerest.SauceREST;
import zabelin.portfolio.core.common.Listener;
import zabelin.portfolio.core.common.Log;

import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.Map;

public class MobileAppInfoHolder {
    private static final String SEPARATOR_CHAR = ".";

    public MobileAppInfoHolder() {
    }

    public static SauceREST getSouceRest() throws Exception {
        Map<String, String> sauceLabsCredentials = Listener.getSauceLabsCredentials();
        String userName = sauceLabsCredentials.get("username");
        String password = sauceLabsCredentials.get("password");
        return new SauceREST(userName, password);
    }

    private static String getFileChecksum(File file) {
        try {
            byte[] b = Files.readAllBytes(Paths.get(file.getAbsolutePath()));
            byte[] hash = MessageDigest.getInstance("MD5").digest(b);
            return DatatypeConverter.printHexBinary(hash);
        } catch (Exception var3) {
            Log.error(var3.getMessage());
            return "";
        }
    }

    public String checkAndUpload(File file) throws Exception {
        String hash = getFileChecksum(file);
        String appId = hash + "." + file.getName();
        SauceREST rest = getSouceRest();
        String storedFiles = rest.getStoredFiles();
        if (!storedFiles.contains(appId)) {
            Log.info("File " + file.getName() + " is uploading to the SauceLab...");
            String md5 = rest.uploadFile(file, appId);
            Log.info("Upload finished without errors. Hash codes equals: " + md5.equalsIgnoreCase(hash));
        } else {
            Log.info("File " + file.getName() + " already exists at SauceLab");
        }

        return appId;
    }
}
