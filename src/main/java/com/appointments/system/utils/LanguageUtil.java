package com.appointments.system.utils;

import java.util.Locale;
import java.util.ResourceBundle;

public class LanguageUtil {

    private static String selectedLanguage = getSystemLanguage();
    private static String selectedCountry = getSystemOrigin();

    public static void main(String[] args) {
        System.out.println(getString("wish"));
    }

    public static String getString(String key){
        String lang = "fr";
        String country = "US";
//        setSelectedCountry(country);
        setSelectedLanguage(lang);
        Locale locale = new Locale(getSelectedLanguage(), getSelectedCountry());
        ResourceBundle resourceBundle = ResourceBundle.getBundle("bundle", locale);
        return resourceBundle.getString(key);
    }

    public static String getSystemLanguage() {
        return System.getProperty("user.language");
    }

    public static String getSystemOrigin() {
        return System.getProperty("user.language");
    }

    public static String getSelectedLanguage() {
        return selectedLanguage;
    }

    public static String getSelectedCountry() {
        return selectedCountry;
    }

    public static void setSelectedLanguage(String selectedLanguage) {
        LanguageUtil.selectedLanguage = selectedLanguage;
    }

    public static void setSelectedCountry(String selectedCountry) {
        LanguageUtil.selectedCountry = selectedCountry;
    }
}
