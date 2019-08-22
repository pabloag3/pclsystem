/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lawyersys.pclsystembacke.entities;

import java.io.Serializable;
import java.math.BigInteger;
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
@Table(name = "detalle_cuenta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleCuenta.findAll", query = "SELECT d FROM DetalleCuenta d")
    , @NamedQuery(name = "DetalleCuenta.findByCodDetalleCuenta", query = "SELECT d FROM DetalleCuenta d WHERE d.detalleCuentaPK.codDetalleCuenta = :codDetalleCuenta")
    , @NamedQuery(name = "DetalleCuenta.findByCodCuenta", query = "SELECT d FROM DetalleCuenta d WHERE d.detalleCuentaPK.codCuenta = :codCuenta")
    , @NamedQuery(name = "DetalleCuenta.findByDescripcion", query = "SELECT d FROM DetalleCuenta d WHERE d.descripcion = :descripcion")
    , @NamedQuery(name = "DetalleCuenta.findByMonto", query = "SELECT d FROM DetalleCuenta d WHERE d.monto = :monto")
    , @NamedQuery(name = "DetalleCuenta.findByEstado", query = "SELECT d FROM DetalleCuenta d WHERE d.estado = :estado")
    , @NamedQuery(name = "DetalleCuenta.findBySaldoDetalleCta", query = "SELECT d FROM DetalleCuenta d WHERE d.saldoDetalleCta = :saldoDetalleCta")})
public class DetalleCuenta implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DetalleCuentaPK detalleCuentaPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "monto")
    private int monto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private boolean estado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "saldo_detalle_cta")
    private BigInteger saldoDetalleCta;
    @ManyToOne(optional = false)
    @JoinColumns({
        @JoinColumn(name = "cod_cuenta", referencedColumnName = "cod_cuenta", insertable = false, updatable = false),
        @JoinColumn(name = "cod_cliente", referencedColumnName = "cod_cliente", insertable = false, updatable = false)
    })
    private Cuentas cuentas;

    public DetalleCuenta() {
    }

    public DetalleCuenta(DetalleCuentaPK detalleCuentaPK) {
        this.detalleCuentaPK = detalleCuentaPK;
    }

    public DetalleCuenta(DetalleCuentaPK detalleCuentaPK, String descripcion, int monto, boolean estado, BigInteger saldoDetalleCta) {
        this.detalleCuentaPK = detalleCuentaPK;
        this.descripcion = descripcion;
        this.monto = monto;
        this.estado = estado;
        this.saldoDetalleCta = saldoDetalleCta;
    }

    public DetalleCuenta(int codDetalleCuenta, int codCuenta) {
        this.detalleCuentaPK = new DetalleCuentaPK(codDetalleCuenta, codCuenta);
    }

    public DetalleCuentaPK getDetalleCuentaPK() {
        return detalleCuentaPK;
    }

    public void setDetalleCuentaPK(DetalleCuentaPK detalleCuentaPK) {
        this.detalleCuentaPK = detalleCuentaPK;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public BigInteger getSaldoDetalleCta() {
        return saldoDetalleCta;
    }

    public void setSaldoDetalleCta(BigInteger saldoDetalleCta) {
        this.saldoDetalleCta = saldoDetalleCta;
    }

    public Cuentas getCuentas() {
        return cuentas;
    }

    public void setCuentas(Cuentas cuentas) {
        this.cuentas = cuentas;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detalleCuentaPK != null ? detalleCuentaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleCuenta)) {
            return false;
        }
        DetalleCuenta other = (DetalleCuenta) object;
        if ((this.detalleCuentaPK == null && other.detalleCuentaPK != null) || (this.detalleCuentaPK != null && !this.detalleCuentaPK.equals(other.detalleCuentaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lawyersys.pclsystembacke.DetalleCuenta[ detalleCuentaPK=" + detalleCuentaPK + " ]";
    }
    
}