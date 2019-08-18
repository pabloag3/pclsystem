/*
 */
package com.lawyersys.pclsystembe.abm;

import com.lawyersys.pclsystembacke.entities.Actividades;
import com.lawyersys.pclsystembacke.entities.EstadosActividades;
import com.lawyersys.pclsystembacke.entities.TiposActividades;
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
        if (entidad == "TiposActividades") {
            Query q = em.createNamedQuery(entidad + ".findByCodTipoActividad")
                    .setParameter("codTipoActividad", Integer.parseInt(id));
            return q.getSingleResult();
        } else if (entidad == "EstadosActividades") {
            Query q = em.createNamedQuery(entidad + ".findByCodEstado")
                    .setParameter("codEstado", Integer.parseInt(id));
            return q.getSingleResult();
        } else if (entidad == "Actividades") {
            Query q = em.createNamedQuery(entidad + ".findByCodActividad")
                    .setParameter("codActividad", Integer.parseInt(id));
            return q.getSingleResult();
        }
        return elem;
    }

    public <S> void create(Class<S> clazz, S elem) {
        if (clazz == TiposActividades.class) {
            TiposActividades ta = (TiposActividades) elem;
            em.persist(ta);
        } else if (clazz == EstadosActividades.class) {
            EstadosActividades ta = (EstadosActividades) elem;
            em.persist(ta);
        } else if (clazz == Actividades.class) {
            Actividades ta = (Actividades) elem;
            em.persist(ta);
        }
    }

    public <S> void edit(Class<S> clazz, S elem) {
        if (clazz == TiposActividades.class) {
            TiposActividades ta = (TiposActividades) elem;
            em.merge(ta);
        } else if (clazz == EstadosActividades.class) {
            EstadosActividades ta = (EstadosActividades) elem;
            em.merge(ta);
        } else if (clazz == Actividades.class) {
            Actividades ta = (Actividades) elem;
            em.merge(ta);
        }
    }
    
}
