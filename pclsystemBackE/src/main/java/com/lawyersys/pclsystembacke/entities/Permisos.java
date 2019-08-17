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
@Table(name = "permisos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Permisos.findAll", query = "SELECT p FROM Permisos p")
    , @NamedQuery(name = "Permisos.findByCodPermiso", query = "SELECT p FROM Permisos p WHERE p.codPermiso = :codPermiso")
    , @NamedQuery(name = "Permisos.findByDescripcion", query = "SELECT p FROM Permisos p WHERE p.descripcion = :descripcion")})
public class Permisos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cod_permiso")
    private Integer codPermiso;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codPermiso")
    @JsonIgnore
    private List<RolesPermisos> rolesPermisosList;

    public Permisos() {
    }

    public Permisos(Integer codPermiso) {
        this.codPermiso = codPermiso;
    }

    public Permisos(Integer codPermiso, String descripcion) {
        this.codPermiso = codPermiso;
        this.descripcion = descripcion;
    }

    public Integer getCodPermiso() {
        return codPermiso;
    }

    public void setCodPermiso(Integer codPermiso) {
        this.codPermiso = codPermiso;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<RolesPermisos> getRolesPermisosList() {
        return rolesPermisosList;
    }

    public void setRolesPermisosList(List<RolesPermisos> rolesPermisosList) {
        this.rolesPermisosList = rolesPermisosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codPermiso != null ? codPermiso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Permisos)) {
            return false;
        }
        Permisos other = (Permisos) object;
        if ((this.codPermiso == null && other.codPermiso != null) || (this.codPermiso != null && !this.codPermiso.equals(other.codPermiso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lawyersys.pclsystembacke.Permisos[ codPermiso=" + codPermiso + " ]";
    }
    
}
