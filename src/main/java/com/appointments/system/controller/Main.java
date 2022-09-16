package com.appointments.system.controller;

import com.appointments.system.utils.LanguageUtil;

import java.text.ParseException;
import java.time.LocalTime;
import java.util.TimeZone;

public class Main {
    public static void main(String[] args) throws ParseException {
        System.out.println(LanguageUtil.changeDateTime("2022-09-17T23:24:49"));
        System.out.println(LocalTime.parse("12:1"));
    }
}
