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
    @Size(min = 1, max = 2147483647)
    @Column(name = "nro_factura")
    private String nroFactura;

    public DetalleFacturaPK() {
    }

    public DetalleFacturaPK(int codDetalleFactura, int codFactura, String nroFactura) {
        this.codDetalleFactura = codDetalleFactura;
        this.codFactura = codFactura;
        this.nroFactura = nroFactura;
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

    public String getNroFactura() {
        return nroFactura;
    }

    public void setNroFactura(String nroFactura) {
        this.nroFactura = nroFactura;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codDetalleFactura;
        hash += (int) codFactura;
        hash += (nroFactura != null ? nroFactura.hashCode() : 0);
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
        if ((this.nroFactura == null && other.nroFactura != null) || (this.nroFactura != null && !this.nroFactura.equals(other.nroFactura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lawyersys.pclsystembacke.entities.DetalleFacturaPK[ codDetalleFactura=" + codDetalleFactura + ", codFactura=" + codFactura + ", nroFactura=" + nroFactura + " ]";
    }
    
}
