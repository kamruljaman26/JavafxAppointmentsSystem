package com.appointments.system.repo;


import com.appointments.system.model.Appointments;
import com.appointments.system.utils.HibernateUtil;

import java.util.List;

public class AppointmentsDao extends AbsDAO<Appointments> {

    public AppointmentsDao() {
        sessionFactory = HibernateUtil.getSessionFactory();
        setClazz(Appointments.class);
    }

}
