/*
 */
package com.lawyersys.pclsystembacke.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Pablo Aguilar
 */
@Entity
@Table(name = "facturas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Facturas.findAll", query = "SELECT f FROM Facturas f")
    , @NamedQuery(name = "Facturas.findByCodFactura", query = "SELECT f FROM Facturas f WHERE f.facturasPK.codFactura = :codFactura")
    , @NamedQuery(name = "Facturas.findByNroFactura", query = "SELECT f FROM Facturas f WHERE f.facturasPK.nroFactura = :nroFactura")
    , @NamedQuery(name = "Facturas.findByFechaEmision", query = "SELECT f FROM Facturas f WHERE f.fechaEmision = :fechaEmision")
    , @NamedQuery(name = "Facturas.findByMontoTotal", query = "SELECT f FROM Facturas f WHERE f.montoTotal = :montoTotal")
    , @NamedQuery(name = "Facturas.findByArchivo", query = "SELECT f FROM Facturas f WHERE f.archivo = :archivo")
    , @NamedQuery(name = "Facturas.findUltimaFactura", query = "SELECT f FROM Facturas f ORDER BY f.facturasPK.codFactura DESC")
})
public class Facturas implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    protected FacturasPK facturasPK;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_emision")
    @Temporal(TemporalType.DATE)
    private Date fechaEmision;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "monto_total")
    private int montoTotal;
    
    @Size(max = 2147483647)
    @Column(name = "archivo")
    private String archivo;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "vigente")
    private boolean vigente;
    
    @JoinColumn(name = "cod_cliente", referencedColumnName = "cod_cliente")
    @ManyToOne(optional = false)
    private Clientes codCliente;
    
    @JoinColumn(name = "cedula_emisor", referencedColumnName = "cedula")
    @ManyToOne(optional = false)
    private Empleados cedulaEmisor;
    
    @JoinColumn(name = "cod_pago", referencedColumnName = "cod_pago")
    @ManyToOne(optional = false)
    private Pagos codPago;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "facturas")
    @JsonIgnore
    private List<DetalleFactura> detalleFacturaList;

    public Facturas() {
    }

    public Facturas(FacturasPK facturasPK) {
        this.facturasPK = facturasPK;
    }

    public Facturas(FacturasPK facturasPK, Date fechaEmision, int montoTotal) {
        this.facturasPK = facturasPK;
        this.fechaEmision = fechaEmision;
        this.montoTotal = montoTotal;
    }

    public Facturas(int codFactura, String nroFactura) {
        this.facturasPK = new FacturasPK(codFactura, nroFactura);
    }

    public FacturasPK getFacturasPK() {
        return facturasPK;
    }

    public void setFacturasPK(FacturasPK facturasPK) {
        this.facturasPK = facturasPK;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public int getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(int montoTotal) {
        this.montoTotal = montoTotal;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    @XmlTransient
    public List<DetalleFactura> getDetalleFacturaList() {
        return detalleFacturaList;
    }

    public void setDetalleFacturaList(List<DetalleFactura> detalleFacturaList) {
        this.detalleFacturaList = detalleFacturaList;
    }

    public Clientes getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(Clientes codCliente) {
        this.codCliente = codCliente;
    }

    public Empleados getCedulaEmisor() {
        return cedulaEmisor;
    }

    public void setCedulaEmisor(Empleados cedulaEmisor) {
        this.cedulaEmisor = cedulaEmisor;
    }

    public Pagos getCodPago() {
        return codPago;
    }

    public void setCodPago(Pagos codPago) {
        this.codPago = codPago;
    }
    
    public boolean getVigente() {
        return vigente;
    }

    public void setVigente(boolean vigente) {
        this.vigente = vigente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (facturasPK != null ? facturasPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Facturas)) {
            return false;
        }
        Facturas other = (Facturas) object;
        if ((this.facturasPK == null && other.facturasPK != null) || (this.facturasPK != null && !this.facturasPK.equals(other.facturasPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lawyersys.pclsystembacke.entities.Facturas[ facturasPK=" + facturasPK + " ]";
    }
    
}
