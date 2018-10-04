package com.gfs.projects.common.db.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DAOFactory {

    static Log log = LogFactory.getLog(DAOFactory.class);

    /**
     * The idea is to switch implementations of DAO interfaces by modifying the 
     * static fields below. This allows loose coupling without having to use
     * dependency injection frameworks and such..
     */
    private static GenericDAO instantiateDAO(Class daoClass) {
        try {
            GenericDAO dao = (GenericDAO)daoClass.newInstance();
            return dao;
        } catch (Exception e) {
            log.error("",e);
            throw new RuntimeException("Couldn't instantiate DAO: "+e);
        }
    }

}
