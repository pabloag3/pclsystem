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
@Table(name = "fueros")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Fueros.findAll", query = "SELECT f FROM Fueros f")
    , @NamedQuery(name = "Fueros.findByCodFuero", query = "SELECT f FROM Fueros f WHERE f.codFuero = :codFuero")
    , @NamedQuery(name = "Fueros.findByTipoFuero", query = "SELECT f FROM Fueros f WHERE f.tipoFuero = :tipoFuero")})
public class Fueros implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cod_fuero")
    private Integer codFuero;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "tipo_fuero")
    private String tipoFuero;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codFuero")
    @JsonIgnore
    private List<Despachos> despachosList;

    public Fueros() {
    }

    public Fueros(Integer codFuero) {
        this.codFuero = codFuero;
    }

    public Fueros(Integer codFuero, String tipoFuero) {
        this.codFuero = codFuero;
        this.tipoFuero = tipoFuero;
    }

    public Integer getCodFuero() {
        return codFuero;
    }

    public void setCodFuero(Integer codFuero) {
        this.codFuero = codFuero;
    }

    public String getTipoFuero() {
        return tipoFuero;
    }

    public void setTipoFuero(String tipoFuero) {
        this.tipoFuero = tipoFuero;
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
        hash += (codFuero != null ? codFuero.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fueros)) {
            return false;
        }
        Fueros other = (Fueros) object;
        if ((this.codFuero == null && other.codFuero != null) || (this.codFuero != null && !this.codFuero.equals(other.codFuero))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lawyersys.pclsystembacke.Fueros[ codFuero=" + codFuero + " ]";
    }
    
}
