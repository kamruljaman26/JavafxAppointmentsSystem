package com.appointments.system.utils;

import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * Used to manage zone based date and time difference
 */
public class DateTimeUtil {

    public static ZoneId SYSTEM_ZONE_ID = ZonedDateTime.now().getZone();
    public static ZoneId UTC_ZONE_ID = ZoneOffset.UTC;
    public static ZoneId EST_ZONE_ID = ZoneId.of("America/New_York");

    public static ZonedDateTime UTC_ZONE_DATE_TIME = ZonedDateTime.now();
    public static ZonedDateTime EST_ZONE_DATE_TIME =
            ZonedDateTime.ofInstant(Instant.now(), EST_ZONE_ID);
    public static ZonedDateTime SYSTEM_ZONE_DATE_TIME = ZonedDateTime.now();

    public static void main(String[] args) {
        //ZonedDateTime is the right class
//        System.out.println(UTC_ZONE_DATE_TIME);
//        System.out.println(EST_ZONE_DATE_TIME);
//        System.out.println(SYSTEM_ZONE_DATE_TIME);
//
//        // Generate text
//        DateTimeFormatter f = DateTimeFormatter.ofPattern("MM/dd/uuuu HH:mm:ss.SSS");
//        String output = SYSTEM_ZONE_DATE_TIME.format(f);
////        System.out.println(output);
//
//        // Adjusting zones
//        // Same moment, same point on the timeline, different wall-clock time.
//        ZonedDateTime zdtPhoenix = ZonedDateTime.now(ZoneId.of("America/Phoenix"));
//        ZonedDateTime zdtNewYork = zdtPhoenix.withZoneSameInstant(ZoneId.of("America/New_York"));
//        System.out.println(EST_ZONE_DATE_TIME.getOffset().getTotalSeconds());

        System.out.println(UTC_ZONE_ID);
    }

}
