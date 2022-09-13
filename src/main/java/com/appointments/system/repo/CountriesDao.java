package com.appointments.system.repo;


import com.appointments.system.model.Countries;
import com.appointments.system.model.Customers;
import com.appointments.system.utils.HibernateUtil;

import java.util.List;

public class CountriesDao extends AbsDAO<Countries> {

    public CountriesDao() {
        sessionFactory = HibernateUtil.getSessionFactory();
        setClazz(Countries.class);
    }

}
