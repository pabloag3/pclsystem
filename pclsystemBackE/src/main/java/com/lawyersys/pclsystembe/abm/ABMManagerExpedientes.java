/*
 */
package com.lawyersys.pclsystembe.abm;

import com.lawyersys.pclsystembacke.entities.Casos;
import com.lawyersys.pclsystembacke.entities.Clientes;
import com.lawyersys.pclsystembacke.entities.DetalleExpediente;
import com.lawyersys.pclsystembacke.entities.EstadosCaso;
import com.lawyersys.pclsystembacke.entities.Expedientes;
import com.lawyersys.pclsystembacke.entities.TiposActuaciones;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Pablo Aguilar
 */
@Stateless(name="ABMManagerExpedientes")
public class ABMManagerExpedientes {
        
    @PersistenceContext(unitName = "lawyersys")
    EntityManager em; // Para ejecutar queries en la base de datos
    
    public List<Object> findAll(String entidad) {
        Query q = em.createNamedQuery(entidad + ".findAll");
        return q.getResultList();
    }
    
    public Object find(String entidad, String id) {
        List<Object> elem = null;
        if (entidad == "Casos") {
            Query q = em.createNamedQuery(entidad + ".findByCodCaso")
                    .setParameter("codCaso", Integer.parseInt(id));
            return q.getSingleResult();
        } else if (entidad == "Clientes") {
            Query q = em.createNamedQuery(entidad + ".findByCodCliente")
                    .setParameter("codCliente", Integer.parseInt(id));
            return q.getSingleResult();
        } else if (entidad == "DetalleExpediente") {
            Query q = em.createNamedQuery(entidad + ".findByCodDetalleExpediente")
                    .setParameter("codDetalleExpediente", Integer.parseInt(id));
            return q.getSingleResult();
        } else if (entidad == "EstadosCaso") {
            Query q = em.createNamedQuery(entidad + ".findByCodEstadoCaso")
                    .setParameter("codEstadoCaso", Integer.parseInt(id));
            return q.getSingleResult();
        } else if (entidad == "Expedientes") {
            Query q = em.createNamedQuery(entidad + ".findByCodExpediente")
                    .setParameter("codExpediente", Integer.parseInt(id));
            return q.getSingleResult();
        } else if (entidad == "TiposActuaciones") {
            Query q = em.createNamedQuery(entidad + ".findByCodTipoActuacion")
                    .setParameter("codTipoActuacion", Integer.parseInt(id));
            return q.getSingleResult();
        }
        return elem;
    }
    
    public <S> void create(Class<S> clazz, S elem) {
        if (clazz == Casos.class) {
            Casos ta = (Casos) elem;
            em.persist(ta);
        } else if (clazz == Clientes.class) {
            Clientes ta = (Clientes) elem;
            em.persist(ta);
        } else if (clazz == DetalleExpediente.class) {
            DetalleExpediente ta = (DetalleExpediente) elem;
            em.persist(ta);
        } else if (clazz == EstadosCaso.class) {
            EstadosCaso ta = (EstadosCaso) elem;
            em.persist(ta);
        } else if (clazz == Expedientes.class) {
            Expedientes ta = (Expedientes) elem;
            em.persist(ta);
        } else if (clazz == TiposActuaciones.class) {
            TiposActuaciones ta = (TiposActuaciones) elem;
            em.persist(ta);
        }
    }

    public <S> void edit(Class<S> clazz, S elem) {
        if (clazz == Casos.class) {
            Casos ta = (Casos) elem;
            em.merge(ta);
        } else if (clazz == Clientes.class) {
            Clientes ta = (Clientes) elem;
            em.merge(ta);
        } else if (clazz == DetalleExpediente.class) {
            DetalleExpediente ta = (DetalleExpediente) elem;
            em.merge(ta);
        } else if (clazz == EstadosCaso.class) {
            EstadosCaso ta = (EstadosCaso) elem;
            em.merge(ta);
        } else if (clazz == Expedientes.class) {
            Expedientes ta = (Expedientes) elem;
            em.merge(ta);
        } else if (clazz == TiposActuaciones.class) {
            TiposActuaciones ta = (TiposActuaciones) elem;
            em.merge(ta);
        }
    }

    
}
