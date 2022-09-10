package com.appointments.system.repo;


import com.appointments.system.model.Customer;
import com.appointments.system.utils.HibernateUtil;
import org.hibernate.*;

import java.util.List;

public class CustomerDao extends AbsDAO<Customer> {
    public CustomerDao() {
        super.sessionFactory = HibernateUtil.getSessionFactory();
        setClazz(Customer.class);
    }

    public static void main(String[] args) {
        CustomerDao dao = new CustomerDao();
        List<Customer> all = dao.findAll();
        for (Customer c:all){
            System.out.println(c);
        }
    }
}
