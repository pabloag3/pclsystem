/*
 */
package com.lawyersys.pclsystembacke.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Pablo Aguilar
 */
@Embeddable
public class FacturasPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "cod_factura")
    private int codFactura;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "nro_factura")
    private String nroFactura;

    public FacturasPK() {
    }

    public FacturasPK(int codFactura, String nroFactura) {
        this.codFactura = codFactura;
        this.nroFactura = nroFactura;
    }

    public int getCodFactura() {
        return codFactura;
    }

    public void setCodFactura(int codFactura) {
        this.codFactura = codFactura;
    }

    public String getNroFactura() {
        return nroFactura;
    }

    public void setNroFactura(String nroFactura) {
        this.nroFactura = nroFactura;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codFactura;
        hash += (nroFactura != null ? nroFactura.hashCode() : 0);
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
        if ((this.nroFactura == null && other.nroFactura != null) || (this.nroFactura != null && !this.nroFactura.equals(other.nroFactura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lawyersys.pclsystembacke.entities.FacturasPK[ codFactura=" + codFactura + ", nroFactura=" + nroFactura + " ]";
    }
    
}
