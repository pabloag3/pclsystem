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
public class PagosPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "cod_pago")
    private int codPago;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cod_cuenta")
    private int codCuenta;

    public PagosPK() {
    }

    public PagosPK(int codPago, int codCuenta) {
        this.codPago = codPago;
        this.codCuenta = codCuenta;
    }

    public int getCodPago() {
        return codPago;
    }

    public void setCodPago(int codPago) {
        this.codPago = codPago;
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
        hash += (int) codPago;
        hash += (int) codCuenta;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PagosPK)) {
            return false;
        }
        PagosPK other = (PagosPK) object;
        if (this.codPago != other.codPago) {
            return false;
        }
        if (this.codCuenta != other.codCuenta) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lawyersys.pclsystembacke.PagosPK[ codPago=" + codPago + ", codCuenta=" + codCuenta + " ]";
    }
    
}
