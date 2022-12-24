package com.appointments.system.aExample;

import java.sql.Timestamp;
import java.time.*;
import java.time.zone.ZoneRules;
import java.util.Set;
import java.util.TimeZone;

public class Main {
    public static void main(String[] args) {
        ZoneRules of = ZoneRules.of(ZoneOffset.UTC);
        System.out.println(of);

        Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();
        System.out.println(availableZoneIds);

        Instant now = Instant.now();
        System.out.println(now.toString());

        ZoneId ZONEID_EUROPE_PARIS = ZoneId.of("Europe/Paris");
        ZONEID_EUROPE_PARIS.getRules();
        System.out.println(ZONEID_EUROPE_PARIS);

        ZoneId ZONEID_ASIA_KOLKATA = ZoneId.of("Asia/Kolkata");
        System.out.println(ZONEID_ASIA_KOLKATA);

        LocalDateTime noon1June2017Anywhere = LocalDateTime.of( 2017 , Month.JUNE , 1 , 12 , 0);
        ZonedDateTime noon1June2017EuropeParis = noon1June2017Anywhere.atZone( ZONEID_EUROPE_PARIS ) ;
        ZonedDateTime noon1June2017AsiaKolkata = noon1June2017Anywhere.atZone( ZONEID_ASIA_KOLKATA ) ;


        Instant instantNoon1June2017EuropeParis = noon1June2017EuropeParis.toInstant() ;  // Extract the same moment but in UTC zone.
        Instant instantNoon1June2017AsiaKolkata = noon1June2017AsiaKolkata.toInstant() ; // Extract the same moment but in UTC zone.

        System.out.println(instantNoon1June2017AsiaKolkata);
        System.out.println(instantNoon1June2017EuropeParis);

        Timestamp timestamp = Timestamp.from(Instant.ofEpochMilli(0));
        System.out.println();
        System.out.println(System.currentTimeMillis());

        System.out.println("\n ---------------------------------------------------------------- \n");

        Instant instant = Instant.now();
        LocalDateTime local = LocalDateTime.now();
        ZonedDateTime zone = ZonedDateTime.now();

        System.out.println("====== WITHOUT 'UTC' TIME ZONE ======");
        System.out.println("instant           : " + instant );
        System.out.println("local             : " + local);
        System.out.println("zone              : " + zone);
        System.out.println("instant converted : " + instant.atZone(ZoneId.of("Europe/Paris")).toLocalDateTime());
        System.out.println("====================================");

        TimeZone.setDefault(TimeZone.getTimeZone("EST"));

        Instant instant2 = Instant.now();
        LocalDateTime local2 = LocalDateTime.now();
        ZonedDateTime zone2 = ZonedDateTime.now();

        System.out.println("====== WITH 'UTC' TIME ZONE ======");
        System.out.println("instant2           : " + instant2 );
        System.out.println("local2             : " + local2);
        System.out.println("zone2              : " + zone2);
        System.out.println("instant2 converted : " + instant2.atZone(ZoneId.of("Europe/Paris")).toLocalDateTime());
        System.out.println("==================================");
    }
}
