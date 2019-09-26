/*
 */
package com.lawyersys.pclsystembe.abm;

import com.lawyersys.pclsystembacke.entities.Cuentas;
import com.lawyersys.pclsystembacke.entities.DetalleCuenta;
import com.lawyersys.pclsystembacke.entities.DetalleFactura;
import com.lawyersys.pclsystembacke.entities.Facturas;
import com.lawyersys.pclsystembacke.entities.Recibos;
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
        } else if (entidad == "DetalleCuenta") {
            Query q = em.createNamedQuery(entidad + ".findByCodDetalleCuenta")
                    .setParameter("codDetalleCuenta", Integer.parseInt(id));
            return q.getResultList();
        } else if (entidad == "Facturas") {
            Query q = em.createNamedQuery(entidad + ".findByCodFactura")
                    .setParameter("codFactura", Integer.parseInt(id));
            return q.getResultList();
        } else if (entidad == "DetalleFactura") {
            Query q = em.createNamedQuery(entidad + ".findByCodDetalleFactura")
                    .setParameter("codDetalleFactura", Integer.parseInt(id));
            return q.getResultList();
        } else if (entidad == "Recibos") {
            Query q = em.createNamedQuery(entidad + ".findByCodRecibo")
                    .setParameter("codRecibo", Integer.parseInt(id));
            return q.getResultList();
        }
        return elem;
    }

    public <S> void create(Class<S> clazz, S elem) {
        if (clazz == TiposPagos.class) {
            TiposPagos ta = (TiposPagos) elem;
            em.persist(ta);
        } else if (clazz == Cuentas.class) {
            Cuentas ta = (Cuentas) elem;
            em.persist(ta);
        } else if (clazz == DetalleCuenta.class) {
            DetalleCuenta ta = (DetalleCuenta) elem;
            em.persist(ta);
        } else if (clazz == Facturas.class) {
            Facturas ta = (Facturas) elem;
            em.persist(ta);
        } else if (clazz == DetalleFactura.class) {
            DetalleFactura ta = (DetalleFactura) elem;
            em.persist(ta);
        } else if (clazz == Recibos.class) {
            Recibos ta = (Recibos) elem;
            em.persist(ta);
        }
    }

    public <S> void edit(Class<S> clazz, S elem) {
        if (clazz == TiposPagos.class) {
            TiposPagos ta = (TiposPagos) elem;
            em.merge(ta);
        } else if (clazz == Cuentas.class) {
            Cuentas ta = (Cuentas) elem;
            em.merge(ta);
        } else if (clazz == DetalleCuenta.class) {
            DetalleCuenta ta = (DetalleCuenta) elem;
            em.merge(ta);
        } else if (clazz == Facturas.class) {
            Facturas ta = (Facturas) elem;
            em.merge(ta);
        } else if (clazz == DetalleFactura.class) {
            DetalleFactura ta = (DetalleFactura) elem;
            em.merge(ta);
        } else if (clazz == Recibos.class) {
            Recibos ta = (Recibos) elem;
            em.merge(ta);
        }
    }
    
}
