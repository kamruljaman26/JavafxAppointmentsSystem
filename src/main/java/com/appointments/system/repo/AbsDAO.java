package com.appointments.system.repo;

import com.google.common.base.Preconditions;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

public abstract class AbsDAO<T extends Serializable> {

    private Class<T> clazz;
    protected SessionFactory sessionFactory;

    public void setClazz(final Class<T> clazzToSet) {
        clazz = Preconditions.checkNotNull(clazzToSet);
    }

    public T findOne(final long id) {
        // if Transaction is not active
        Transaction tx = getCurrentSession().getTransaction();
        if (!tx.isActive()) tx.begin();

        Session currentSession = getCurrentSession();
        return currentSession.get(clazz, id);
    }

//    find all data from given table
    public List<T> findAll() {
        // if Transaction is not active
        Transaction tx = getCurrentSession().getTransaction();
        if (!tx.isActive()) tx.begin();

        CriteriaBuilder cb = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(clazz);
        Root<T> root = cq.from(clazz);
        cq.select(root);
        Query query = getCurrentSession().createQuery(cq);
        return query.getResultList();
    }

//    insert data in a table
    public T createOrUpdate(final T entity) {
        // if Transaction is not active
        Transaction tx = getCurrentSession().getTransaction();
        if (!tx.isActive()) tx.begin();

        try {
            Preconditions.checkNotNull(entity);
            getCurrentSession().saveOrUpdate(entity);
            tx.commit();
            return entity;
        } catch (Exception ex) {
            tx.rollback();
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    //    insert data in a table
    public T update(final T entity) {
        // if Transaction is not active
        Transaction tx = getCurrentSession().getTransaction();
        if (!tx.isActive()) tx.begin();

        try {
            Preconditions.checkNotNull(entity);
            getCurrentSession().merge(entity);
            tx.commit();
            return entity;
        } catch (Exception ex) {
            tx.rollback();
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    public void delete(final T entity) {
        // if Transaction is not active
        Transaction tx = getCurrentSession().getTransaction();
        if (!tx.isActive()) tx.begin();

        Preconditions.checkNotNull(entity);
        getCurrentSession().delete(entity);
        tx.commit();
    }

    public void deleteById(final long entityId) {
        // if Transaction is not active
        Transaction tx = getCurrentSession().getTransaction();
        if (!tx.isActive()) tx.begin();

        final T entity = findOne(entityId);
        Preconditions.checkState(entity != null);
        delete(entity);
    }

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
