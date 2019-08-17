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
@Table(name = "estados_caso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadosCaso.findAll", query = "SELECT e FROM EstadosCaso e")
    , @NamedQuery(name = "EstadosCaso.findByCodEstadoCaso", query = "SELECT e FROM EstadosCaso e WHERE e.codEstadoCaso = :codEstadoCaso")
    , @NamedQuery(name = "EstadosCaso.findByDescripcion", query = "SELECT e FROM EstadosCaso e WHERE e.descripcion = :descripcion")})
public class EstadosCaso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cod_estado_caso")
    private Integer codEstadoCaso;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codEstadoCaso")
    @JsonIgnore
    private List<Casos> casosList;

    public EstadosCaso() {
    }

    public EstadosCaso(Integer codEstadoCaso) {
        this.codEstadoCaso = codEstadoCaso;
    }

    public EstadosCaso(Integer codEstadoCaso, String descripcion) {
        this.codEstadoCaso = codEstadoCaso;
        this.descripcion = descripcion;
    }

    public Integer getCodEstadoCaso() {
        return codEstadoCaso;
    }

    public void setCodEstadoCaso(Integer codEstadoCaso) {
        this.codEstadoCaso = codEstadoCaso;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<Casos> getCasosList() {
        return casosList;
    }

    public void setCasosList(List<Casos> casosList) {
        this.casosList = casosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codEstadoCaso != null ? codEstadoCaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadosCaso)) {
            return false;
        }
        EstadosCaso other = (EstadosCaso) object;
        if ((this.codEstadoCaso == null && other.codEstadoCaso != null) || (this.codEstadoCaso != null && !this.codEstadoCaso.equals(other.codEstadoCaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lawyersys.pclsystembacke.EstadosCaso[ codEstadoCaso=" + codEstadoCaso + " ]";
    }
    
}
