package com.lawyersys.pclsystembacke.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author carlo
 */
@Entity
@Table(name = "detalle_factura")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleFactura.findAll", query = "SELECT d FROM DetalleFactura d")
    , @NamedQuery(name = "DetalleFactura.findByCodDetalleFactura", query = "SELECT d FROM DetalleFactura d WHERE d.detalleFacturaPK.codDetalleFactura = :codDetalleFactura")
    , @NamedQuery(name = "DetalleFactura.findByCodFactura", query = "SELECT d FROM DetalleFactura d WHERE d.detalleFacturaPK.codFactura = :codFactura")
    , @NamedQuery(name = "DetalleFactura.findByPorcentajeIva", query = "SELECT d FROM DetalleFactura d WHERE d.porcentajeIva = :porcentajeIva")
    , @NamedQuery(name = "DetalleFactura.findByDescripcion", query = "SELECT d FROM DetalleFactura d WHERE d.descripcion = :descripcion")
    , @NamedQuery(name = "DetalleFactura.findByMontoIva", query = "SELECT d FROM DetalleFactura d WHERE d.montoIva = :montoIva")
    , @NamedQuery(name = "DetalleFactura.findByMonto", query = "SELECT d FROM DetalleFactura d WHERE d.monto = :monto")})
public class DetalleFactura implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    protected DetalleFacturaPK detalleFacturaPK;
    
    @JoinColumn(name = "cod_pago", referencedColumnName = "cod_pago")
    @ManyToOne(optional = false)
    private Pagos codPago;

    @Basic(optional = false)
    @NotNull
    @Column(name = "porcentaje_iva")
    private int porcentajeIva;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "descripcion")
    private String descripcion;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "monto_iva")
    private int montoIva;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "monto")
    private int monto;
    
    @ManyToOne(optional = false)
    @JoinColumns({
        @JoinColumn(name = "cod_factura", referencedColumnName = "cod_factura", insertable = false, updatable = false),
        @JoinColumn(name = "cod_cuenta", referencedColumnName = "cod_cuenta", insertable = false, updatable = false)
    })
    private Facturas facturas;

    public DetalleFactura() {
    }

    public DetalleFactura(DetalleFacturaPK detalleFacturaPK) {
        this.detalleFacturaPK = detalleFacturaPK;
    }

    public DetalleFactura(DetalleFacturaPK detalleFacturaPK, int porcentajeIva, String descripcion, int montoIva, int monto) {
        this.detalleFacturaPK = detalleFacturaPK;
        this.porcentajeIva = porcentajeIva;
        this.descripcion = descripcion;
        this.montoIva = montoIva;
        this.monto = monto;
    }

    public DetalleFactura(int codDetalleFactura, int codFactura) {
        this.detalleFacturaPK = new DetalleFacturaPK(codDetalleFactura, codFactura);
    }

    public DetalleFacturaPK getDetalleFacturaPK() {
        return detalleFacturaPK;
    }

    public void setDetalleFacturaPK(DetalleFacturaPK detalleFacturaPK) {
        this.detalleFacturaPK = detalleFacturaPK;
    }

    public int getPorcentajeIva() {
        return porcentajeIva;
    }

    public void setPorcentajeIva(int porcentajeIva) {
        this.porcentajeIva = porcentajeIva;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getMontoIva() {
        return montoIva;
    }

    public void setMontoIva(int montoIva) {
        this.montoIva = montoIva;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public Facturas getFacturas() {
        return facturas;
    }

    public void setFacturas(Facturas facturas) {
        this.facturas = facturas;
    }
    
    public Pagos getCodPago() {
        return codPago;
    }

    public void setCodPago(Pagos codPago) {
        this.codPago = codPago;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detalleFacturaPK != null ? detalleFacturaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleFactura)) {
            return false;
        }
        DetalleFactura other = (DetalleFactura) object;
        if ((this.detalleFacturaPK == null && other.detalleFacturaPK != null) || (this.detalleFacturaPK != null && !this.detalleFacturaPK.equals(other.detalleFacturaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lawyersys.pclsystembacke.DetalleFactura[ detalleFacturaPK=" + detalleFacturaPK + " ]";
    }
    
}
