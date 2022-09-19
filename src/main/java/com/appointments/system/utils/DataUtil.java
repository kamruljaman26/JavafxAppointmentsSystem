package com.appointments.system.utils;

import com.appointments.system.model.Appointments;
import com.appointments.system.repo.AppointmentsDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class DataUtil {

    /**
     * @param days
     * @return
     */
    public static synchronized ObservableList<Appointments> getAppointmentsByDays(int days) {
        ObservableList<Appointments> appointments = FXCollections.observableArrayList();
        Calendar calendar = Calendar.getInstance();

        // search all appointment to find out with date date range
        for (int i = 0; i < days; i++) {
            Date time = calendar.getTime();

            List<Appointments> all = new AppointmentsDao().findAll();
            List<Appointments> allList = all.stream().collect(Collectors.toList());

            for (Appointments a : allList) {
//                System.out.println(a);
                LocalDateTime localStart = a.getLocalStart();
                int year1 = localStart.getYear();
                int year2 = time.getYear() + 1900;

                int month1 = localStart.toLocalDate().getMonthValue();
                int month2 = time.getMonth() + 1;

                int day1 = localStart.toLocalDate().getDayOfMonth();
                int day2 = time.getDate();

                // if before current time
//                System.out.println(day1 + "::" + day2);

                if (year1 == year2 && month1 == month2 && day1 == day2) {
                    // filter today and already added appointments
                    boolean before = LocalTime.now().isAfter(a.getLocalStart().toLocalTime());
                    if (!appointments.contains(a) && !(i == 0 && before)) {
                        appointments.add(a);
                    }
                }
            }

            // increase by 1 day
            calendar.add(Calendar.DATE, 1);
        }

        System.out.println();
        System.out.println();

        return appointments;
    }
}
