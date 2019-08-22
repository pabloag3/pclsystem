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
public class CuentasPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "cod_cuenta")
    private int codCuenta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cod_cliente")
    private int codCliente;

    public CuentasPK() {
    }

    public CuentasPK(int codCuenta, int codCliente) {
        this.codCuenta = codCuenta;
        this.codCliente = codCliente;
    }

    public int getCodCuenta() {
        return codCuenta;
    }

    public void setCodCuenta(int codCuenta) {
        this.codCuenta = codCuenta;
    }

    public int getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(int codCliente) {
        this.codCliente = codCliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codCuenta;
        hash += (int) codCliente;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CuentasPK)) {
            return false;
        }
        CuentasPK other = (CuentasPK) object;
        if (this.codCuenta != other.codCuenta) {
            return false;
        }
        if (this.codCliente != other.codCliente) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lawyersys.pclsystembacke.CuentasPK[ codCuenta=" + codCuenta + ", codCliente=" + codCliente + " ]";
    }
    
}
