package com.lawyersys.pclsystembacke.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author carlo
 */
@Entity
@Table(name = "reportes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reportes.findAll", query = "SELECT r FROM Reportes r")
    , @NamedQuery(name = "Reportes.findByCodReporte", query = "SELECT r FROM Reportes r WHERE r.codReporte = :codReporte")
    , @NamedQuery(name = "Reportes.findByFecha", query = "SELECT r FROM Reportes r WHERE r.fecha = :fecha")
    , @NamedQuery(name = "Reportes.findByDescripcion", query = "SELECT r FROM Reportes r WHERE r.descripcion = :descripcion")
    , @NamedQuery(name = "Reportes.findByParametros", query = "SELECT r FROM Reportes r WHERE r.parametros = :parametros")})
public class Reportes implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cod_reporte")
    private Integer codReporte;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    
    @Size(max = 100)
    @Column(name = "descripcion")
    private String descripcion;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "parametros")
    private String parametros;
    
    @JoinColumn(name = "cod_tipo_reporte", referencedColumnName = "cod_tipo_reporte")
    @ManyToOne(optional = false)
    private TiposReporte codTipoReporte;
    
    @JoinColumn(name = "cod_usuario", referencedColumnName = "cod_usuario")
    @ManyToOne(optional = false)
    private Usuarios codUsuario;

    public Reportes() {
    }

    public Reportes(Integer codReporte) {
        this.codReporte = codReporte;
    }

    public Reportes(Integer codReporte, Date fecha, String parametros) {
        this.codReporte = codReporte;
        this.fecha = fecha;
        this.parametros = parametros;
    }

    public Integer getCodReporte() {
        return codReporte;
    }

    public void setCodReporte(Integer codReporte) {
        this.codReporte = codReporte;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getParametros() {
        return parametros;
    }

    public void setParametros(String parametros) {
        this.parametros = parametros;
    }

    public TiposReporte getCodTipoReporte() {
        return codTipoReporte;
    }

    public void setCodTipoReporte(TiposReporte codTipoReporte) {
        this.codTipoReporte = codTipoReporte;
    }

    public Usuarios getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(Usuarios codUsuario) {
        this.codUsuario = codUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codReporte != null ? codReporte.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reportes)) {
            return false;
        }
        Reportes other = (Reportes) object;
        if ((this.codReporte == null && other.codReporte != null) || (this.codReporte != null && !this.codReporte.equals(other.codReporte))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lawyersys.pclsystembacke.entities.Reportes[ codReporte=" + codReporte + " ]";
    }
    
}
