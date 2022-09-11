package com.appointments.system.repo;


import com.appointments.system.model.Appointments;
import com.appointments.system.utils.HibernateUtil;

import java.util.List;

public class AppointmentsDao extends AbsDAO<Appointments> {

    public AppointmentsDao() {
        super.sessionFactory = HibernateUtil.getSessionFactory();
        setClazz(Appointments.class);
    }

    public static void main(String[] args) {
        AppointmentsDao dao = new AppointmentsDao();
        List<Appointments> all = dao.findAll();
        for (Appointments c:all){
            System.out.println(c);
        }
    }
}
