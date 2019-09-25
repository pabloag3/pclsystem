package com.lawyersys.pclsystembacke.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
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
@Table(name = "jueces")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Jueces.findAll", query = "SELECT j FROM Jueces j")
    , @NamedQuery(name = "Jueces.findByCodJuez", query = "SELECT j FROM Jueces j WHERE j.codJuez = :codJuez")
    , @NamedQuery(name = "Jueces.findByNombre", query = "SELECT j FROM Jueces j WHERE j.nombre = :nombre")
    , @NamedQuery(name = "Jueces.findByApellido", query = "SELECT j FROM Jueces j WHERE j.apellido = :apellido")
    , @NamedQuery(name = "Jueces.findByTelefono1", query = "SELECT j FROM Jueces j WHERE j.telefono1 = :telefono1")
    , @NamedQuery(name = "Jueces.findByTelefono2", query = "SELECT j FROM Jueces j WHERE j.telefono2 = :telefono2")})
public class Jueces implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cod_juez")
    private Integer codJuez;
    
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
    
    @OneToMany(mappedBy = "codJuez")
    @JsonIgnore
    private List<DetalleExpediente> detalleExpedienteList;
    
    @OneToMany(mappedBy = "codJuez")
    @JsonIgnore
    private List<Despachos> despachosList;

    public Jueces() {
    }

    public Jueces(Integer codJuez) {
        this.codJuez = codJuez;
    }

    public Jueces(Integer codJuez, String nombre, String apellido) {
        this.codJuez = codJuez;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Integer getCodJuez() {
        return codJuez;
    }

    public void setCodJuez(Integer codJuez) {
        this.codJuez = codJuez;
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
    public List<DetalleExpediente> getDetalleExpedienteList() {
        return detalleExpedienteList;
    }

    public void setDetalleExpedienteList(List<DetalleExpediente> detalleExpedienteList) {
        this.detalleExpedienteList = detalleExpedienteList;
    }

    @XmlTransient
    public List<Despachos> getDespachosList() {
        return despachosList;
    }

    public void setDespachosList(List<Despachos> despachosList) {
        this.despachosList = despachosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codJuez != null ? codJuez.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Jueces)) {
            return false;
        }
        Jueces other = (Jueces) object;
        if ((this.codJuez == null && other.codJuez != null) || (this.codJuez != null && !this.codJuez.equals(other.codJuez))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lawyersys.pclsystembacke.entities.Jueces[ codJuez=" + codJuez + " ]";
    }
    
}
