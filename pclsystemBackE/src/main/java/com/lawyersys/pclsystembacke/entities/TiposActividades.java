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
@Table(name = "tipos_actividades")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TiposActividades.findAll", query = "SELECT t FROM TiposActividades t")
    , @NamedQuery(name = "TiposActividades.findByCodTipoActividad", query = "SELECT t FROM TiposActividades t WHERE t.codTipoActividad = :codTipoActividad")
    , @NamedQuery(name = "TiposActividades.findByDescripcion", query = "SELECT t FROM TiposActividades t WHERE t.descripcion = :descripcion")})
public class TiposActividades implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cod_tipo_actividad")
    private Integer codTipoActividad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codTipoActividad")
    @JsonIgnore
    private List<Actividades> actividadesList;

    public TiposActividades() {
    }

    public TiposActividades(Integer codTipoActividad) {
        this.codTipoActividad = codTipoActividad;
    }

    public TiposActividades(Integer codTipoActividad, String descripcion) {
        this.codTipoActividad = codTipoActividad;
        this.descripcion = descripcion;
    }

    public Integer getCodTipoActividad() {
        return codTipoActividad;
    }

    public void setCodTipoActividad(Integer codTipoActividad) {
        this.codTipoActividad = codTipoActividad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
        hash += (codTipoActividad != null ? codTipoActividad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TiposActividades)) {
            return false;
        }
        TiposActividades other = (TiposActividades) object;
        if ((this.codTipoActividad == null && other.codTipoActividad != null) || (this.codTipoActividad != null && !this.codTipoActividad.equals(other.codTipoActividad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lawyersys.pclsystembacke.TiposActividades[ codTipoActividad=" + codTipoActividad + " ]";
    }
    
}
