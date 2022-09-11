package com.appointments.system.repo;


import com.appointments.system.model.Countries;
import com.appointments.system.model.Customers;
import com.appointments.system.utils.HibernateUtil;

import java.util.List;

public class CountriesDao extends AbsDAO<Countries> {

    public CountriesDao() {
        super.sessionFactory = HibernateUtil.getSessionFactory();
        setClazz(Countries.class);
    }

    public static void main(String[] args) {
        CountriesDao dao = new CountriesDao();
        List<Countries> all = dao.findAll();
        for (Countries c:all){
            System.out.println(c);
        }
    }
}
