/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author carlo
 */
@Entity
@Table(name = "facturas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Facturas.findAll", query = "SELECT f FROM Facturas f")
    , @NamedQuery(name = "Facturas.findByCodFactura", query = "SELECT f FROM Facturas f WHERE f.facturasPK.codFactura = :codFactura")
    , @NamedQuery(name = "Facturas.findByCodCuenta", query = "SELECT f FROM Facturas f WHERE f.facturasPK.codCuenta = :codCuenta")
    , @NamedQuery(name = "Facturas.findByFechaEmision", query = "SELECT f FROM Facturas f WHERE f.fechaEmision = :fechaEmision")
    , @NamedQuery(name = "Facturas.findByMontoTotal", query = "SELECT f FROM Facturas f WHERE f.montoTotal = :montoTotal")})
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
    @ManyToOne(optional = false)
    @JoinColumns({
        @JoinColumn(name = "cod_cuenta", referencedColumnName = "cod_cuenta", insertable = false, updatable = false),
        @JoinColumn(name = "cod_cliente", referencedColumnName = "cod_cliente", insertable = false, updatable = false)
    })
    private Cuentas cuentas;
    @JoinColumn(name = "cedula_emisor", referencedColumnName = "cedula")
    @ManyToOne(optional = false)
    private Empleados cedulaEmisor;
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

    public Facturas(int codFactura, int codCuenta) {
        this.facturasPK = new FacturasPK(codFactura, codCuenta);
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

    public Cuentas getCuentas() {
        return cuentas;
    }

    public void setCuentas(Cuentas cuentas) {
        this.cuentas = cuentas;
    }

    public Empleados getCedulaEmisor() {
        return cedulaEmisor;
    }

    public void setCedulaEmisor(Empleados cedulaEmisor) {
        this.cedulaEmisor = cedulaEmisor;
    }

    @XmlTransient
    public List<DetalleFactura> getDetalleFacturaList() {
        return detalleFacturaList;
    }

    public void setDetalleFacturaList(List<DetalleFactura> detalleFacturaList) {
        this.detalleFacturaList = detalleFacturaList;
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
        return "com.lawyersys.pclsystembacke.Facturas[ facturasPK=" + facturasPK + " ]";
    }
    
}