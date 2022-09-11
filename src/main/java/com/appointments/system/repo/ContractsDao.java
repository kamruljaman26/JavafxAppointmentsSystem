package com.appointments.system.repo;


import com.appointments.system.model.Appointments;
import com.appointments.system.model.Contacts;
import com.appointments.system.utils.HibernateUtil;

import java.util.List;

public class ContractsDao extends AbsDAO<Contacts> {

    public ContractsDao() {
        super.sessionFactory = HibernateUtil.getSessionFactory();
        setClazz(Contacts.class);
    }

    public static void main(String[] args) {
        ContractsDao dao = new ContractsDao();
        List<Contacts> all = dao.findAll();
        for (Contacts c:all){
            System.out.println(c);
        }
    }
}
