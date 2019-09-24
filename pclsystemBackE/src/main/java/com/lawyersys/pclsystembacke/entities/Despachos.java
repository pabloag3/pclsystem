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
@Table(name = "despachos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Despachos.findAll", query = "SELECT d FROM Despachos d")
    , @NamedQuery(name = "Despachos.findByCodDespacho", query = "SELECT d FROM Despachos d WHERE d.codDespacho = :codDespacho")
    , @NamedQuery(name = "Despachos.findByDescripcion", query = "SELECT d FROM Despachos d WHERE d.descripcion = :descripcion")})
public class Despachos implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cod_despacho")
    private Integer codDespacho;
   
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "descripcion")
    private String descripcion;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codDespacho")
    @JsonIgnore
    private List<Expedientes> expedientesList;
   
    @OneToMany(mappedBy = "camaraSorteada")
    @JsonIgnore
    private List<Expedientes> expedientesList1;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codDespacho")
    @JsonIgnore
    private List<DetalleExpediente> detalleExpedienteList;
    
    @JoinColumn(name = "cod_actuario", referencedColumnName = "cod_actuario")
    @ManyToOne
    private Actuarios codActuario;
    
    @JoinColumn(name = "cod_departamento", referencedColumnName = "cod_departamento")
    @ManyToOne(optional = false)
    private Departamentos codDepartamento;
    
    @JoinColumn(name = "cod_fuero", referencedColumnName = "cod_fuero")
    @ManyToOne(optional = false)
    private Fueros codFuero;
    
    @JoinColumn(name = "cod_juez", referencedColumnName = "cod_juez")
    @ManyToOne
    private Jueces codJuez;
    
    @JoinColumn(name = "cod_ujier", referencedColumnName = "cod_ujier")
    @ManyToOne
    private Ujieres codUjier;

    public Despachos() {
    }

    public Despachos(Integer codDespacho) {
        this.codDespacho = codDespacho;
    }

    public Despachos(Integer codDespacho, String descripcion) {
        this.codDespacho = codDespacho;
        this.descripcion = descripcion;
    }

    public Integer getCodDespacho() {
        return codDespacho;
    }

    public void setCodDespacho(Integer codDespacho) {
        this.codDespacho = codDespacho;
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

    @XmlTransient
    public List<Expedientes> getExpedientesList1() {
        return expedientesList1;
    }

    public void setExpedientesList1(List<Expedientes> expedientesList1) {
        this.expedientesList1 = expedientesList1;
    }

    @XmlTransient
    public List<DetalleExpediente> getDetalleExpedienteList() {
        return detalleExpedienteList;
    }

    public void setDetalleExpedienteList(List<DetalleExpediente> detalleExpedienteList) {
        this.detalleExpedienteList = detalleExpedienteList;
    }

    public Actuarios getCodActuario() {
        return codActuario;
    }

    public void setCodActuario(Actuarios codActuario) {
        this.codActuario = codActuario;
    }

    public Departamentos getCodDepartamento() {
        return codDepartamento;
    }

    public void setCodDepartamento(Departamentos codDepartamento) {
        this.codDepartamento = codDepartamento;
    }

    public Fueros getCodFuero() {
        return codFuero;
    }

    public void setCodFuero(Fueros codFuero) {
        this.codFuero = codFuero;
    }

    public Jueces getCodJuez() {
        return codJuez;
    }

    public void setCodJuez(Jueces codJuez) {
        this.codJuez = codJuez;
    }

    public Ujieres getCodUjier() {
        return codUjier;
    }

    public void setCodUjier(Ujieres codUjier) {
        this.codUjier = codUjier;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codDespacho != null ? codDespacho.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Despachos)) {
            return false;
        }
        Despachos other = (Despachos) object;
        if ((this.codDespacho == null && other.codDespacho != null) || (this.codDespacho != null && !this.codDespacho.equals(other.codDespacho))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lawyersys.pclsystembacke.entities.Despachos[ codDespacho=" + codDespacho + " ]";
    }
    
}
