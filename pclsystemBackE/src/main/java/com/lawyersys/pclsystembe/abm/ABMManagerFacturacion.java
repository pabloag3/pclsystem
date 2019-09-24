/*
 */
package com.lawyersys.pclsystembe.abm;

import com.lawyersys.pclsystembacke.entities.TiposPagos;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author tatoa
 */
@Stateless(name="ABMManagerFacturacion")
public class ABMManagerFacturacion {
    
    @PersistenceContext(unitName = "lawyersys")
    EntityManager em; // Para ejecutar queries en la base de datos

    public List<Object> findAll(String entidad) {
        Query q = em.createNamedQuery(entidad + ".findAll");
        return q.getResultList();
    }
    
    public List<Object> find(String entidad, String id) {
        List<Object> elem = null;
        if (entidad == "TiposPagos") {
            Query q = em.createNamedQuery(entidad + ".findByCodTipoPago")
                    .setParameter("codTipoPago", Integer.parseInt(id));
            return q.getResultList();
        } else if (entidad == "Cuentas") {
            Query q = em.createNamedQuery(entidad + ".findByCodCuenta")
                    .setParameter("codTipoPago", Integer.parseInt(id));
            return q.getResultList();
        }
        return elem;
    }

    public <S> void create(Class<S> clazz, S elem) {
        if (clazz == TiposPagos.class) {
            TiposPagos ta = (TiposPagos) elem;
            em.persist(ta);
        }
    }

    public <S> void edit(Class<S> clazz, S elem) {
        if (clazz == TiposPagos.class) {
            TiposPagos ta = (TiposPagos) elem;
            em.merge(ta);
        }
    }
    
}
