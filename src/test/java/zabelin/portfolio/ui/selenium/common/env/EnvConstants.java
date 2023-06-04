/**
 * Copyright (c) 2016-2017 Severotek, LLC.
 * All Rights Reserved.
 * <p>
 * This software is the confidential and proprietary information of
 * Severotek, LLC. ("Confidential Information").
 * <p>
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered
 * into with Severotek.
 * <p>
 * Copyright Version 1.0
 */
package zabelin.portfolio.ui.selenium.common.env;

import org.testng.annotations.Test;
import zabelin.portfolio.core.common.Log;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EnvConstants extends zabelin.portfolio.core.common.EnvConstants {

    protected static final String mpropkey = System.getProperty("mpropkey");

    //URLs
    public static final String BASE_PAGE = getProperty("base.page");
    public static final String HOME_PAGE = BASE_PAGE;
    public static final String BROKEN_IMAGES_PAGE = BASE_PAGE + "broken_images";



    /**
     * Get property value and decrypt it
     *
     * @param property property name
     * @return
     */
    protected static String getEncryptedProperty(String property) {
        String propertyValue = getProperty(property);
        if (mpropkey == null || mpropkey.isEmpty()) {
            return propertyValue;
        }
        return new EncryptSettings(mpropkey.getBytes()).decryptString(propertyValue);
    }

    /**
     * Get property and split it to list
     *
     * @param property
     * @param delimiter
     * @return
     */
    protected static List<String> getListProperty(String property, String delimiter) {
        return Arrays
                .stream(getProperty(property).split(delimiter))
                .collect(Collectors.toList());
    }

    /**
     * Get property and split it to list by default delimiter
     *
     * @param property
     * @return
     */
    protected static List<String> getListProperty(String property) {
        return getListProperty(property, "\\|");
    }

    /**
     * Get property, split it to list and add postfix for every item
     *
     * @param property
     * @param delimiter
     * @param addPostfix
     * @return
     */
    protected static List<String> getListPropertyWithPostfix(String property, String delimiter, String addPostfix) {
        return Arrays
                .stream(getProperty(property).split(delimiter))
                .map((s) -> s + addPostfix)
                .collect(Collectors.toList());
    }

    /**
     * Get property, split it to list by default delimiter and add postfix for every item
     *
     * @param property
     * @param addPostfix
     * @return
     */
    protected static List<String> getListPropertyWithPostfix(String property, String addPostfix) {
        return getListPropertyWithPostfix(property, "\\|", addPostfix);
    }

    @Test
    public static void encryptString() throws Exception {

        String stringToEncrypt = "PUT HERE A STRING TO ENCRYPT. Don't forget to remove your value from here.";

        if (mpropkey == null || mpropkey.isEmpty())
            throw new Exception("Specify decryption key using '-Dmpropkey'");
        Log.debug("String to encrypt: " + stringToEncrypt);
        String encryptedString = new EncryptSettings(mpropkey.getBytes()).encryptString(stringToEncrypt);
        Log.debug("Encrypted string: " + encryptedString);
    }

    @Test
    public static void decryptProperty() {
        Log.debug("Decrypted property: " + "123");
    }
}
