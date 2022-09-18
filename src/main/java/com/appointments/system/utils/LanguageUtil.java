package com.appointments.system.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public class LanguageUtil {

    private static Locale locale = Locale.getDefault();
    private static String selectedLanguage = locale.getLanguage();
    private static String selectedCountry = locale.getCountry();

    /**
     *
     * @return system time zone
     */
    public static String getTimeZone(){
        return TimeZone.getDefault().getID();
    }

    public static boolean isOverlapping(LocalTime start1, LocalTime end1, LocalTime start2, LocalTime end2) {
        return start1.isBefore(end2) && start2.isBefore(end1);
    }

    /**
     * Convert from UTC to local system time zone
     */
    public static LocalDateTime changeDateTime(String dt) throws ParseException {
        if(dt.contains("T")) dt = dt.replace("T", " ");
        SimpleDateFormat sdfOriginal = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdfOriginal.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date1 = sdfOriginal.parse(dt);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone(getTimeZone()));
        return LocalDateTime.parse(sdf.format(calendar.getTime()).replace(" ", "T"));
    }

    /**
     * Get string from resource bundle
     *
     * @param key bundle key
     */
    public static String getString(String key) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("bundle", locale);
        return resourceBundle.getString(key);
    }

    // return current selected language
    public static String getLanguage() {
        return selectedLanguage;
    }

    // return current selected country
    public static String getCountry() {
        return selectedCountry;
    }

    /**
     * Set/change interface country
     * @param country os
     */
    public static void setCountry(String country) {
        locale = new Locale(getLanguage(), country); // update locale
        LanguageUtil.selectedCountry = country;
    }

    /**
     * Set/change interface language
     * @param language os
     */
    public static void setLanguage(String language) {
        locale = new Locale(language, getCountry()); // update locale
        LanguageUtil.selectedLanguage = language; // selected language
    }

    // get current locale
    public static Locale getLocale() {
        return locale;
    }
}
