package com.appointments.system.aExample;

import com.appointments.system.utils.DateTimeUtil;

import java.time.*;

public class Main2 {
    public static void main(String[] args) {

        String date = "2022-12-24";
        String time = "18:00";

        ZonedDateTime system =
                ZonedDateTime.of(LocalDate.parse(date), LocalTime.parse(time), DateTimeUtil.SYSTEM_ZONE_ID);
        ZonedDateTime est = system.withZoneSameInstant(DateTimeUtil.EST_ZONE_ID);
        ZonedDateTime utc = system.withZoneSameInstant(DateTimeUtil.EST_ZONE_ID);

        System.out.println(system);
        System.out.println(est);
        System.out.println(utc);

    }
}
