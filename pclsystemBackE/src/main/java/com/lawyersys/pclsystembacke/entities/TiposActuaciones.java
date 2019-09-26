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
@Table(name = "tipos_actuaciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TiposActuaciones.findAll", query = "SELECT t FROM TiposActuaciones t")
    , @NamedQuery(name = "TiposActuaciones.findByCodTipoActuacion", query = "SELECT t FROM TiposActuaciones t WHERE t.codTipoActuacion = :codTipoActuacion")
    , @NamedQuery(name = "TiposActuaciones.findByDescripcion", query = "SELECT t FROM TiposActuaciones t WHERE t.descripcion = :descripcion")})
public class TiposActuaciones implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cod_tipo_actuacion")
    private Integer codTipoActuacion;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "descripcion")
    private String descripcion;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codTipoActuacion")
    @JsonIgnore
    private List<DetalleExpediente> detalleExpedienteList;

    public TiposActuaciones() {
    }

    public TiposActuaciones(Integer codTipoActuacion) {
        this.codTipoActuacion = codTipoActuacion;
    }

    public TiposActuaciones(Integer codTipoActuacion, String descripcion) {
        this.codTipoActuacion = codTipoActuacion;
        this.descripcion = descripcion;
    }

    public Integer getCodTipoActuacion() {
        return codTipoActuacion;
    }

    public void setCodTipoActuacion(Integer codTipoActuacion) {
        this.codTipoActuacion = codTipoActuacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<DetalleExpediente> getDetalleExpedienteList() {
        return detalleExpedienteList;
    }

    public void setDetalleExpedienteList(List<DetalleExpediente> detalleExpedienteList) {
        this.detalleExpedienteList = detalleExpedienteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codTipoActuacion != null ? codTipoActuacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TiposActuaciones)) {
            return false;
        }
        TiposActuaciones other = (TiposActuaciones) object;
        if ((this.codTipoActuacion == null && other.codTipoActuacion != null) || (this.codTipoActuacion != null && !this.codTipoActuacion.equals(other.codTipoActuacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lawyersys.pclsystembacke.TiposActuaciones[ codTipoActuacion=" + codTipoActuacion + " ]";
    }
    
}
