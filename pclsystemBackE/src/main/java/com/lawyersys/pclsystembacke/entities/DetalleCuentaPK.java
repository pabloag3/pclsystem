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
public class DetalleCuentaPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "cod_detalle_cuenta")
    private int codDetalleCuenta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cod_cuenta")
    private int codCuenta;

    public DetalleCuentaPK() {
    }

    public DetalleCuentaPK(int codDetalleCuenta, int codCuenta) {
        this.codDetalleCuenta = codDetalleCuenta;
        this.codCuenta = codCuenta;
    }

    public int getCodDetalleCuenta() {
        return codDetalleCuenta;
    }

    public void setCodDetalleCuenta(int codDetalleCuenta) {
        this.codDetalleCuenta = codDetalleCuenta;
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
        hash += (int) codDetalleCuenta;
        hash += (int) codCuenta;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleCuentaPK)) {
            return false;
        }
        DetalleCuentaPK other = (DetalleCuentaPK) object;
        if (this.codDetalleCuenta != other.codDetalleCuenta) {
            return false;
        }
        if (this.codCuenta != other.codCuenta) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lawyersys.pclsystembacke.DetalleCuentaPK[ codDetalleCuenta=" + codDetalleCuenta + ", codCuenta=" + codCuenta + " ]";
    }
    
}
