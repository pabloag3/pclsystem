package com.lawyersys.pclsystembacke.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author tatoa
 */
@Entity
@Table(name = "expedientes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Expedientes.findAll", query = "SELECT e FROM Expedientes e")
    , @NamedQuery(name = "Expedientes.findByCodExpediente", query = "SELECT e FROM Expedientes e WHERE e.codExpediente = :codExpediente")
    , @NamedQuery(name = "Expedientes.findByDescripcion", query = "SELECT e FROM Expedientes e WHERE e.descripcion = :descripcion")
    , @NamedQuery(name = "Expedientes.findByCaratula", query = "SELECT e FROM Expedientes e WHERE e.caratula = :caratula")
    , @NamedQuery(name = "Expedientes.findByFecha", query = "SELECT e FROM Expedientes e WHERE e.fecha = :fecha")
    , @NamedQuery(name = "Expedientes.findByNroExpediente", query = "SELECT e FROM Expedientes e WHERE e.nroExpediente = :nroExpediente")
    , @NamedQuery(name = "Expedientes.findByFechaFin", query = "SELECT e FROM Expedientes e WHERE e.fechaFin = :fechaFin")
    , @NamedQuery(name = "Expedientes.findByParentCodExpediente", query = "SELECT e FROM Expedientes e WHERE e.parentCodExpediente = :parentCodExpediente")
    , @NamedQuery(name = "Expedientes.findBycodDespacho", query = "SELECT e FROM Expedientes e WHERE e.codDespacho.codDespacho = :codDespacho")
    , @NamedQuery(name = "Expedientes.findBycodCaso", query = "SELECT e FROM Expedientes e WHERE e.codCaso.codCaso = :codCaso")
    , @NamedQuery(name = "Expedientes.findHijosDeExpediente", query = "SELECT e FROM Expedientes e WHERE e.parentCodExpediente = :codExpediente")
})
public class Expedientes implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cod_expediente")
    private Integer codExpediente;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "descripcion")
    private String descripcion;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "caratula")
    private String caratula;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "nro_expediente")
    private int nroExpediente;
    
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    
    @Column(name = "parent_cod_expediente")
    private Integer parentCodExpediente;
    
    @JoinColumn(name = "cod_caso", referencedColumnName = "cod_caso")
    @ManyToOne(optional = false)
    private Casos codCaso;
    
    @JoinColumn(name = "cod_despacho", referencedColumnName = "cod_despacho")
    @ManyToOne(optional = false)
    private Despachos codDespacho;
    
    @JoinColumn(name = "camara_sorteada", referencedColumnName = "cod_despacho")
    @ManyToOne
    private Despachos camaraSorteada;
    
    @JoinColumn(name = "cod_estado_expediente", referencedColumnName = "cod_estado_expediente")
    @ManyToOne(optional = false)
    private EstadoExpediente codEstadoExpediente;
    
    @OneToMany(mappedBy = "codExpediente")
    @JsonIgnore
    private List<Actividades> actividadesList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codExpediente")
    @JsonIgnore
    private List<Pagos> pagosList;

    public Expedientes() {
    }

    public Expedientes(Integer codExpediente) {
        this.codExpediente = codExpediente;
    }

    public Expedientes(Integer codExpediente, String descripcion, String caratula, Date fecha, int nroExpediente) {
        this.codExpediente = codExpediente;
        this.descripcion = descripcion;
        this.caratula = caratula;
        this.fecha = fecha;
        this.nroExpediente = nroExpediente;
    }

    public Integer getCodExpediente() {
        return codExpediente;
    }

    public void setCodExpediente(Integer codExpediente) {
        this.codExpediente = codExpediente;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCaratula() {
        return caratula;
    }

    public void setCaratula(String caratula) {
        this.caratula = caratula;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getNroExpediente() {
        return nroExpediente;
    }

    public void setNroExpediente(int nroExpediente) {
        this.nroExpediente = nroExpediente;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Integer getParentCodExpediente() {
        return parentCodExpediente;
    }

    public void setParentCodExpediente(Integer parentCodExpediente) {
        this.parentCodExpediente = parentCodExpediente;
    }

    public Casos getCodCaso() {
        return codCaso;
    }

    public void setCodCaso(Casos codCaso) {
        this.codCaso = codCaso;
    }

    public Despachos getCodDespacho() {
        return codDespacho;
    }

    public void setCodDespacho(Despachos codDespacho) {
        this.codDespacho = codDespacho;
    }

    public Despachos getCamaraSorteada() {
        return camaraSorteada;
    }

    public void setCamaraSorteada(Despachos camaraSorteada) {
        this.camaraSorteada = camaraSorteada;
    }

    public EstadoExpediente getCodEstadoExpediente() {
        return codEstadoExpediente;
    }

    public void setCodEstadoExpediente(EstadoExpediente codEstadoExpediente) {
        this.codEstadoExpediente = codEstadoExpediente;
    }

    @XmlTransient
    public List<Actividades> getActividadesList() {
        return actividadesList;
    }

    public void setActividadesList(List<Actividades> actividadesList) {
        this.actividadesList = actividadesList;
    }

    @XmlTransient
    public List<Pagos> getPagosList() {
        return pagosList;
    }

    public void setPagosList(List<Pagos> pagosList) {
        this.pagosList = pagosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codExpediente != null ? codExpediente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Expedientes)) {
            return false;
        }
        Expedientes other = (Expedientes) object;
        if ((this.codExpediente == null && other.codExpediente != null) || (this.codExpediente != null && !this.codExpediente.equals(other.codExpediente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lawyersys.pclsystembacke.entities.Expedientes[ codExpediente=" + codExpediente + " ]";
    }
    
}
