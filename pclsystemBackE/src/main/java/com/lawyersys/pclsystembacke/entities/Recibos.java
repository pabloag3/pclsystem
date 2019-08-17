/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
@Table(name = "recibos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Recibos.findAll", query = "SELECT r FROM Recibos r")
    , @NamedQuery(name = "Recibos.findByCodRecibo", query = "SELECT r FROM Recibos r WHERE r.recibosPK.codRecibo = :codRecibo")
    , @NamedQuery(name = "Recibos.findByCodPago", query = "SELECT r FROM Recibos r WHERE r.recibosPK.codPago = :codPago")
    , @NamedQuery(name = "Recibos.findByDescripcion", query = "SELECT r FROM Recibos r WHERE r.descripcion = :descripcion")
    , @NamedQuery(name = "Recibos.findByMonto", query = "SELECT r FROM Recibos r WHERE r.monto = :monto")})
public class Recibos implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RecibosPK recibosPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "monto")
    private int monto;
    @ManyToOne(optional = false)
    @JoinColumns({
           @JoinColumn(name = "cod_pago", referencedColumnName = "cod_pago", insertable = false, updatable = false),
           @JoinColumn(name = "cod_cuenta", referencedColumnName = "cod_cuenta", insertable = false, updatable = false)
    })
    private Pagos pagos;

    public Recibos() {
    }

    public Recibos(RecibosPK recibosPK) {
        this.recibosPK = recibosPK;
    }

    public Recibos(RecibosPK recibosPK, String descripcion, int monto) {
        this.recibosPK = recibosPK;
        this.descripcion = descripcion;
        this.monto = monto;
    }

    public Recibos(int codRecibo, int codPago) {
        this.recibosPK = new RecibosPK(codRecibo, codPago);
    }

    public RecibosPK getRecibosPK() {
        return recibosPK;
    }

    public void setRecibosPK(RecibosPK recibosPK) {
        this.recibosPK = recibosPK;
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

    public Pagos getPagos() {
        return pagos;
    }

    public void setPagos(Pagos pagos) {
        this.pagos = pagos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (recibosPK != null ? recibosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Recibos)) {
            return false;
        }
        Recibos other = (Recibos) object;
        if ((this.recibosPK == null && other.recibosPK != null) || (this.recibosPK != null && !this.recibosPK.equals(other.recibosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lawyersys.pclsystembacke.Recibos[ recibosPK=" + recibosPK + " ]";
    }
    
}
