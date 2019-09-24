package com.lawyersys.pclsystembacke.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author carlo
 */
@Entity
@Table(name = "roles_permisos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RolesPermisos.findAll", query = "SELECT r FROM RolesPermisos r")
    , @NamedQuery(name = "RolesPermisos.findByCodRolPermiso", query = "SELECT r FROM RolesPermisos r WHERE r.codRolPermiso = :codRolPermiso")})
public class RolesPermisos implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cod_rol_permiso")
    private Integer codRolPermiso;
    
    @JoinColumn(name = "cod_permiso", referencedColumnName = "cod_permiso")
    @ManyToOne(optional = false)
    private Permisos codPermiso;
    
    @JoinColumn(name = "cod_rol", referencedColumnName = "cod_rol")
    @ManyToOne(optional = false)
    private RolesUsuario codRol;

    public RolesPermisos() {
    }

    public RolesPermisos(Integer codRolPermiso) {
        this.codRolPermiso = codRolPermiso;
    }

    public Integer getCodRolPermiso() {
        return codRolPermiso;
    }

    public void setCodRolPermiso(Integer codRolPermiso) {
        this.codRolPermiso = codRolPermiso;
    }

    public Permisos getCodPermiso() {
        return codPermiso;
    }

    public void setCodPermiso(Permisos codPermiso) {
        this.codPermiso = codPermiso;
    }

    public RolesUsuario getCodRol() {
        return codRol;
    }

    public void setCodRol(RolesUsuario codRol) {
        this.codRol = codRol;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codRolPermiso != null ? codRolPermiso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RolesPermisos)) {
            return false;
        }
        RolesPermisos other = (RolesPermisos) object;
        if ((this.codRolPermiso == null && other.codRolPermiso != null) || (this.codRolPermiso != null && !this.codRolPermiso.equals(other.codRolPermiso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lawyersys.pclsystembacke.entities.RolesPermisos[ codRolPermiso=" + codRolPermiso + " ]";
    }
    
}
