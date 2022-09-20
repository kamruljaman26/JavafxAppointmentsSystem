package com.appointments.system.repo;


import com.appointments.system.model.Appointments;
import com.appointments.system.model.Contacts;
import com.appointments.system.utils.HibernateUtil;

import java.util.List;

public class ContactsDao extends AbsDAO<Contacts> {

    public ContactsDao() {
        sessionFactory = HibernateUtil.getSessionFactory();
        setClazz(Contacts.class);
    }

}
