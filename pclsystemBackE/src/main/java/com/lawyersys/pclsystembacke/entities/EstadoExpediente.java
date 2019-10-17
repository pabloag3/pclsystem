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
 * @author tatoa
 */
@Entity
@Table(name = "estado_expediente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadoExpediente.findAll", query = "SELECT e FROM EstadoExpediente e")
    , @NamedQuery(name = "EstadoExpediente.findByCodEstadoExpediente", query = "SELECT e FROM EstadoExpediente e WHERE e.codEstadoExpediente = :codEstadoExpediente")
    , @NamedQuery(name = "EstadoExpediente.findByDescripcion", query = "SELECT e FROM EstadoExpediente e WHERE e.descripcion = :descripcion")})
public class EstadoExpediente implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cod_estado_expediente")
    private Integer codEstadoExpediente;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "descripcion")
    private String descripcion;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codEstadoExpediente")
    @JsonIgnore
    private List<Expedientes> expedientesList;

    public EstadoExpediente() {
    }

    public EstadoExpediente(Integer codEstadoExpediente) {
        this.codEstadoExpediente = codEstadoExpediente;
    }

    public EstadoExpediente(Integer codEstadoExpediente, String descripcion) {
        this.codEstadoExpediente = codEstadoExpediente;
        this.descripcion = descripcion;
    }

    public Integer getCodEstadoExpediente() {
        return codEstadoExpediente;
    }

    public void setCodEstadoExpediente(Integer codEstadoExpediente) {
        this.codEstadoExpediente = codEstadoExpediente;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<Expedientes> getExpedientesList() {
        return expedientesList;
    }

    public void setExpedientesList(List<Expedientes> expedientesList) {
        this.expedientesList = expedientesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codEstadoExpediente != null ? codEstadoExpediente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoExpediente)) {
            return false;
        }
        EstadoExpediente other = (EstadoExpediente) object;
        if ((this.codEstadoExpediente == null && other.codEstadoExpediente != null) || (this.codEstadoExpediente != null && !this.codEstadoExpediente.equals(other.codEstadoExpediente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lawyersys.pclsystembacke.entities.EstadoExpediente[ codEstadoExpediente=" + codEstadoExpediente + " ]";
    }
    
}
