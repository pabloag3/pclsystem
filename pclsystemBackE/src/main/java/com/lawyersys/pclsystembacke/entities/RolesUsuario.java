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
@Table(name = "roles_usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RolesUsuario.findAll", query = "SELECT r FROM RolesUsuario r")
    , @NamedQuery(name = "RolesUsuario.findByCodRol", query = "SELECT r FROM RolesUsuario r WHERE r.codRol = :codRol")
    , @NamedQuery(name = "RolesUsuario.findByDescripcion", query = "SELECT r FROM RolesUsuario r WHERE r.descripcion = :descripcion")})
public class RolesUsuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cod_rol")
    private Integer codRol;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codRol")
    @JsonIgnore
    private List<Usuarios> usuariosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codRol")
    @JsonIgnore
    private List<RolesPermisos> rolesPermisosList;

    public RolesUsuario() {
    }

    public RolesUsuario(Integer codRol) {
        this.codRol = codRol;
    }

    public RolesUsuario(Integer codRol, String descripcion) {
        this.codRol = codRol;
        this.descripcion = descripcion;
    }

    public Integer getCodRol() {
        return codRol;
    }

    public void setCodRol(Integer codRol) {
        this.codRol = codRol;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<Usuarios> getUsuariosList() {
        return usuariosList;
    }

    public void setUsuariosList(List<Usuarios> usuariosList) {
        this.usuariosList = usuariosList;
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
        hash += (codRol != null ? codRol.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RolesUsuario)) {
            return false;
        }
        RolesUsuario other = (RolesUsuario) object;
        if ((this.codRol == null && other.codRol != null) || (this.codRol != null && !this.codRol.equals(other.codRol))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lawyersys.pclsystembacke.RolesUsuario[ codRol=" + codRol + " ]";
    }
    
}
