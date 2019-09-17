/*
 */
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
@Table(name = "estados_empleados")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadosEmpleados.findAll", query = "SELECT e FROM EstadosEmpleados e")
    , @NamedQuery(name = "EstadosEmpleados.findByCodEstado", query = "SELECT e FROM EstadosEmpleados e WHERE e.codEstado = :codEstado")
    , @NamedQuery(name = "EstadosEmpleados.findByDescripcion", query = "SELECT e FROM EstadosEmpleados e WHERE e.descripcion = :descripcion")})
public class EstadosEmpleados implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cod_estado")
    private Integer codEstado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codEstado")
    @JsonIgnore
    private List<Empleados> empleadosList;

    public EstadosEmpleados() {
    }

    public EstadosEmpleados(Integer codEstado) {
        this.codEstado = codEstado;
    }

    public EstadosEmpleados(Integer codEstado, String descripcion) {
        this.codEstado = codEstado;
        this.descripcion = descripcion;
    }

    public Integer getCodEstado() {
        return codEstado;
    }

    public void setCodEstado(Integer codEstado) {
        this.codEstado = codEstado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<Empleados> getEmpleadosList() {
        return empleadosList;
    }

    public void setEmpleadosList(List<Empleados> empleadosList) {
        this.empleadosList = empleadosList;
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
        if (!(object instanceof EstadosEmpleados)) {
            return false;
        }
        EstadosEmpleados other = (EstadosEmpleados) object;
        if ((this.codEstado == null && other.codEstado != null) || (this.codEstado != null && !this.codEstado.equals(other.codEstado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lawyersys.pclsystembacke.entities.EstadosEmpleados[ codEstado=" + codEstado + " ]";
    }
    
}
