
package com.lawyersys.pclsystembacke.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author carlo
 */
@Entity
@Table(name = "tipos_pagos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TiposPagos.findAll", query = "SELECT t FROM TiposPagos t")
    , @NamedQuery(name = "TiposPagos.findByCodTipoPago", query = "SELECT t FROM TiposPagos t WHERE t.codTipoPago = :codTipoPago")
    , @NamedQuery(name = "TiposPagos.findByDescripcion", query = "SELECT t FROM TiposPagos t WHERE t.descripcion = :descripcion")})
public class TiposPagos implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cod_tipo_pago")
    private Integer codTipoPago;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "descripcion")
    private String descripcion;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codTipoPago")
    @JsonIgnore
    private List<Pagos> pagosList;

    public TiposPagos() {
    }

    public TiposPagos(Integer codTipoPago) {
        this.codTipoPago = codTipoPago;
    }

    public TiposPagos(Integer codTipoPago, String descripcion) {
        this.codTipoPago = codTipoPago;
        this.descripcion = descripcion;
    }

    public Integer getCodTipoPago() {
        return codTipoPago;
    }

    public void setCodTipoPago(Integer codTipoPago) {
        this.codTipoPago = codTipoPago;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<Pagos> getPagosList() {
        return pagosList;
    }

    public void setPagosList(List<Pagos> pagosList) {
        this.pagosList = pagosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codTipoPago != null ? codTipoPago.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TiposPagos)) {
            return false;
        }
        TiposPagos other = (TiposPagos) object;
        if ((this.codTipoPago == null && other.codTipoPago != null) || (this.codTipoPago != null && !this.codTipoPago.equals(other.codTipoPago))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lawyersys.pclsystembacke.entities.TiposPagos[ codTipoPago=" + codTipoPago + " ]";
    }
    
}
