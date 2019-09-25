/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lawyersys.pclsystembacke.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author carlo
 */
@Embeddable
public class DetalleFacturaPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "cod_factura")
    private int codFactura;
    @Basic(optional = false)
    @Column(name = "cod_detalle_factura")
    private int codDetalleFactura;

    public DetalleFacturaPK() {
    }

    public DetalleFacturaPK(int codFactura, int codDetalleFactura) {
        this.codFactura = codFactura;
        this.codDetalleFactura = codDetalleFactura;
    }

    public int getCodFactura() {
        return codFactura;
    }

    public void setCodFactura(int codFactura) {
        this.codFactura = codFactura;
    }

    public int getCodDetalleFactura() {
        return codDetalleFactura;
    }

    public void setCodDetalleFactura(int codDetalleFactura) {
        this.codDetalleFactura = codDetalleFactura;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codFactura;
        hash += (int) codDetalleFactura;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleFacturaPK)) {
            return false;
        }
        DetalleFacturaPK other = (DetalleFacturaPK) object;
        if (this.codFactura != other.codFactura) {
            return false;
        }
        if (this.codDetalleFactura != other.codDetalleFactura) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lawyersys.pclsystembacke.entities.DetalleFacturaPK[ codFactura=" + codFactura + ", codDetalleFactura=" + codDetalleFactura + " ]";
    }
    
}
