/*
 */
package com.lawyersys.pclsystembe.abm;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author tatoa
 */
@Stateless(name="ABMManagerActividades")
public class ABMManagerActividades {
    
    @PersistenceContext(unitName = "lawyersys")
    EntityManager em; // Para ejecutar queries en la base de datos

    public List<Object> findAll(String entidad) {
        Query q = em.createNamedQuery(entidad + ".findAll");
        return q.getResultList();
    }
    
    public Object find(String entidad, String id) {
        List<Object> elem = null;
        
        
        return elem;
    }
    
}
