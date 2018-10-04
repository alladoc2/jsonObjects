package com.gfs.projects.common.db.dao;


import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;



public abstract class GenericDAOJpaImpl<T> implements GenericDAO<T> {

	public static final Logger logger =  Logger.getLogger(GenericDAOJpaImpl.class);

    private EntityManagerFactory emf;
    private Class<T> type;
    
    public GenericDAOJpaImpl() {
        Type t = this.getClass().getGenericSuperclass();
        type = (Class)((ParameterizedType)t).getActualTypeArguments()[0];
        Map props = new HashMap();
        props.put("openjpa.jdbc.Schema", "config.dbSchema");
        this.emf = Persistence.createEntityManagerFactory("txmanager", props);
    }

    
    public T create(T t) {
        EntityManager em = getEm();
        em.getTransaction().begin();
        em.persist(t);
        em.getTransaction().commit();
        em.close();
        return t;
    }

    public void delete(Object id) {
        EntityManager em = getEm();
        em.getTransaction().begin();
        em.remove(em.getReference(type, id));
        em.getTransaction().commit();
        em.close();
    }

    public T find(Object id) {
        T t = null;
        EntityManager em = getEm();
        t = em.find(type, id);            
        em.close();
        return t;
    }

    public T update(T t) {
        EntityManager em = getEm();
        em.getTransaction().begin();
        t = em.merge(t);
        em.getTransaction().commit();
        em.close();
        return t;
    }
    
    EntityManager getEm() {
        return emf.createEntityManager();
    }

}
