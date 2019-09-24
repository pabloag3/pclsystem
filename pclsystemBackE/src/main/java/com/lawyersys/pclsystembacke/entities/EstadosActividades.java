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
@Table(name = "estados_actividades")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadosActividades.findAll", query = "SELECT e FROM EstadosActividades e")
    , @NamedQuery(name = "EstadosActividades.findByCodEstado", query = "SELECT e FROM EstadosActividades e WHERE e.codEstado = :codEstado")
    , @NamedQuery(name = "EstadosActividades.findByTipoEstado", query = "SELECT e FROM EstadosActividades e WHERE e.tipoEstado = :tipoEstado")})
public class EstadosActividades implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cod_estado")
    private Integer codEstado;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "tipo_estado")
    private String tipoEstado;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codEstado")
    @JsonIgnore
    private List<Actividades> actividadesList;

    public EstadosActividades() {
    }

    public EstadosActividades(Integer codEstado) {
        this.codEstado = codEstado;
    }

    public EstadosActividades(Integer codEstado, String tipoEstado) {
        this.codEstado = codEstado;
        this.tipoEstado = tipoEstado;
    }

    public Integer getCodEstado() {
        return codEstado;
    }

    public void setCodEstado(Integer codEstado) {
        this.codEstado = codEstado;
    }

    public String getTipoEstado() {
        return tipoEstado;
    }

    public void setTipoEstado(String tipoEstado) {
        this.tipoEstado = tipoEstado;
    }

    @XmlTransient
    public List<Actividades> getActividadesList() {
        return actividadesList;
    }

    public void setActividadesList(List<Actividades> actividadesList) {
        this.actividadesList = actividadesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codEstado != null ? codEstado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadosActividades)) {
            return false;
        }
        EstadosActividades other = (EstadosActividades) object;
        if ((this.codEstado == null && other.codEstado != null) || (this.codEstado != null && !this.codEstado.equals(other.codEstado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lawyersys.pclsystembacke.entities.EstadosActividades[ codEstado=" + codEstado + " ]";
    }
    
}
