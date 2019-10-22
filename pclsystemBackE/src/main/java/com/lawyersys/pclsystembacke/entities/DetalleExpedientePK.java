/*
 */
package com.lawyersys.pclsystembacke.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Pablo Aguilar
 */
@Embeddable
public class DetalleExpedientePK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "cod_expediente")
    private int codExpediente;
    
    @Basic(optional = false)
    @Column(name = "cod_detalle_expediente")
    private int codDetalleExpediente;

    public DetalleExpedientePK() {
    }

    public DetalleExpedientePK(int codExpediente, int codDetalleExpediente) {
        this.codExpediente = codExpediente;
        this.codDetalleExpediente = codDetalleExpediente;
    }

    public int getCodExpediente() {
        return codExpediente;
    }

    public void setCodExpediente(int codExpediente) {
        this.codExpediente = codExpediente;
    }

    public int getCodDetalleExpediente() {
        return codDetalleExpediente;
    }

    public void setCodDetalleExpediente(int codDetalleExpediente) {
        this.codDetalleExpediente = codDetalleExpediente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codExpediente;
        hash += (int) codDetalleExpediente;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleExpedientePK)) {
            return false;
        }
        DetalleExpedientePK other = (DetalleExpedientePK) object;
        if (this.codExpediente != other.codExpediente) {
            return false;
        }
        if (this.codDetalleExpediente != other.codDetalleExpediente) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lawyersys.pclsystembacke.entities.DetalleExpedientePK[ codExpediente=" + codExpediente + ", codDetalleExpediente=" + codDetalleExpediente + " ]";
    }
    
}
