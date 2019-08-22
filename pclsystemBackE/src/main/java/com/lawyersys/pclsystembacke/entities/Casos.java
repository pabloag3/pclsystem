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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "casos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Casos.findAll", query = "SELECT c FROM Casos c")
    , @NamedQuery(name = "Casos.findByCodCaso", query = "SELECT c FROM Casos c WHERE c.codCaso = :codCaso")
    , @NamedQuery(name = "Casos.findByDescripcion", query = "SELECT c FROM Casos c WHERE c.descripcion = :descripcion")})
public class Casos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cod_caso")
    private Integer codCaso;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codCaso")
    private List<Expedientes> expedientesList;
    @JoinColumn(name = "cod_cliente", referencedColumnName = "cod_cliente")
    @ManyToOne(optional = false)
    private Clientes codCliente;
    @JoinColumn(name = "cod_estado_caso", referencedColumnName = "cod_estado_caso")
    @ManyToOne(optional = false)
    private EstadosCaso codEstadoCaso;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codCaso")
    private List<Cuentas> cuentasList;

    public Casos() {
    }

    public Casos(Integer codCaso) {
        this.codCaso = codCaso;
    }

    public Casos(Integer codCaso, String descripcion) {
        this.codCaso = codCaso;
        this.descripcion = descripcion;
    }

    public Integer getCodCaso() {
        return codCaso;
    }

    public void setCodCaso(Integer codCaso) {
        this.codCaso = codCaso;
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

    public Clientes getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(Clientes codCliente) {
        this.codCliente = codCliente;
    }

    public EstadosCaso getCodEstadoCaso() {
        return codEstadoCaso;
    }

    public void setCodEstadoCaso(EstadosCaso codEstadoCaso) {
        this.codEstadoCaso = codEstadoCaso;
    }

    @XmlTransient
    public List<Cuentas> getCuentasList() {
        return cuentasList;
    }

    public void setCuentasList(List<Cuentas> cuentasList) {
        this.cuentasList = cuentasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codCaso != null ? codCaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Casos)) {
            return false;
        }
        Casos other = (Casos) object;
        if ((this.codCaso == null && other.codCaso != null) || (this.codCaso != null && !this.codCaso.equals(other.codCaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lawyersys.pclsystembacke.Casos[ codCaso=" + codCaso + " ]";
    }
    
}
