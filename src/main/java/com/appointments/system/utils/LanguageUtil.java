package com.appointments.system.utils;

import java.util.Locale;
import java.util.ResourceBundle;

public class LanguageUtil {

    private static Locale locale = Locale.getDefault();
    private static String selectedLanguage = locale.getLanguage();
    private static String selectedCountry = locale.getCountry();

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

    //    // test main
//    public static void main(String[] args) {
//        setLanguage("fr");
//        setCountry("FR");
//        System.out.println(getString("wish"));
//        System.out.println(getLanguage());
//    }
}
