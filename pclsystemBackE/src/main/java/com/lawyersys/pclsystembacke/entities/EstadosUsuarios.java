/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
 * @author carlo
 */
@Entity
@Table(name = "estados_usuarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadosUsuarios.findAll", query = "SELECT e FROM EstadosUsuarios e")
    , @NamedQuery(name = "EstadosUsuarios.findByCodEstado", query = "SELECT e FROM EstadosUsuarios e WHERE e.codEstado = :codEstado")
    , @NamedQuery(name = "EstadosUsuarios.findByDescripcion", query = "SELECT e FROM EstadosUsuarios e WHERE e.descripcion = :descripcion")})
public class EstadosUsuarios implements Serializable {

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
    private List<Usuarios> usuariosList;

    public EstadosUsuarios() {
    }

    public EstadosUsuarios(Integer codEstado) {
        this.codEstado = codEstado;
    }

    public EstadosUsuarios(Integer codEstado, String descripcion) {
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
    public List<Usuarios> getUsuariosList() {
        return usuariosList;
    }

    public void setUsuariosList(List<Usuarios> usuariosList) {
        this.usuariosList = usuariosList;
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
        if (!(object instanceof EstadosUsuarios)) {
            return false;
        }
        EstadosUsuarios other = (EstadosUsuarios) object;
        if ((this.codEstado == null && other.codEstado != null) || (this.codEstado != null && !this.codEstado.equals(other.codEstado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lawyersys.pclsystembacke.EstadosUsuarios[ codEstado=" + codEstado + " ]";
    }
    
}
