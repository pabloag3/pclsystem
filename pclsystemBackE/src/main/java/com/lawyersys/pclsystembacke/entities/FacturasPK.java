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
public class FacturasPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "cod_factura")
    private int codFactura;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "cod_cuenta")
    private int codCuenta;

    public FacturasPK() {
    }

    public FacturasPK(int codFactura, int codCuenta) {
        this.codFactura = codFactura;
        this.codCuenta = codCuenta;
    }

    public int getCodFactura() {
        return codFactura;
    }

    public void setCodFactura(int codFactura) {
        this.codFactura = codFactura;
    }

    public int getCodCuenta() {
        return codCuenta;
    }

    public void setCodCuenta(int codCuenta) {
        this.codCuenta = codCuenta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codFactura;
        hash += (int) codCuenta;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FacturasPK)) {
            return false;
        }
        FacturasPK other = (FacturasPK) object;
        if (this.codFactura != other.codFactura) {
            return false;
        }
        if (this.codCuenta != other.codCuenta) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lawyersys.pclsystembacke.FacturasPK[ codFactura=" + codFactura + ", codCuenta=" + codCuenta + " ]";
    }
    
}
