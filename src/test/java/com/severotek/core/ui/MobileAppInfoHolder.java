//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.severotek.core.ui;

import com.saucelabs.saucerest.SauceREST;
import com.severotek.core.common.Listener;
import com.severotek.core.common.Log;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.Map;
import javax.xml.bind.DatatypeConverter;

public class MobileAppInfoHolder {
    private static final String SEPARATOR_CHAR = ".";

    public MobileAppInfoHolder() {
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

    public static SauceREST getSouceRest() throws Exception {
        Map<String, String> sauceLabsCredentials = Listener.getSauceLabsCredentials();
        String userName = (String)sauceLabsCredentials.get("username");
        String password = (String)sauceLabsCredentials.get("password");
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
}
