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
public class DetalleExpedientePK implements Serializable {

    @Basic(optional = false)
    @Column(name = "cod_detalle_expediente")
    private int codDetalleExpediente;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "cod_expediente")
    private int codExpediente;

    public DetalleExpedientePK() {
    }

    public DetalleExpedientePK(int codDetalleExpediente, int codExpediente) {
        this.codDetalleExpediente = codDetalleExpediente;
        this.codExpediente = codExpediente;
    }

    public int getCodDetalleExpediente() {
        return codDetalleExpediente;
    }

    public void setCodDetalleExpediente(int codDetalleExpediente) {
        this.codDetalleExpediente = codDetalleExpediente;
    }

    public int getCodExpediente() {
        return codExpediente;
    }

    public void setCodExpediente(int codExpediente) {
        this.codExpediente = codExpediente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codDetalleExpediente;
        hash += (int) codExpediente;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleExpedientePK)) {
            return false;
        }
        DetalleExpedientePK other = (DetalleExpedientePK) object;
        if (this.codDetalleExpediente != other.codDetalleExpediente) {
            return false;
        }
        if (this.codExpediente != other.codExpediente) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lawyersys.pclsystembacke.DetalleExpedientePK[ codDetalleExpediente=" + codDetalleExpediente + ", codExpediente=" + codExpediente + " ]";
    }
    
}
