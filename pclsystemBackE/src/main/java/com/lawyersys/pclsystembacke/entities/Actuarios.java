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
@Table(name = "actuarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Actuarios.findAll", query = "SELECT a FROM Actuarios a")
    , @NamedQuery(name = "Actuarios.findByCodActuario", query = "SELECT a FROM Actuarios a WHERE a.codActuario = :codActuario")
    , @NamedQuery(name = "Actuarios.findByNombre", query = "SELECT a FROM Actuarios a WHERE a.nombre = :nombre")
    , @NamedQuery(name = "Actuarios.findByApellido", query = "SELECT a FROM Actuarios a WHERE a.apellido = :apellido")
    , @NamedQuery(name = "Actuarios.findByTelefono1", query = "SELECT a FROM Actuarios a WHERE a.telefono1 = :telefono1")
    , @NamedQuery(name = "Actuarios.findByTelefono2", query = "SELECT a FROM Actuarios a WHERE a.telefono2 = :telefono2")})
public class Actuarios implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cod_actuario")
    private Integer codActuario;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "nombre")
    private String nombre;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "apellido")
    private String apellido;
    
    @Size(max = 2147483647)
    @Column(name = "telefono_1")
    private String telefono1;
    
    @Size(max = 2147483647)
    @Column(name = "telefono_2")
    private String telefono2;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codActuario")
    @JsonIgnore
    private List<Despachos> despachosList;
    
    @OneToMany(mappedBy = "codActuario")
    @JsonIgnore
    private List<DetalleExpediente> detalleExpedienteList;

    public Actuarios() {
    }

    public Actuarios(Integer codActuario) {
        this.codActuario = codActuario;
    }

    public Actuarios(Integer codActuario, String nombre, String apellido) {
        this.codActuario = codActuario;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Integer getCodActuario() {
        return codActuario;
    }

    public void setCodActuario(Integer codActuario) {
        this.codActuario = codActuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono1() {
        return telefono1;
    }

    public void setTelefono1(String telefono1) {
        this.telefono1 = telefono1;
    }

    public String getTelefono2() {
        return telefono2;
    }

    public void setTelefono2(String telefono2) {
        this.telefono2 = telefono2;
    }

    @XmlTransient
    public List<Despachos> getDespachosList() {
        return despachosList;
    }

    public void setDespachosList(List<Despachos> despachosList) {
        this.despachosList = despachosList;
    }

    @XmlTransient
    public List<DetalleExpediente> getDetalleExpedienteList() {
        return detalleExpedienteList;
    }

    public void setDetalleExpedienteList(List<DetalleExpediente> detalleExpedienteList) {
        this.detalleExpedienteList = detalleExpedienteList;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codActuario != null ? codActuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Actuarios)) {
            return false;
        }
        Actuarios other = (Actuarios) object;
        if ((this.codActuario == null && other.codActuario != null) || (this.codActuario != null && !this.codActuario.equals(other.codActuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lawyersys.pclsystembacke.Actuarios[ codActuario=" + codActuario + " ]";
    }
    
}
