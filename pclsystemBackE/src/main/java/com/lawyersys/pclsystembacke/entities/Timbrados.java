/*
 */
package com.lawyersys.pclsystembacke.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Pablo Aguilar
 */
@Entity
@Table(name = "timbrados")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Timbrados.findAll", query = "SELECT t FROM Timbrados t")
    , @NamedQuery(name = "Timbrados.findByNroTimbrado", query = "SELECT t FROM Timbrados t WHERE t.nroTimbrado = :nroTimbrado")
    , @NamedQuery(name = "Timbrados.findByFechaInicio", query = "SELECT t FROM Timbrados t WHERE t.fechaInicio = :fechaInicio")
    , @NamedQuery(name = "Timbrados.findByFechaFin", query = "SELECT t FROM Timbrados t WHERE t.fechaFin = :fechaFin")
    , @NamedQuery(name = "Timbrados.findByNroEstablecimiento", query = "SELECT t FROM Timbrados t WHERE t.nroEstablecimiento = :nroEstablecimiento")
    , @NamedQuery(name = "Timbrados.findByNroPuntoExpedicion", query = "SELECT t FROM Timbrados t WHERE t.nroPuntoExpedicion = :nroPuntoExpedicion")
    , @NamedQuery(name = "Timbrados.findByNroSecInicio", query = "SELECT t FROM Timbrados t WHERE t.nroSecInicio = :nroSecInicio")
    , @NamedQuery(name = "Timbrados.findByNroSecFinal", query = "SELECT t FROM Timbrados t WHERE t.nroSecFinal = :nroSecFinal")
    , @NamedQuery(name = "Timbrados.findByNroSecActual", query = "SELECT t FROM Timbrados t WHERE t.nroSecActual = :nroSecActual")
    , @NamedQuery(name = "Timbrados.traerTimbradoVigenteDeEmpleado", query = "SELECT t FROM Timbrados t WHERE t.cedula.cedula = :cedula AND t.vigente = true")
})
public class Timbrados implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "nro_timbrado")
    private Integer nroTimbrado;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "nro_establecimiento")
    private int nroEstablecimiento;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "nro_punto_expedicion")
    private int nroPuntoExpedicion;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "nro_sec_inicio")
    private int nroSecInicio;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "nro_sec_final")
    private int nroSecFinal;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "nro_sec_actual")
    private int nroSecActual;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "vigente")
    private boolean vigente;
    
    @JoinColumn(name = "cedula", referencedColumnName = "cedula")
    @ManyToOne(optional = false)
    private Empleados cedula;

    public Timbrados() {
    }

    public Timbrados(Integer nroTimbrado) {
        this.nroTimbrado = nroTimbrado;
    }

    public Timbrados(Integer nroTimbrado, Date fechaInicio, Date fechaFin, int nroEstablecimiento, int nroPuntoExpedicion, int nroSecInicio, int nroSecFinal, int nroSecActual) {
        this.nroTimbrado = nroTimbrado;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.nroEstablecimiento = nroEstablecimiento;
        this.nroPuntoExpedicion = nroPuntoExpedicion;
        this.nroSecInicio = nroSecInicio;
        this.nroSecFinal = nroSecFinal;
        this.nroSecActual = nroSecActual;
    }

    public Integer getNroTimbrado() {
        return nroTimbrado;
    }

    public void setNroTimbrado(Integer nroTimbrado) {
        this.nroTimbrado = nroTimbrado;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getNroEstablecimiento() {
        return nroEstablecimiento;
    }

    public void setNroEstablecimiento(int nroEstablecimiento) {
        this.nroEstablecimiento = nroEstablecimiento;
    }

    public int getNroPuntoExpedicion() {
        return nroPuntoExpedicion;
    }

    public void setNroPuntoExpedicion(int nroPuntoExpedicion) {
        this.nroPuntoExpedicion = nroPuntoExpedicion;
    }

    public int getNroSecInicio() {
        return nroSecInicio;
    }

    public void setNroSecInicio(int nroSecInicio) {
        this.nroSecInicio = nroSecInicio;
    }

    public int getNroSecFinal() {
        return nroSecFinal;
    }

    public void setNroSecFinal(int nroSecFinal) {
        this.nroSecFinal = nroSecFinal;
    }

    public int getNroSecActual() {
        return nroSecActual;
    }

    public void setNroSecActual(int nroSecActual) {
        this.nroSecActual = nroSecActual;
    }

    public Empleados getCedula() {
        return cedula;
    }

    public void setCedula(Empleados cedula) {
        this.cedula = cedula;
    }
    
    public boolean getVigente() {
        return vigente;
    }

    public void setVigente(boolean vigente) {
        this.vigente = vigente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nroTimbrado != null ? nroTimbrado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Timbrados)) {
            return false;
        }
        Timbrados other = (Timbrados) object;
        if ((this.nroTimbrado == null && other.nroTimbrado != null) || (this.nroTimbrado != null && !this.nroTimbrado.equals(other.nroTimbrado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lawyersys.pclsystembacke.entities.Timbrados[ nroTimbrado=" + nroTimbrado + " ]";
    }
    
}
