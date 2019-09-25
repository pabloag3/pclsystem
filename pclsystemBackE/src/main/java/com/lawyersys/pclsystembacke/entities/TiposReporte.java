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
@Table(name = "tipos_reporte")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TiposReporte.findAll", query = "SELECT t FROM TiposReporte t")
    , @NamedQuery(name = "TiposReporte.findByCodTipoReporte", query = "SELECT t FROM TiposReporte t WHERE t.codTipoReporte = :codTipoReporte")
    , @NamedQuery(name = "TiposReporte.findByDescripcion", query = "SELECT t FROM TiposReporte t WHERE t.descripcion = :descripcion")})
public class TiposReporte implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cod_tipo_reporte")
    private Integer codTipoReporte;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "descripcion")
    private String descripcion;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codTipoReporte")
    @JsonIgnore
    private List<Reportes> reportesList;

    public TiposReporte() {
    }

    public TiposReporte(Integer codTipoReporte) {
        this.codTipoReporte = codTipoReporte;
    }

    public TiposReporte(Integer codTipoReporte, String descripcion) {
        this.codTipoReporte = codTipoReporte;
        this.descripcion = descripcion;
    }

    public Integer getCodTipoReporte() {
        return codTipoReporte;
    }

    public void setCodTipoReporte(Integer codTipoReporte) {
        this.codTipoReporte = codTipoReporte;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<Reportes> getReportesList() {
        return reportesList;
    }

    public void setReportesList(List<Reportes> reportesList) {
        this.reportesList = reportesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codTipoReporte != null ? codTipoReporte.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TiposReporte)) {
            return false;
        }
        TiposReporte other = (TiposReporte) object;
        if ((this.codTipoReporte == null && other.codTipoReporte != null) || (this.codTipoReporte != null && !this.codTipoReporte.equals(other.codTipoReporte))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lawyersys.pclsystembacke.entities.TiposReporte[ codTipoReporte=" + codTipoReporte + " ]";
    }
    
}
