package com.appointments.system.repo;


import com.appointments.system.model.Contacts;
import com.appointments.system.model.Users;
import com.appointments.system.utils.HibernateUtil;

import java.util.List;

public class UsersDao extends AbsDAO<Users> {

    public UsersDao() {
        super.sessionFactory = HibernateUtil.getSessionFactory();
        setClazz(Users.class);
    }

    public static void main(String[] args) {
        UsersDao dao = new UsersDao();
        List<Users> all = dao.findAll();
        for (Users c:all){
            System.out.println(c);
        }
    }
}
