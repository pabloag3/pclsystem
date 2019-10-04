/*
 */
package com.lawyersys.pclsystembe.abm;

import com.lawyersys.pclsystembacke.entities.Cuentas;
import com.lawyersys.pclsystembacke.entities.DetalleCuenta;
import com.lawyersys.pclsystembacke.entities.DetalleFactura;
import com.lawyersys.pclsystembacke.entities.DetalleFacturaPK;
import com.lawyersys.pclsystembacke.entities.Facturas;
import com.lawyersys.pclsystembacke.entities.Pagos;
import com.lawyersys.pclsystembacke.entities.Recibos;
import com.lawyersys.pclsystembacke.entities.TiposPagos;
import java.util.Date;
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
        } else if (entidad == "Pagos") {
            Query q = em.createNamedQuery(entidad + ".findByCodPago")
                    .setParameter("codPago", Integer.parseInt(id));
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
        } else if (clazz == Pagos.class) {
            Pagos pago = (Pagos) elem;
            pago.setFechaPago(new Date());
            
            em.persist(pago);
        } else if (clazz == Cuentas.class) {
            Cuentas ta = (Cuentas) elem;
            em.persist(ta);
        } else if (clazz == DetalleCuenta.class) {
            DetalleCuenta ta = (DetalleCuenta) elem;
            em.persist(ta);
        } else if (clazz == Facturas.class) {
            Facturas factura = (Facturas) elem;
            factura.setFechaEmision(new Date());
            
            // capturo el ultimo pago realizado para la generacion de la factura
            Query q = em.createNamedQuery("Pagos.findUltimoPago");
            Pagos ultimoPago = (Pagos) q.setMaxResults(1).getResultList();
            factura.setCodPago(ultimoPago);
            
            em.persist(factura);
        } else if (clazz == DetalleFactura.class) {
            DetalleFactura detalleFactura = (DetalleFactura) elem;
            
            Query q = em.createNamedQuery("Facturas.findUltimaFactura");
            Facturas ultimaFactura = (Facturas) q.setMaxResults(1).getResultList();
            
            DetalleFacturaPK detalleFacturaPK = null;
            detalleFacturaPK.setCodFactura(ultimaFactura.getFacturasPK().getCodFactura());
            detalleFactura.setDetalleFacturaPK(detalleFacturaPK);
            
            detalleFactura.setMontoIva((detalleFactura.getMonto() * detalleFactura.getPorcentajeIva() / 100));
            
            em.persist(detalleFactura);
        } else if (clazz == Recibos.class) {
            Recibos recibo = (Recibos) elem;
            
            // capturo el ultimo pago realizado para la generacion del recibo
            Query q = em.createNamedQuery("Pagos.findUltimoPago");
            Pagos ultimoPago = (Pagos) q.setMaxResults(1).getResultList();
            recibo.setCodPago(ultimoPago);
            
            em.persist(recibo);
        }
    }

    public <S> void edit(Class<S> clazz, S elem) {
        if (clazz == TiposPagos.class) {
            TiposPagos ta = (TiposPagos) elem;
            em.merge(ta);
        } else if (clazz == Pagos.class) {
            Pagos pago = (Pagos) elem;
            pago.setFechaPago(new Date());
            
            em.merge(pago);
        } else if (clazz == Cuentas.class) {
            Cuentas ta = (Cuentas) elem;
            em.merge(ta);
        } else if (clazz == DetalleCuenta.class) {
            DetalleCuenta ta = (DetalleCuenta) elem;
            em.merge(ta);
        } else if (clazz == Facturas.class) {
            Facturas factura = (Facturas) elem;
            factura.setFechaEmision(new Date());
            em.merge(factura);
        } else if (clazz == DetalleFactura.class) {
            DetalleFactura detalleFactura = (DetalleFactura) elem;
            
            detalleFactura.setMontoIva((detalleFactura.getMonto() * detalleFactura.getPorcentajeIva() / 100));
            
            em.merge(detalleFactura);
        } else if (clazz == Recibos.class) {
            Recibos ta = (Recibos) elem;
            em.merge(ta);
        }
    }
    
}
