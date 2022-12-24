package com.appointments.system.controller;

import com.appointments.system.utils.DateTimeUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;

public class main {
    public static void main(String[] args) {
        String date = "2022-12-24";
        String time = "18:00";

        ZonedDateTime utc =
                ZonedDateTime.of(LocalDate.parse(date), LocalTime.parse(time), DateTimeUtil.UTC_ZONE_ID);

        ZonedDateTime est = utc.withZoneSameInstant(DateTimeUtil.EST_ZONE_ID);
        System.out.println(est);
    }
}
