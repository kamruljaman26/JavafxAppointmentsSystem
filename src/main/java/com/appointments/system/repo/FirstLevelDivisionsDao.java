package com.appointments.system.repo;


import com.appointments.system.model.Customers;
import com.appointments.system.model.FirstLevelDivisions;
import com.appointments.system.utils.HibernateUtil;

import java.util.List;

public class FirstLevelDivisionsDao extends AbsDAO<FirstLevelDivisions> {
    public FirstLevelDivisionsDao() {
        super.sessionFactory = HibernateUtil.getSessionFactory();
        setClazz(FirstLevelDivisions.class);
    }

    public static void main(String[] args) {
        FirstLevelDivisionsDao dao = new FirstLevelDivisionsDao();
        List<FirstLevelDivisions> all = dao.findAll();
        for (FirstLevelDivisions c:all){
            System.out.println(c);
        }
    }
}
