package com.appointments.system.controller;

import com.appointments.system.utils.LanguageUtil;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        LocalDateTime dateTime = LanguageUtil.changeDateTime("2022-09-17 01:24:49");
        System.out.println(dateTime.getDayOfMonth());
        System.out.println(dateTime.getDayOfYear());
        System.out.println(dateTime.getYear());

        System.out.println();

        System.out.println(calendar.getTime());
        System.out.println(calendar.getTime().getYear());

        System.out.println();
        for (int i = 0; i < 7; i++) {
            Date time = calendar.getTime();
            System.out.println("" + calendar.getTime());
            calendar.add(Calendar.DATE, 1);
        }

    }
}
