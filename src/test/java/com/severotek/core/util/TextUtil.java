package com.severotek.core.util;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import org.apache.oro.text.PatternCache;
import org.apache.oro.text.PatternCacheLRU;
import org.apache.oro.text.perl.Perl5Util;
import org.apache.oro.text.regex.Perl5Compiler;

public class TextUtil extends StringUtils {
    public static final String REGEX_ALPHANUMERIC;
    public static final String REGEX_NUMERIC;
    public static final String REGEX_NUMERIC_ONLY;
    public static final String REGEX_WITHOUT_WHITESPACE;
    public static final String REGEX_SPACE_ALPHANUMERIC;
    public static final String REGEX_BARCODE;
    public static final String REGEX_US_PHONE;
    public static final String REGEX_PHONE;
    public static final String REGEX_EMAIL;
    public static final String DATE_REGEXP;
    public static final String TIMESTAMP_REGEXP;
    public static final String DATE_REGEXP1;
    public static final String DATE_DOB;
    public static final String DATE_REGEXP_SLASHDATE;
    public static final String DATE_CCYY_REGEXP;
    public static final String MM_DD;
    public static final String MM_DD2;
    public static final String XML_CHAR_AND = "&#38;";
    public static final String REGEX_CAMEL_CASE = "^[A-Z][a-z]+";
    private static Perl5Util m_alphaNumericMatcher;
    private static Perl5Util m_numericMatcher;
    private static Perl5Util m_withoutwhitespaceMatcher;
    private static Perl5Util m_alphaNumericSpaceMatcher;
    private static Perl5Util m_usPhoneMatcher;
    private static Perl5Util m_emailMatcher;
    private static Perl5Util m_dateMatcher;
    private static Perl5Util m_barcodeMatcher;
    public static final SimpleDateFormat ISO_DATETIME_FORMAT;
    public static final SimpleDateFormat ISO_DATETIME_FORMAT_WITH_TZ;
    public static final SimpleDateFormat DATETIME_FORMAT_LONG;
    public static final SimpleDateFormat GMT_DATETIME_FORMAT_LONG;
    public static final SimpleDateFormat DATETIME_FORMAT_SHORT;
    public static final SimpleDateFormat DATETIME_FORMAT_SHORT_70;
    public static final SimpleDateFormat DATE_FORMAT_SHORT;
    public static final SimpleDateFormat TIME_FORMAT_SHORT;
    public static final SimpleDateFormat TIME_FORMAT_24_HOUR;
    public static final String[] wildcardchars;

    public TextUtil() {
    }

    public static boolean hasValue(String text) {
        return isNotNullNotEmpty(text, true);
    }

    public static String replaceTokens(String input, String seperator, String replaceWith) {
        StringBuffer buffer = new StringBuffer();
        if (input != null && input.length() > 0 && replaceWith != null) {
            StringTokenizer stringTokenizer = new StringTokenizer(input, seperator);
            int nTokens = stringTokenizer.countTokens();

            for(int i = 0; i < nTokens; ++i) {
                if (i > 0) {
                    buffer.append(seperator);
                }

                buffer.append(replaceWith);
            }
        }

        return buffer.toString();
    }

    public static String replaceAll(String input, char toReplace, String replaceWith) {
        if (input != null && input.length() != 0 && replaceWith != null) {
            int fromIndex = 0;
            int index = 0;

            StringBuffer buffer;
            for(buffer = new StringBuffer(); (index = input.indexOf(toReplace, fromIndex)) != -1; fromIndex = index + 1) {
                buffer.append(input.substring(fromIndex, index) + replaceWith);
            }

            if (fromIndex < input.length()) {
                buffer.append(input.substring(fromIndex));
            }

            return buffer.toString();
        } else {
            return input;
        }
    }

    public static String replaceAll(String input, String toReplace, String replaceWith) {
        if (input != null && input.length() != 0 && toReplace != null && replaceWith != null) {
            int fromIndex = 0;
            StringBuffer buffer = new StringBuffer();

            int index;
            for(int toReplaceLength = toReplace.length(); (index = input.indexOf(toReplace, fromIndex)) != -1; fromIndex = index + toReplaceLength) {
                buffer.append(input.substring(fromIndex, index) + replaceWith);
            }

            if (fromIndex < input.length()) {
                buffer.append(input.substring(fromIndex));
            }

            return buffer.toString();
        } else {
            return input;
        }
    }

    public static String replaceAll(String input, String toReplace, List<String> replaceWith) {
        int nReplaceWith = replaceWith != null ? replaceWith.size() : 0;
        if (nReplaceWith == 0) {
            return input;
        } else {
            StringBuffer buffer = new StringBuffer(512);
            int fromIndex = 0;
            int index = 0;
            int toReplaceLength = toReplace.length();

            for(int i = 0; (index = input.indexOf(toReplace, fromIndex)) != -1; fromIndex = index + toReplaceLength) {
                String s;
                if (i < nReplaceWith) {
                    s = (String)replaceWith.get(i);
                    ++i;
                } else {
                    s = "";
                }

                buffer.append(input.substring(fromIndex, index) + s);
            }

            if (fromIndex < input.length()) {
                buffer.append(input.substring(fromIndex));
            }

            return buffer.toString();
        }
    }

    public static String truncate(String input, int maxLength) {
        if (input != null && input.trim().length() != 0) {
            input = input.trim();
            int length = input.length();
            if (length <= maxLength) {
                return input;
            } else {
                input = input.substring(0, maxLength);
                int index = input.lastIndexOf(" ");
                if (index != -1) {
                    input = input.substring(0, index);
                }

                return input;
            }
        } else {
            return input;
        }
    }

    public static boolean isAlphanumeric(String value) {
        return m_alphaNumericMatcher.match(REGEX_ALPHANUMERIC, value);
    }

    public static boolean isAlphaAndNumeric(String value) {
        if (isAlphanumeric(value)) {
            if (value.matches("(.*)[0-9]+(.*)") && value.matches("(.*)[a-z]+(.*)")) {
                return true;
            }

            if (value.matches("(.*)[0-9]+(.*)") && value.matches("(.*)[A-Z]+(.*)")) {
                return true;
            }
        }

        return false;
    }

    public static boolean isNumeric(String value) {
        return m_numericMatcher.match(REGEX_NUMERIC, value);
    }

    public static boolean notIncludeWhiteSpaces(String value) {
        return m_withoutwhitespaceMatcher.match(REGEX_WITHOUT_WHITESPACE, value);
    }

    public static boolean isAlphanumericOrSpace(String value) {
        return m_alphaNumericSpaceMatcher.match(REGEX_SPACE_ALPHANUMERIC, value);
    }

    public static boolean isBarcode(String value) {
        return m_barcodeMatcher.match(REGEX_BARCODE, value);
    }

    public static boolean isMMDDYYYYDate(String value) {
        return m_dateMatcher.match(DATE_REGEXP, value);
    }

    public static boolean isMMDDYYYHHMMSS(String value) {
        return m_dateMatcher.match(TIMESTAMP_REGEXP, value);
    }

    public static boolean isDOBDate(String value) {
        return m_dateMatcher.match(DATE_DOB, value);
    }

    public static boolean isMMDDDate(String value) {
        return m_dateMatcher.match(MM_DD, value);
    }

    public static boolean isMMDDDate2(String value) {
        return m_dateMatcher.match(MM_DD2, value);
    }

    public static boolean isInteger(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException var2) {
            return false;
        }
    }

    public static boolean isPositiveInteger(String value) {
        int i = 0;

        try {
            i = Integer.parseInt(value);
        } catch (NumberFormatException var3) {
            return false;
        }

        return i > 0;
    }

    public static boolean isPositiveIntegerGreaterThanEqualToZero(String value) {
        int i = 0;

        try {
            i = Integer.parseInt(value);
        } catch (NumberFormatException var3) {
            return false;
        }

        return i >= 0;
    }

    public static boolean isPositiveLong(String value) {
        long i = 0L;

        try {
            i = Long.parseLong(value);
        } catch (NumberFormatException var4) {
            return false;
        }

        return i > 0L;
    }

    public static boolean isDouble(String value) {
        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException var2) {
            return false;
        }
    }

    public static boolean isFloat(String value) {
        try {
            Float.parseFloat(value);
            return true;
        } catch (NumberFormatException var2) {
            return false;
        }
    }

    public static String generateRandomNumeric(int length) {
        int num = 0;
        StringBuffer value = new StringBuffer("");

        for(int i = 0; i < length; ++i) {
            num = (new Double(Math.random() * 10.0)).intValue();
            value.append(num);
        }

        return value.toString();
    }

    public static String generateRandomNumeric(String prefix, int length) {
        return prefix + generateRandomNumeric(length);
    }

    public static boolean isUsPhone(String value) {
        return m_usPhoneMatcher.match(REGEX_US_PHONE, value);
    }

    public static boolean isPhone(String value) {
        return m_usPhoneMatcher.match(REGEX_PHONE, value);
    }

    public static boolean isEmail(String value) {
        return m_emailMatcher.match(REGEX_EMAIL, value);
    }

    public static void showTrace(String msg) {
        try {
            throw new Exception(msg);
        } catch (Exception var2) {
            var2.printStackTrace();
        }
    }

    public static String maskText(String text, int to, char ch) {
        return maskText(text, 0, to, ch);
    }

    public static String maskText(String text, int to) {
        return maskText(text, 0, to, 'X');
    }

    public static String maskText(String text, int from, int to, char ch) {
        int textLen = text != null ? text.length() : 0;
        StringBuffer maskedText = new StringBuffer(textLen);
        if (from >= 0 && from < to) {
            maskedText.append(text.substring(0, from));

            for(int i = from; i < to; ++i) {
                maskedText.append(ch);
            }

            maskedText.append(text.substring(to));
        }

        return maskedText.toString();
    }

    public static String suppressAttributes(String xml, String[] attrs) {
        StringBuilder aStr = new StringBuilder("(");
        boolean first = true;
        String[] var4 = attrs;
        int var5 = attrs.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            String attr = var4[var6];
            if (first) {
                first = false;
            } else {
                aStr.append("|");
            }

            aStr.append(attr);
        }

        aStr.append(")(=\")([^\"]*)");
        Pattern p = Pattern.compile(aStr.toString());
        Matcher m = p.matcher(xml);
        StringBuffer result = new StringBuffer();

        while(m.find()) {
            m.appendReplacement(result, m.group(1) + m.group(2) + "SUPPRESSED");
        }

        m.appendTail(result);
        return result.toString();
    }

    public static String trim(String in) {
        return in == null ? "" : in.trim();
    }

    public static List<String> splitToList(String text, String delim, boolean caseSensitive) {
        List<String> lList = new ArrayList();
        //int liPos = false;
        //int liPrevPos = false;
        String lsString = noNull(text);
        String lsDelim = noNull(delim);
        int liLen = lsDelim.length();
        if (!lsString.equals("") && !lsDelim.equals("")) {
            if (!caseSensitive) {
                lsString = lsString.toLowerCase();
                lsDelim = lsDelim.toLowerCase();
            }

            int liPrevPos = 0;

            for(int liPos = lsString.indexOf(lsDelim); liPos >= 0; liPos = lsString.indexOf(lsDelim, liPrevPos)) {
                if (liPos == 0) {
                    lList.add("");
                } else {
                    lList.add(text.substring(liPrevPos, liPos));
                }

                liPrevPos = liPos + liLen;
            }

            if (liPrevPos > 0) {
                lList.add(text.substring(liPrevPos));
            }

            if (lList.size() == 0) {
                lList.add(text);
            }

            return lList;
        } else {
            lList.add(text);
            return lList;
        }
    }

    public static String noNull(String text) {
        return text == null ? "" : text;
    }

    public static String noNull(int val) {
        return val == 0 ? "" : Integer.toString(val);
    }

    public static String noNull(long val) {
        return (new Long(val)).intValue() == 0 ? "" : Long.toString(val);
    }

    public static String noNull(Long val) {
        return val != null && val.intValue() != 0 ? val.toString() : "";
    }

    public static String noNull(double val) {
        return (new Double(val)).intValue() == 0 ? "" : Double.toString(val);
    }

    public static String noNull(char val) {
        return new Character(val) == 0 ? "" : Character.toString(val);
    }

    public static String removeNumeric(String text) {
        int size = text.length();
        StringBuffer strBuff = new StringBuffer();

        for(int i = 1; i <= size; ++i) {
            String str = text.substring(i - 1, i);
            if (!isNumeric(str)) {
                strBuff.append(str);
            }
        }

        return strBuff.toString();
    }

    public static boolean isMMDDYYDate(String value) {
        return m_dateMatcher.match(DATE_REGEXP1, value);
    }

    public static boolean isMMSlashDDSlashYYDate(String value) {
        return m_dateMatcher.match(DATE_REGEXP_SLASHDATE, value);
    }

    public static boolean isCCYY(String value) {
        return m_dateMatcher.match(DATE_CCYY_REGEXP, value);
    }


    public static boolean toBoolean(String text, boolean defaultValue) {
        boolean value = defaultValue;
        if (text != null && text.length() > 0) {
            try {
                Boolean b = new Boolean(text);
                value = b;
            } catch (Throwable var4) {
            }
        }

        return value;
    }

    public static float toFloat(String text, float defaultValue) {
        float value = defaultValue;
        if (text != null && text.length() > 0) {
            try {
                value = Float.parseFloat(text);
            } catch (Throwable var4) {
            }
        }

        return value;
    }

    public static double toDouble(String text, double defaultValue) {
        double value = defaultValue;
        if (text != null && text.length() > 0) {
            try {
                value = Double.parseDouble(text);
            } catch (Throwable var6) {
            }
        }

        return value;
    }

    public static long toLong(String text, long defaultValue) {
        long value = defaultValue;
        if (text != null && text.length() > 0) {
            try {
                value = Long.parseLong(text);
            } catch (Throwable var6) {
            }
        }

        return value;
    }

    public static char toChar(String text, char defaultChar) {
        char c = defaultChar;
        String trimText;
        if (text != null && (trimText = text.trim()).length() > 0) {
            c = trimText.charAt(0);
        }

        return c;
    }

    public static boolean isNotNullNotEmpty(String text) {
        return isNotNullNotEmpty(text, false);
    }

    public static boolean isNotNullNotEmpty(String text, boolean trim) {
        if (trim) {
            if (text != null && text.trim().length() > 0) {
                return true;
            }
        } else if (text != null && text.length() > 0) {
            return true;
        }

        return false;
    }

    public static boolean contains(String attribute, String text) {
        int compileOptions1 = '耀';
        PatternCache patternCache1 = new PatternCacheLRU(new Perl5Compiler());
        String regExp = "/.*" + text + ".*/";
        patternCache1.getPattern(regExp, compileOptions1);
        Perl5Util stringMatcher = new Perl5Util(patternCache1);
        return stringMatcher.match(regExp, attribute);
    }

    public static String decodeXML(String text) {
        String returnString = replaceAll(text, "&amp;", "&");
        returnString = replaceAll(returnString, "&apos;", "'");
        returnString = replaceAll(returnString, "&quot;", "\"");
        returnString = replaceAll(returnString, "&lt;", "<");
        returnString = replaceAll(returnString, "&gt;", ">");
        return returnString;
    }

    public static String convertToMMDDPattern(String inputStr) {
        String returnStr = null;
        if (!isNotNullNotEmpty(inputStr)) {
            returnStr = "";
        } else if (isMMDDDate(inputStr) && inputStr.length() == 5) {
            returnStr = inputStr;
        } else {
            StringBuffer sb = new StringBuffer();
            returnStr = replaceAll(inputStr, '-', "/");
            String[] temp = returnStr.split("/");
            if (temp.length != 2) {
                returnStr = "";
            } else {
                String single = temp[0].length() == 1 ? "0" + temp[0] : temp[0];
                sb.append(single);
                sb.append("/");
                single = temp[1].length() == 1 ? "0" + temp[1] : temp[1];
                sb.append(single);
                returnStr = sb.toString();
            }
        }

        return returnStr;
    }

    public static String listToString(List<Object> list, String elementSeparator) {
        return listToString(list, elementSeparator, "", "");
    }

    public static String listToString(List<Object> list, String elementSeparator, String elementEnclosure) {
        return listToString(list, elementSeparator, elementEnclosure, elementEnclosure);
    }

    public static String listToString(List<Object> list, String elementSeparator, String elementPrefix, String elementSuffix) {
        StringBuffer buffer = new StringBuffer();
        Iterator<Object> iterator = list != null ? list.iterator() : null;
        if (iterator != null) {
            elementSeparator = elementSeparator == null ? "" : elementSeparator;
            elementPrefix = elementPrefix == null ? "" : elementPrefix;
            elementSuffix = elementSuffix == null ? "" : elementSuffix;
            boolean first = true;

            while(iterator.hasNext()) {
                Object element = iterator.next();
                if (element != null) {
                    if (first) {
                        first = false;
                    } else {
                        buffer.append(elementSeparator);
                    }

                    buffer.append(elementPrefix).append(element.toString()).append(elementSuffix);
                }
            }
        }

        return buffer.toString();
    }


    public static String ArrayToString(int[] array, String elementSeparator) {
        return ArrayToString(array, elementSeparator, "", "");
    }

    public static String ArrayToString(int[] array, String elementSeparator, String elementEnclosure) {
        return ArrayToString(array, elementSeparator, elementEnclosure, elementEnclosure);
    }

    public static String ArrayToString(int[] array, String elementSeparator, String elementPrefix, String elementSuffix) {
        StringBuffer buffer = new StringBuffer();
        if (array != null) {
            elementSeparator = elementSeparator == null ? "" : elementSeparator;
            elementPrefix = elementPrefix == null ? "" : elementPrefix;
            elementSuffix = elementSuffix == null ? "" : elementSuffix;
            boolean first = true;

            for(int i = 0; i < array.length; ++i) {
                if (first) {
                    first = false;
                } else {
                    buffer.append(elementSeparator);
                }

                buffer.append(elementPrefix).append(array[i]).append(elementSuffix);
            }
        }

        return buffer.toString();
    }

    public static String ArrayToString(long[] array, String elementSeparator) {
        return ArrayToString(array, elementSeparator, "", "");
    }

    public static String ArrayToString(long[] array, String elementSeparator, String elementEnclosure) {
        return ArrayToString(array, elementSeparator, elementEnclosure, elementEnclosure);
    }

    public static String ArrayToString(long[] array, String elementSeparator, String elementPrefix, String elementSuffix) {
        StringBuffer buffer = new StringBuffer();
        if (array != null) {
            elementSeparator = elementSeparator == null ? "" : elementSeparator;
            elementPrefix = elementPrefix == null ? "" : elementPrefix;
            elementSuffix = elementSuffix == null ? "" : elementSuffix;
            boolean first = true;

            for(int i = 0; i < array.length; ++i) {
                if (first) {
                    first = false;
                } else {
                    buffer.append(elementSeparator);
                }

                buffer.append(elementPrefix).append(array[i]).append(elementSuffix);
            }
        }

        return buffer.toString();
    }

    public static String ArrayToString(Object[] array, String elementSeparator) {
        return ArrayToString(array, elementSeparator, "", "");
    }

    public static String ArrayToString(Object[] array, String elementSeparator, String elementEnclosure) {
        return ArrayToString(array, elementSeparator, elementEnclosure, elementEnclosure);
    }

    public static String ArrayToString(Object[] array, String elementSeparator, String elementPrefix, String elementSuffix) {
        StringBuffer buffer = new StringBuffer();
        if (array != null) {
            elementSeparator = elementSeparator == null ? "" : elementSeparator;
            elementPrefix = elementPrefix == null ? "" : elementPrefix;
            elementSuffix = elementSuffix == null ? "" : elementSuffix;
            boolean first = true;

            for(int i = 0; i < array.length; ++i) {
                if (array[i] != null) {
                    if (first) {
                        first = false;
                    } else {
                        buffer.append(elementSeparator);
                    }

                    buffer.append(elementPrefix).append(array[i].toString()).append(elementSuffix);
                }
            }
        }

        return buffer.toString();
    }

    public static int toInteger(String text, int defaultValue) {
        int value = defaultValue;
        if (text != null && text.length() > 0) {
            try {
                value = Integer.parseInt(text);
            } catch (Throwable var4) {
            }
        }

        return value;
    }

    public static byte[] intToByteArray(int intValue) {
        byte[] b = new byte[4];

        for(int i = 0; i < 4; ++i) {
            int offset = (3 - i) * 8;
            b[i] = (byte)(intValue >>> offset & 255);
        }

        return b;
    }

    public static String appendWithSeperator(String text1, String text2, String seperator) {
        int size1 = text1 == null ? 0 : text1.length();
        int size2 = text2 == null ? 0 : text2.length();
        if (size1 > 0 && size2 > 0) {
            return text1.concat(seperator).concat(text2);
        } else {
            return size1 > 0 ? text1 : text2;
        }
    }

    public static String removeSpecialCharacters(String xml) {
        xml = replaceAll(xml, "&amp;apos;", "&#38;apos;");
        xml = replaceAll(xml, "&amp;", "&#38;amp;");
        xml = replaceAll(xml, "&lt;", "&#38;lt;");
        xml = replaceAll(xml, "&gt;", "&#38;gt;");
        return xml;
    }

    public static String removeVerySpecialCharacters(String xml) {
        xml = replaceAll(xml, "&#10;", "%lfTempChangealf%");
        xml = replaceAll(xml, "&", "%ampTempChangeamp%");
        xml = replaceAll(xml, "<", "%ltTempChangelt%");
        xml = replaceAll(xml, ">", "%gtTempChangegt%");
        xml = replaceAll(xml, "'", "%aposTempChangeapos%");
        xml = replaceAll(xml, "&amp;apos;", "&#38;apos;");
        xml = replaceAll(xml, "&amp;", "&#38;amp;");
        xml = replaceAll(xml, "&lt;", "&#38;lt;");
        xml = replaceAll(xml, "&gt;", "&#38;gt;");
        xml = replaceAll(xml, "%ampTempChangeamp%", "&amp;");
        xml = replaceAll(xml, "%ltTempChangelt%", "&lt;");
        xml = replaceAll(xml, "%gtTempChangegt%", "&gt;");
        xml = replaceAll(xml, "%aposTempChangeapos%", "&apos;");
        xml = replaceAll(xml, "%lfTempChangealf%", "&#10;");
        return xml;
    }

    public static String convertCharAndToXmlFormat(String xml) {
        return replaceAll(xml, "&", "&#38;");
    }

    public static String batchReplace(String text, String[][] replpaceMatrix) {
        if (text != null && replpaceMatrix != null) {
            StringBuffer sbPattern = new StringBuffer();
            boolean beginning = true;

            for(int i = 0; i < replpaceMatrix.length; ++i) {
                if (!beginning) {
                    sbPattern.append("|");
                }

                sbPattern.append("(").append(replpaceMatrix[i][0]).append(")");
                beginning = false;
            }

            Pattern p = Pattern.compile(sbPattern.toString());
            Matcher m = p.matcher(text);
            StringBuffer result = new StringBuffer();

            while(true) {
                while(m.find()) {
                    for(int i = 0; i < replpaceMatrix.length; ++i) {
                        if (m.group(i + 1) != null) {
                            m.appendReplacement(result, replpaceMatrix[i][1]);
                            break;
                        }
                    }
                }

                m.appendTail(result);
                return result.toString();
            }
        } else {
            return text;
        }
    }

    public static boolean isEqualString(String text1, String text2, boolean isIgnoreCase) {
        boolean var10000;
        if (text1 != null || text2 != null) {
            label35: {
                if (text1 != null && text2 != null) {
                    if (isIgnoreCase) {
                        if (text1.equalsIgnoreCase(text2)) {
                            break label35;
                        }
                    } else if (text1.equals(text2)) {
                        break label35;
                    }
                }

                var10000 = false;
                return var10000;
            }
        }

        var10000 = true;
        return var10000;
    }

    public static Date convertUTCStringToDate(String date) throws ParseException {
        return ISO_DATETIME_FORMAT.parse(date);
    }

    public static Date convertMMDDYYYYStringToDateTime(String date) throws ParseException {
        return DATETIME_FORMAT_SHORT.parse(date);
    }

    public static Date convertMMDDYYYYStringToDate(String date) throws ParseException {
        return DATE_FORMAT_SHORT.parse(date);
    }

    public static Date convertShortTimeStringToDate(String date) throws ParseException {
        return TIME_FORMAT_SHORT.parse(date);
    }

    public static Date convert24HourStringToDate(String date) throws ParseException {
        return TIME_FORMAT_24_HOUR.parse(date);
    }

    public static String convertTimeTo24HourString(Date time) {
        return TIME_FORMAT_24_HOUR.format(time);
    }

    public static String convertDateToUTCString(Date date) {
        return ISO_DATETIME_FORMAT.format(date);
    }

    public static String convertDateMMDDYYYYString(Date date) {
        return DATE_FORMAT_SHORT.format(date);
    }

    public static String convertDateTimeMMDDYYYYString(Date date) {
        return DATETIME_FORMAT_SHORT.format(date);
    }

    public static String convertShortTimeString(Date date) {
        return TIME_FORMAT_SHORT.format(date);
    }

    public static String convertDoubleToDecimalString(Double data, int decimalDigits) {
        StringBuffer pattern = new StringBuffer(32);
        pattern.append("###");

        for(int i = 0; i < decimalDigits; ++i) {
            if (i == 0) {
                pattern.append(".");
            }

            pattern.append("#");
        }

        DecimalFormat formatter = new DecimalFormat(pattern.toString());
        return formatter.format(data);
    }

    public static char charAtZero(String str) {
        char c = 0;
        if (str != null) {
            c = str.charAt(0);
        }

        return c;
    }

    public static Date stripTimeZoneOffset(Date date) {
        Date result = null;
        if (date != null) {
            try {
                result = DATETIME_FORMAT_LONG.parse(GMT_DATETIME_FORMAT_LONG.format(date));
            } catch (ParseException var3) {
            }
        }

        return result;
    }

    public static long stripTimeZoneOffset(long timeMillis) {
        Date result = stripTimeZoneOffset(new Date(timeMillis));
        return result == null ? 0L : result.getTime();
    }

    public static String toCamelString(String text) {
        return toCamelString(text, "_");
    }

    public static String toCamelString(String text, String delimiter) {
        if (text == null) {
            return null;
        } else {
            StringBuffer buffer = new StringBuffer();
            if (delimiter != null) {
                String[] var3 = text.split(delimiter);
                int var4 = var3.length;

                for(int var5 = 0; var5 < var4; ++var5) {
                    String s = var3[var5];
                    if (isNotNullNotEmpty(s)) {
                        buffer.append(s.substring(0, 1).toUpperCase());
                        if (s.length() > 1) {
                            buffer.append(s.substring(1).toLowerCase());
                        }
                    }
                }
            } else {
                buffer.append(text);
            }

            return buffer.toString();
        }
    }

    public static String encodeXML(String xml) {
        xml = replaceAll(xml, "&", "&amp;");
        xml = replaceAll(xml, "<", "&lt;");
        xml = replaceAll(xml, ">", "&gt;");
        xml = replaceAll(xml, "'", "&apos;");
        xml = replaceAll(xml, "\"", "&quot;");
        return xml;
    }

    public static boolean startWithWildcardCharacters(String value) {
        if (isNotNullNotEmpty(value)) {
            String[] var1 = wildcardchars;
            int var2 = var1.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                String wc = var1[var3];
                if (value.startsWith(wc)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static String getNumericOnly(String value) {
        return hasValue(value) ? value.trim().replaceAll(REGEX_NUMERIC_ONLY, "") : value;
    }

    public static String convertUTCDateToStringWithTimezone(Date date, TimeZone timezone) {
        ISO_DATETIME_FORMAT_WITH_TZ.setTimeZone(timezone);
        return ISO_DATETIME_FORMAT_WITH_TZ.format(date);
    }

    public static boolean isMalformedStringNumber(String number) {
        return isNotNullNotEmpty(number) && !isNumeric(number);
    }

    public static String join(Iterator iterator, char separator) {
        if (iterator == null) {
            return null;
        } else if (!iterator.hasNext()) {
            return "";
        } else {
            Object first = iterator.next();
            if (!iterator.hasNext()) {
                return first == null ? "" : first.toString();
            } else {
                StringBuffer buf = new StringBuffer(256);
                if (first != null) {
                    buf.append(first);
                }

                while(iterator.hasNext()) {
                    buf.append(separator);
                    Object obj = iterator.next();
                    if (obj != null) {
                        buf.append(obj);
                    }
                }

                return buf.toString();
            }
        }
    }

    public static String join(Collection collection, char separator) {
        return collection == null ? null : join(collection.iterator(), separator);
    }

    public static boolean isCamelCase(String value) {
        return value.matches("^[A-Z][a-z]+");
    }

    static {
        ISO_DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.US);
        ISO_DATETIME_FORMAT_WITH_TZ = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.US);
        DATETIME_FORMAT_LONG = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.US);
        GMT_DATETIME_FORMAT_LONG = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.US);
        DATETIME_FORMAT_SHORT = new SimpleDateFormat("MM/dd/yy h:mm a", Locale.US);
        DATETIME_FORMAT_SHORT_70 = new SimpleDateFormat("MM/dd/yyyy h:mm a", Locale.US);
        DATE_FORMAT_SHORT = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
        TIME_FORMAT_SHORT = new SimpleDateFormat("h:mm a", Locale.US);
        TIME_FORMAT_24_HOUR = new SimpleDateFormat("HH:mm", Locale.US);
        wildcardchars = new String[]{"*", "?"};
        REGEX_ALPHANUMERIC = "/^[A-Za-z0-9]+$/";
        REGEX_NUMERIC = "/^[0-9]+$/";
        REGEX_NUMERIC_ONLY = "[^0-9]";
        REGEX_WITHOUT_WHITESPACE = "/^[^\\s]+$/";
        REGEX_SPACE_ALPHANUMERIC = "/^[A-Za-z0-9\\s]+$/";
        REGEX_BARCODE = "/^[A-Za-z0-9\\s\\-\\.\\+\\%\\!\\#\\{\\}\\(\\)\\*\\,\\^\\_\\`\\~\\|\\[\\]\\'\\134\\042]+$/";
        REGEX_US_PHONE = "/^((\\(\\d{3}\\) ?)|(\\d{3}-))?\\d{3}-\\d{4}$/";
        REGEX_EMAIL = "/^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$/";
        DATE_REGEXP = "m!^(0?[1-9]|1[012])[- /.](0?[1-9]|[12][0-9]|3[01])[- /.](19|20)\\d\\d$!";
        REGEX_PHONE = "/^[A-Za-z0-9\\(\\)\\+\\.\\-]/";
        TIMESTAMP_REGEXP = "m!^(0?[1-9]|1[012])[- /.](0?[1-9]|[12][0-9]|3[01])[- /.](19|20)\\d\\d( )(0?[0-9]|1[0-9]|2[0-3])[:](0?[0-9]|[1|2|3|4|5][0-9])[:](0?[0-9]|[1|2|3|4|5][0-9])$!";
        DATE_REGEXP1 = "m!^(0?[1-9]|1[012])[- /.](0?[1-9]|[12][0-9]|3[01])[- /.]\\d\\d$!";
        DATE_DOB = "m!^(0?[1-9]|1[012])[- /.](0?[1-9]|[12][0-9]|3[01])[- /.](18|19|20|21)\\d\\d$!";
        DATE_REGEXP_SLASHDATE = "m!^(0?[1-9]|1[012])/(0?[1-9]|[12][0-9]|3[01])/\\d\\d$!";
        DATE_CCYY_REGEXP = "m!^(19|20)\\d\\d$!";
        MM_DD = "m!^(0?[1-9]|1[012])/(0?[1-9]|[12][0-9]|3[01])$!";
        MM_DD2 = "m!^(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])$!";
        int compileOptions = '耀';
        PatternCache patternCache = new PatternCacheLRU(new Perl5Compiler());
        patternCache.getPattern(REGEX_ALPHANUMERIC, compileOptions);
        patternCache.getPattern(REGEX_NUMERIC, compileOptions);
        patternCache.getPattern(REGEX_WITHOUT_WHITESPACE, compileOptions);
        patternCache.getPattern(REGEX_SPACE_ALPHANUMERIC, compileOptions);
        patternCache.getPattern(REGEX_BARCODE, compileOptions);
        patternCache.getPattern(REGEX_US_PHONE, compileOptions);
        patternCache.getPattern(REGEX_EMAIL, compileOptions);
        patternCache.getPattern(DATE_REGEXP, compileOptions);
        patternCache.getPattern(TIMESTAMP_REGEXP, compileOptions);
        patternCache.getPattern(DATE_DOB, compileOptions);
        patternCache.getPattern(MM_DD, compileOptions);
        patternCache.getPattern(MM_DD2, compileOptions);
        m_alphaNumericMatcher = new Perl5Util(patternCache);
        m_numericMatcher = new Perl5Util(patternCache);
        m_withoutwhitespaceMatcher = new Perl5Util(patternCache);
        m_alphaNumericSpaceMatcher = new Perl5Util(patternCache);
        m_barcodeMatcher = new Perl5Util(patternCache);
        m_usPhoneMatcher = new Perl5Util(patternCache);
        m_emailMatcher = new Perl5Util(patternCache);
        m_dateMatcher = new Perl5Util(patternCache);
        GMT_DATETIME_FORMAT_LONG.setTimeZone(TimeZone.getTimeZone("GMT"));
    }
}
