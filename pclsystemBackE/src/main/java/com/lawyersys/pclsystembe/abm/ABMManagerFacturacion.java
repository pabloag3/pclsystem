package com.lawyersys.pclsystembe.abm;

import com.lawyersys.pclsystembacke.entities.Cuentas;
import com.lawyersys.pclsystembacke.entities.DetalleCuenta;
import com.lawyersys.pclsystembacke.entities.DetalleCuentaPK;
import com.lawyersys.pclsystembacke.entities.DetalleFactura;
import com.lawyersys.pclsystembacke.entities.DetalleFacturaPK;
import com.lawyersys.pclsystembacke.entities.Facturas;
import com.lawyersys.pclsystembacke.entities.FacturasPK;
import com.lawyersys.pclsystembacke.entities.Pagos;
import com.lawyersys.pclsystembacke.entities.Recibos;
import com.lawyersys.pclsystembacke.entities.TiposPagos;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.ArrayList;
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
    
    public List<Object> traerDetallesDeCuenta(String cuenta) {
        Query q = em.createNamedQuery("DetalleCuenta.findByCodCuenta")
                .setParameter("codCuenta", Integer.parseInt(cuenta));
        return q.getResultList();
    }
    
    public List<Object> traerDetallesDeFactura(String factura) {
        Query q = em.createNamedQuery("DetalleFactura.findByCodFactura")
                .setParameter("codFactura", Integer.parseInt(factura));
        return q.getResultList();
    }
    
    public List<Object> traerUltimaFactura() {
        Query q = em.createNamedQuery("Facturas.findUltimaFactura");
        return q.getResultList();
    }
    
    public List<Object> traerUltimoPago() {
        Query q = em.createNamedQuery("Pagos.findUltimoPago");
        return q.getResultList();
    }
    
    public List<Object> traerUltimoDetalleCuenta() {
        Query q = em.createNamedQuery("DetalleCuenta.traerUltimoDetalleCta");
        return q.getResultList();
    }
    
    public List<Object> traerCuentasPorCaso(String codCaso) {
        Query q = em.createNamedQuery("Cuentas.findByCodCaso")
                .setParameter("codCaso", Integer.parseInt(codCaso));
        return q.getResultList();
    }
    
    public List<Object> traerCuentasPendientesACobrar() {
        Query q = em.createNamedQuery("Cuentas.traerCuentasPendientes");
        return q.getResultList();
    }
    
    public List<Object> traerFacturasDeCuenta(String codCuenta) {
        Query q = em.createNativeQuery("SELECT * \n"
                + "FROM facturas f\n"
                + "JOIN pagos p ON p.cod_pago = f.cod_pago\n"
                + "WHERE p.cod_cuenta = (?1);", Facturas.class);
        q.setParameter(1, Integer.parseInt(codCuenta));
        return q.getResultList();
    }
    
    public List<Object> traerRecibosDeCuenta(String codCuenta) {
        Query q = em.createNativeQuery("SELECT * \n"
                + "FROM recibos r\n"
                + "JOIN pagos p ON p.cod_pago = r.cod_pago\n"
                + "WHERE p.cod_cuenta = (?1);", Recibos.class);
        q.setParameter(1, Integer.parseInt(codCuenta));
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
                    .setParameter("codCuenta", Integer.parseInt(id));
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

    public <S> void create(Class<S> clazz, S elem) throws SQLException, Exception {
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
            DetalleCuenta dc = (DetalleCuenta) elem;
            
            Query q = em.createNativeQuery("select nextval('detalle_cuenta_cod_detalle_cuenta_seq');");
            int secuenciaSiguiente = ((BigInteger) q.getSingleResult()).intValue();
            dc.setDetalleCuentaPK(new DetalleCuentaPK(secuenciaSiguiente+1, dc.getDetalleCuentaPK().getCodCuenta()));
            
            dc.setFecha(new Date());
            
            em.persist(dc);
        } else if (clazz == Facturas.class) {
            Facturas factura = (Facturas) elem;

            // preparamos la pk de factura
            
            // traemos el siguiente valor del codigo de factura
            Query q = em.createNativeQuery("select nextval('facturas_cod_factura_seq');");
            int secuenciaSiguiente = ((BigInteger) q.getSingleResult()).intValue();

            // armamos la secuencia de numeros pertenecientes al numero de factura
            q = em.createNativeQuery("SELECT t.nro_establecimiento FROM timbrados t"
                    + " WHERE t.cedula = '" + factura.getCedulaEmisor().getCedula() + "'"
                    + " AND t.vigente = TRUE"
                    + " LIMIT 1;");
            String nroEstablecimiento = q.getSingleResult().toString();
            nroEstablecimiento = String.format("%3s", nroEstablecimiento).replace(' ','0');
            
            q = em.createNativeQuery("SELECT t.nro_punto_expedicion FROM timbrados t"
                    + " WHERE t.cedula = '" + factura.getCedulaEmisor().getCedula() + "'"
                    + " AND t.vigente = TRUE"
                    + " LIMIT 1;");
            String nroPuntoExpedicion = q.getSingleResult().toString();
            nroPuntoExpedicion = String.format("%3s", nroPuntoExpedicion).replace(' ','0');
            
            q = em.createNativeQuery("SELECT t.nro_sec_actual FROM timbrados t"
                    + " WHERE t.cedula = '" + factura.getCedulaEmisor().getCedula() + "'"
                    + " AND t.vigente = TRUE"
                    + " LIMIT 1;");
            String nroSecActual = q.getSingleResult().toString();
            nroSecActual = String.format("%7s", nroSecActual).replace(' ','0');
            
            String nroFactura = nroEstablecimiento + "-" + nroPuntoExpedicion + "-" + nroSecActual;
            
            factura.setFacturasPK(new FacturasPK(secuenciaSiguiente+1, nroFactura));
            
            factura.setFechaEmision(new Date());
            
            // capturo el ultimo pago realizado para la generacion de la factura
            q = em.createNamedQuery("Pagos.findUltimoPago");
            List<Pagos> ultimoPagoAux = q.setMaxResults(1).getResultList();
            Pagos ultimoPago = (Pagos) ultimoPagoAux.get(0);
            factura.setCodPago(ultimoPago);
            
            factura.setVigente(true);
            
            em.persist(factura);
            
            q = em.createNativeQuery("UPDATE timbrados"
                    + " SET nro_sec_actual = nro_sec_actual + 1"
                    + " WHERE cedula = '" + factura.getCedulaEmisor().getCedula() + "'"
                    + " AND vigente = TRUE;");
            q.executeUpdate();            
        } else if (clazz == DetalleFactura.class) {
            DetalleFactura detalleFactura = (DetalleFactura) elem;
            
            // traemos la ultima factura generada
            Query q = em.createNamedQuery("Facturas.findUltimaFactura");
            List<Facturas> ultimaFacturaAux = q.setMaxResults(1).getResultList();
            Facturas ultimaFactura = (Facturas) ultimaFacturaAux.get(0);
            
            // preparamos la pk del detalle de factura
            
            // traemos el siguiente valor del codigo de factura
            q = em.createNativeQuery("select nextval('detalle_factura_cod_detalle_factura_seq');");
            int secuenciaSiguiente = ((BigInteger) q.getSingleResult()).intValue();
            
            DetalleFacturaPK detalleFacturaPK = new DetalleFacturaPK();
            detalleFacturaPK.setCodDetalleFactura(secuenciaSiguiente);
            detalleFacturaPK.setCodFactura(ultimaFactura.getFacturasPK().getCodFactura());
            detalleFacturaPK.setNroFactura(ultimaFactura.getFacturasPK().getNroFactura());
            detalleFactura.setDetalleFacturaPK(detalleFacturaPK);
            
            detalleFactura.setMontoIva((detalleFactura.getMonto() * detalleFactura.getPorcentajeIva() / 100));
            
            em.persist(detalleFactura);
        } else if (clazz == Recibos.class) {
            Recibos recibo = (Recibos) elem;
            
            // capturo el ultimo pago realizado para la generacion del recibo
            Query q = em.createNamedQuery("Pagos.findUltimoPago");
            List<Pagos> ultimoPagoAux = q.setMaxResults(1).getResultList();
            Pagos ultimoPago = (Pagos) ultimoPagoAux.get(0);

            recibo.setCodPago(ultimoPago);
            
            recibo.setFacturado(false);
            
            recibo.setFechaEmision(new Date());
            
            em.persist(recibo);
        }
    }
    
    public <S> void crearFacturaDeRecibo (Facturas factura) throws SQLException, Exception {
        
        // preparamos la pk de factura
            
        // traemos el siguiente valor del codigo de factura
        Query q = em.createNativeQuery("select nextval('facturas_cod_factura_seq');");
        int secuenciaSiguiente = ((BigInteger) q.getSingleResult()).intValue();

        // armamos la secuencia de numeros pertenecientes al numero de factura
        q = em.createNativeQuery("SELECT t.nro_establecimiento FROM timbrados t"
                + " WHERE t.cedula = '" + factura.getCedulaEmisor().getCedula() + "'"
                + " AND t.vigente = TRUE"
                + " LIMIT 1;");
        String nroEstablecimiento = q.getSingleResult().toString();
        nroEstablecimiento = String.format("%3s", nroEstablecimiento).replace(' ','0');

        q = em.createNativeQuery("SELECT t.nro_punto_expedicion FROM timbrados t"
                + " WHERE t.cedula = '" + factura.getCedulaEmisor().getCedula() + "'"
                + " AND t.vigente = TRUE"
                + " LIMIT 1;");
        String nroPuntoExpedicion = q.getSingleResult().toString();
        nroPuntoExpedicion = String.format("%3s", nroPuntoExpedicion).replace(' ','0');

        q = em.createNativeQuery("SELECT t.nro_sec_actual + 1 FROM timbrados t"
                + " WHERE t.cedula = '" + factura.getCedulaEmisor().getCedula() + "'"
                + " AND t.vigente = TRUE"
                + " LIMIT 1;");
        String nroSecActual = q.getSingleResult().toString();
        nroSecActual = String.format("%7s", nroSecActual).replace(' ','0');

        String nroFactura = nroEstablecimiento + "-" + nroPuntoExpedicion + "-" + nroSecActual;

        factura.setFacturasPK(new FacturasPK(secuenciaSiguiente+1, nroFactura));
        
        factura.setFechaEmision(new Date());
        factura.setVigente(true);
        em.persist(factura);
        
        q = em.createNativeQuery("UPDATE recibos"
                + " SET facturado = true"
                + " WHERE cod_pago = " + factura.getCodPago().getCodPago() + ";");
        q.executeUpdate();
        
        q = em.createNativeQuery("UPDATE timbrados"
                    + " SET nro_sec_actual = nro_sec_actual + 1"
                    + " WHERE cedula = '" + factura.getCedulaEmisor().getCedula() + "'"
                    + " AND vigente = TRUE;");
            q.executeUpdate();    
        
    }

    public <S> void actualizarRecibosAFacturados (ArrayList<Integer> listaCodRecibos) throws SQLException, Exception {
        
//        Query q = em.createNativeQuery("UPDATE recibos"
//                + " SET facturado = true"
//                + " WHERE cod_recibo = (?1);");
        
        
        Query q = em.createNamedQuery("Facturas.findUltimaFactura");
        List<Facturas> ultimaFacturaAux = q.setMaxResults(1).getResultList();
        Facturas ultimaFactura = (Facturas) ultimaFacturaAux.get(0);
        
        q = em.createNativeQuery("UPDATE recibos"
                + " SET facturado = true"
                + ","
                + "     cod_factura = " + ultimaFactura.getFacturasPK().getCodFactura() + ","
                + "     nro_factura = '" + ultimaFactura.getFacturasPK().getNroFactura() + "'"
                + " WHERE cod_recibo = (?1);");
            
        for (Integer codRecibo : listaCodRecibos) {
            q.setParameter(1, codRecibo);
            q.executeUpdate();
        }
        
    }
    
    public <S> void edit(Class<S> clazz, S elem) throws SQLException, Exception {
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
            DetalleCuenta dc = (DetalleCuenta) elem;
            
            dc.setFecha(new Date());
            
            em.merge(dc);
        } else if (clazz == Facturas.class) {
            Facturas factura = (Facturas) elem;
            
            factura.setVigente(false);
            
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
