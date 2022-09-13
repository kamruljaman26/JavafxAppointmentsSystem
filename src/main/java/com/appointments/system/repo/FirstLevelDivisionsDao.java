package com.appointments.system.repo;


import com.appointments.system.model.Customers;
import com.appointments.system.model.FirstLevelDivisions;
import com.appointments.system.utils.HibernateUtil;

import java.util.List;

public class FirstLevelDivisionsDao extends AbsDAO<FirstLevelDivisions> {
    public FirstLevelDivisionsDao() {
        sessionFactory = HibernateUtil.getSessionFactory();
        setClazz(FirstLevelDivisions.class);
    }
}
