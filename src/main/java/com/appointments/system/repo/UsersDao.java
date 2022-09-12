package com.appointments.system.repo;


import com.appointments.system.model.Contacts;
import com.appointments.system.model.Users;
import com.appointments.system.utils.HibernateUtil;
import org.hibernate.*;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.HashMap;
import java.util.List;

public class UsersDao extends AbsDAO<Users> {

    public UsersDao() {
        sessionFactory = HibernateUtil.getSessionFactory();
        setClazz(Users.class);
    }

    public static void main(String[] args) {
        UsersDao dao = new UsersDao();
        System.out.println(dao.getUserByUsername("admin"));
    }

    // find user by username
    public Users getUserByUsername(String username) {
        // if Transaction is not active
        Transaction tx = getCurrentSession().getTransaction();
        if (!tx.isActive()) tx.begin();

        CriteriaBuilder cb = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Users> cq = cb.createQuery(Users.class);

        Root<Users> root = cq.from(Users.class);
        cq.select(root).where(cb.equal(root.get("userName"), username));
        Query query = getCurrentSession().createQuery(cq);

        if(query.getResultList().isEmpty()){
            return null;
        }

        return (Users) query.getResultList().get(0);
    }
}
