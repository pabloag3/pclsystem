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
public class RecibosPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "cod_recibo")
    private int codRecibo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cod_pago")
    private int codPago;

    public RecibosPK() {
    }

    public RecibosPK(int codRecibo, int codPago) {
        this.codRecibo = codRecibo;
        this.codPago = codPago;
    }

    public int getCodRecibo() {
        return codRecibo;
    }

    public void setCodRecibo(int codRecibo) {
        this.codRecibo = codRecibo;
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
        hash += (int) codRecibo;
        hash += (int) codPago;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RecibosPK)) {
            return false;
        }
        RecibosPK other = (RecibosPK) object;
        if (this.codRecibo != other.codRecibo) {
            return false;
        }
        if (this.codPago != other.codPago) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lawyersys.pclsystembacke.RecibosPK[ codRecibo=" + codRecibo + ", codPago=" + codPago + " ]";
    }
    
}
