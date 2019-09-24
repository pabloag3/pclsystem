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
@Table(name = "actividades")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Actividades.findAll", query = "SELECT a FROM Actividades a")
    , @NamedQuery(name = "Actividades.findByCodActividad", query = "SELECT a FROM Actividades a WHERE a.codActividad = :codActividad")
    , @NamedQuery(name = "Actividades.findByDescripcion", query = "SELECT a FROM Actividades a WHERE a.descripcion = :descripcion")
    , @NamedQuery(name = "Actividades.findByObservacion", query = "SELECT a FROM Actividades a WHERE a.observacion = :observacion")
    , @NamedQuery(name = "Actividades.findByDiaNotificable", query = "SELECT a FROM Actividades a WHERE a.diaNotificable = :diaNotificable")
    , @NamedQuery(name = "Actividades.findByFecha", query = "SELECT a FROM Actividades a WHERE a.fecha = :fecha")})
public class Actividades implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cod_actividad")
    private Integer codActividad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 3000)
    @Column(name = "observacion")
    private String observacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dia_notificable")
    private int diaNotificable;
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @JoinColumn(name = "cedula_responsable", referencedColumnName = "cedula")
    @ManyToOne(optional = false)
    private Empleados cedulaResponsable;
    @JoinColumn(name = "cod_estado", referencedColumnName = "cod_estado")
    @ManyToOne(optional = false)
    private EstadosActividades codEstado;
    @JoinColumn(name = "cod_tipo_actividad", referencedColumnName = "cod_tipo_actividad")
    @ManyToOne(optional = false)
    private TiposActividades codTipoActividad;

    public Actividades() {
    }

    public Actividades(Integer codActividad) {
        this.codActividad = codActividad;
    }

    public Actividades(Integer codActividad, String descripcion, int diaNotificable) {
        this.codActividad = codActividad;
        this.descripcion = descripcion;
        this.diaNotificable = diaNotificable;
    }

    public Integer getCodActividad() {
        return codActividad;
    }

    public void setCodActividad(Integer codActividad) {
        this.codActividad = codActividad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public int getDiaNotificable() {
        return diaNotificable;
    }

    public void setDiaNotificable(int diaNotificable) {
        this.diaNotificable = diaNotificable;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Empleados getCedulaResponsable() {
        return cedulaResponsable;
    }

    public void setCedulaResponsable(Empleados cedulaResponsable) {
        this.cedulaResponsable = cedulaResponsable;
    }

    public EstadosActividades getCodEstado() {
        return codEstado;
    }

    public void setCodEstado(EstadosActividades codEstado) {
        this.codEstado = codEstado;
    }

    public TiposActividades getCodTipoActividad() {
        return codTipoActividad;
    }

    public void setCodTipoActividad(TiposActividades codTipoActividad) {
        this.codTipoActividad = codTipoActividad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codActividad != null ? codActividad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Actividades)) {
            return false;
        }
        Actividades other = (Actividades) object;
        if ((this.codActividad == null && other.codActividad != null) || (this.codActividad != null && !this.codActividad.equals(other.codActividad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lawyersys.pclsystembacke.entities.Actividades[ codActividad=" + codActividad + " ]";
    }
    
}
