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
@Table(name = "ujieres")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ujieres.findAll", query = "SELECT u FROM Ujieres u")
    , @NamedQuery(name = "Ujieres.findByCodUjier", query = "SELECT u FROM Ujieres u WHERE u.codUjier = :codUjier")
    , @NamedQuery(name = "Ujieres.findByApellido", query = "SELECT u FROM Ujieres u WHERE u.apellido = :apellido")
    , @NamedQuery(name = "Ujieres.findByNombre", query = "SELECT u FROM Ujieres u WHERE u.nombre = :nombre")
    , @NamedQuery(name = "Ujieres.findByTelefono1", query = "SELECT u FROM Ujieres u WHERE u.telefono1 = :telefono1")
    , @NamedQuery(name = "Ujieres.findByTelefono2", query = "SELECT u FROM Ujieres u WHERE u.telefono2 = :telefono2")})
public class Ujieres implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cod_ujier")
    private Integer codUjier;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "apellido")
    private String apellido;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "nombre")
    private String nombre;
    
    @Size(max = 15)
    @Column(name = "telefono_1")
    private String telefono1;
    
    @Size(max = 15)
    @Column(name = "telefono_2")
    private String telefono2;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codUjier")
    @JsonIgnore
    private List<Despachos> despachosList;
    
    @OneToMany(mappedBy = "codUjier")
    @JsonIgnore
    private List<DetalleExpediente> detalleExpedienteList;

    public Ujieres() {
    }

    public Ujieres(Integer codUjier) {
        this.codUjier = codUjier;
    }

    public Ujieres(Integer codUjier, String apellido, String nombre) {
        this.codUjier = codUjier;
        this.apellido = apellido;
        this.nombre = nombre;
    }

    public Integer getCodUjier() {
        return codUjier;
    }

    public void setCodUjier(Integer codUjier) {
        this.codUjier = codUjier;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
        hash += (codUjier != null ? codUjier.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ujieres)) {
            return false;
        }
        Ujieres other = (Ujieres) object;
        if ((this.codUjier == null && other.codUjier != null) || (this.codUjier != null && !this.codUjier.equals(other.codUjier))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lawyersys.pclsystembacke.Ujieres[ codUjier=" + codUjier + " ]";
    }
    
}
