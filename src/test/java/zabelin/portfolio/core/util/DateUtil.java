//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package zabelin.portfolio.core.util;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUtil {
    private static final SimpleDateFormat YYYY_MM_DD_HH_SS_MM_SSS = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
    private static final SimpleDateFormat YYYY_MM_DD_HH_MM_SS = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss");
    private static final SimpleDateFormat YYYY_MM_DD_HH_MM_SS_SSS = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss_SSS");
    public static final SimpleDateFormat MM_DD_YYYY = new SimpleDateFormat("MM/dd/yyyy");
    private static final SimpleDateFormat YYYY_MM_DD = new SimpleDateFormat("yyyy/MM/dd");
    public static final SimpleDateFormat MM_DD_YY = new SimpleDateFormat("MM/dd/yy");
    private static final SimpleDateFormat MM_DD_YYYY_KKMM = new SimpleDateFormat("MM/dd/yyyy kk:mm");
    private static final SimpleDateFormat YYYY_MM_DD_KKMMSS = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
    private static final SimpleDateFormat MM_DD_YYYY_HHMMSS_A = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
    private static final SimpleDateFormat YYYY_MM_DD_HH_MM_SS_S = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
    private static final SimpleDateFormat H_MM_AM_PM = new SimpleDateFormat("h:mm a");
    private static final SimpleDateFormat ISO_8601_DATE_TIME = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    public static final SimpleDateFormat MMM_DD_YYYY = new SimpleDateFormat("MMM dd,yyyy");
    public static final SimpleDateFormat DAY_YYYY_MM_DD_HH_MM_SS_Z = new SimpleDateFormat("E_yyyy.MM.dd_HH.mm.ss_z");

    protected DateUtil() {
    }

    public static final Date parseDatePerFormat(String strToParse, SimpleDateFormat format) throws ParseException {
        return format.parse(strToParse);
    }

    public static final Date parseDateWithTimeZone(String strToParse) throws ParseException {
        YYYY_MM_DD_HH_SS_MM_SSS.setTimeZone(TimeZone.getTimeZone("UTC"));
        return YYYY_MM_DD_HH_SS_MM_SSS.parse(strToParse);
    }

    public static final Date parseMilitaryWithSecondsDBDate(String strToParse) throws ParseException {
        return YYYY_MM_DD_KKMMSS.parse(strToParse);
    }

    public static final Date parseMonthDayYearHourMinSecAmPm(String strToParse) throws ParseException {
        return MM_DD_YYYY_HHMMSS_A.parse(strToParse);
    }

    public static final Date parseMilitaryHourMin(String strTime) throws ParseException {
        return MM_DD_YYYY_KKMM.parse(getCurrentDate("MM/dd/yyyy") + " " + strTime);
    }

    public static String formatMonthDayYear(Date date) {
        return formatDate(date, MM_DD_YYYY);
    }

    public static String formatCurrentMonthDayYear() {
        return formatDate(new Date(), MM_DD_YYYY);
    }

    public static String formatCurrentYearMonthDay() {
        return formatDate(new Date(), YYYY_MM_DD);
    }

    public static String formatCurrentMonthDay2DigitYear() {
        return formatDate(new Date(), MM_DD_YY);
    }

    public static String formatCurrentDateTime() {
        return formatDate(new Date(), YYYY_MM_DD_HH_MM_SS);
    }

    public static String formatCurrentDateTimeSSS() {
        return formatDate(new Date(), YYYY_MM_DD_HH_MM_SS_SSS);
    }

    public static String formatDateTime(Date date) {
        return formatDate(date, YYYY_MM_DD_HH_SS_MM_SSS);
    }

    public static String formatCurrentTime_H_MM_AM_PM() {
        return formatDate(new Date(), H_MM_AM_PM);
    }

    public static String formatCurrectDayYYYYMMDDHHMMSSTimeZone() {
        return formatDate(new Date(), DAY_YYYY_MM_DD_HH_MM_SS_Z);
    }

    public static String isoCurrentDateTime() {
        return formatDate(new Date(), ISO_8601_DATE_TIME);
    }

    public static String getCurrentTimeFormat(String format) {
        SimpleDateFormat timeFormat = new SimpleDateFormat(format);
        return formatDate(new Date(), timeFormat);
    }

    public static String getPastDate(int days) {
        Date date = new Date();
        date = addDays(date, days * -1);
        return formatDate(date, MM_DD_YYYY_HHMMSS_A).trim();
    }

    public static String getCurrentDate(String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        Date date = new Date();
        return dateFormat.format(date).trim();
    }

    public static String formatDate(Date date, SimpleDateFormat formatter) {
        return formatter.format(date);
    }

    public static Date addDays(Date d, int days) {
        d.setTime(d.getTime() + (long) (days * 1000 * 60 * 60 * 24));
        return d;
    }

    public static Date subtractDays(Date d, int days) {
        d.setTime(d.getTime() - (long) (days * 1000 * 60 * 60 * 24));
        return d;
    }

    public static String getPastMonth() {
        SimpleDateFormat format = new SimpleDateFormat("MM");
        Calendar cal = Calendar.getInstance();
        cal.add(2, -1);
        return format.format(2).trim();
    }

    public static String getCurrentMonth() {
        SimpleDateFormat format = new SimpleDateFormat("MM");
        Date date = new Date();
        return format.format(date).trim();
    }

    public static String getCurrentYear() {
        SimpleDateFormat format = new SimpleDateFormat("YYYY");
        Date date = new Date();
        return format.format(date).trim();
    }

    public static String getCurrentDay() {
        SimpleDateFormat format = new SimpleDateFormat("DD");
        Date date = new Date();
        return format.format(date).trim();
    }

    public static String getPastYear() {
        SimpleDateFormat format = MM_DD_YY;
        Calendar cal = Calendar.getInstance();
        cal.add(1, -1);
        return format.format(cal.getTime()).trim();
    }

    public static boolean compareTwoDates(Date date1, Date date2) {
        boolean isOlder = false;
        if (date1.before(date2)) {
            isOlder = true;
        }

        return isOlder;
    }

    public static final Date parseDate(String strTime) throws ParseException {
        SimpleDateFormat format = MM_DD_YY;
        Date date = format.parse(strTime);
        return date;
    }

    public static String getMonth(int month) {
        String date = (new DateFormatSymbols()).getMonths()[month];
        return date;
    }

    public static String getMonthDateYearTime(String[] args) {
        Date now = new Date();
        String date = DateFormat.getDateTimeInstance(1, 3).format(now);
        return date;
    }

    public static String getDateMonthLong(String[] args) {
        Date now = new Date();
        String date = DateFormat.getDateInstance(1).format(now);
        return date;
    }
}
