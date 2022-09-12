package com.appointments.system.repo;


import com.appointments.system.model.Customers;
import com.appointments.system.utils.HibernateUtil;

import java.util.List;

public class CustomerDao extends AbsDAO<Customers> {
    public CustomerDao() {
        sessionFactory = HibernateUtil.getSessionFactory();
        setClazz(Customers.class);
    }

    public static void main(String[] args) {
        CustomerDao dao = new CustomerDao();
        List<Customers> all = dao.findAll();
        for (Customers c:all){
            System.out.println(c);
        }
    }
}
