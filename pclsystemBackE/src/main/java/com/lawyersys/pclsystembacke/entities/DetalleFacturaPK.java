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
    @Column(name = "cod_detalle_factura")
    private int codDetalleFactura;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cod_factura")
    private int codFactura;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cod_pago")
    private int codPago;

    public DetalleFacturaPK() {
    }

    public DetalleFacturaPK(int codDetalleFactura, int codFactura, int codPago) {
        this.codDetalleFactura = codDetalleFactura;
        this.codFactura = codFactura;
        this.codPago = codPago;
    }

    public int getCodDetalleFactura() {
        return codDetalleFactura;
    }

    public void setCodDetalleFactura(int codDetalleFactura) {
        this.codDetalleFactura = codDetalleFactura;
    }

    public int getCodFactura() {
        return codFactura;
    }

    public void setCodFactura(int codFactura) {
        this.codFactura = codFactura;
    }

    public int getCodPago() {
        return codPago;
    }

    public void setCodPago(int codPago) {
        this.codPago = codPago;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codDetalleFactura;
        hash += (int) codFactura;
        hash += (int) codPago;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleFacturaPK)) {
            return false;
        }
        DetalleFacturaPK other = (DetalleFacturaPK) object;
        if (this.codDetalleFactura != other.codDetalleFactura) {
            return false;
        }
        if (this.codFactura != other.codFactura) {
            return false;
        }
        if (this.codPago != other.codPago) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lawyersys.pclsystembacke.DetalleFacturaPK[ codDetalleFactura=" + codDetalleFactura + ", codFactura=" + codFactura + ", codPago=" + codPago + " ]";
    }
    
}
