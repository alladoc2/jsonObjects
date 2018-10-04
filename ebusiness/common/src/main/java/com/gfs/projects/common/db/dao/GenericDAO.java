package com.gfs.projects.common.db.dao;

public interface GenericDAO<T> {
    
    T create(T t);
    
    T update(T t);
    
    void delete(Object id);
    
    T find(Object id);
    
}
