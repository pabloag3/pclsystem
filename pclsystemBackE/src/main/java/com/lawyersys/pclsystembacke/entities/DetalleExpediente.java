/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lawyersys.pclsystembacke.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
@Table(name = "detalle_expediente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleExpediente.findAll", query = "SELECT d FROM DetalleExpediente d")
    , @NamedQuery(name = "DetalleExpediente.findByCodDetalleExpediente", query = "SELECT d FROM DetalleExpediente d WHERE d.detalleExpedientePK.codDetalleExpediente = :codDetalleExpediente")
    , @NamedQuery(name = "DetalleExpediente.findByCodExpediente", query = "SELECT d FROM DetalleExpediente d WHERE d.detalleExpedientePK.codExpediente = :codExpediente")
    , @NamedQuery(name = "DetalleExpediente.findByDescripcion", query = "SELECT d FROM DetalleExpediente d WHERE d.descripcion = :descripcion")
    , @NamedQuery(name = "DetalleExpediente.findByFecha", query = "SELECT d FROM DetalleExpediente d WHERE d.fecha = :fecha")})
public class DetalleExpediente implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    protected DetalleExpedientePK detalleExpedientePK;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "descripcion")
    private String descripcion;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    
    @Lob
    @Column(name = "archivo")
    private byte[] archivo;
    
    @JoinColumn(name = "cod_expediente", referencedColumnName = "cod_expediente", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Expedientes expedientes;
    
    @JoinColumn(name = "cod_tipo_actuacion", referencedColumnName = "cod_tipo_actuacion")
    @ManyToOne(optional = false)
    private TiposActuaciones codTipoActuacion;
    
    @JoinColumn(name = "cod_despacho", referencedColumnName = "cod_despacho")
    @ManyToOne(optional = false)
    private Despachos codDespacho;

    public DetalleExpediente() {
    }

    public DetalleExpediente(DetalleExpedientePK detalleExpedientePK) {
        this.detalleExpedientePK = detalleExpedientePK;
    }

    public DetalleExpediente(DetalleExpedientePK detalleExpedientePK, String descripcion, Date fecha, byte[] archivo) {
        this.detalleExpedientePK = detalleExpedientePK;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.archivo = archivo;
    }

    public DetalleExpediente(int codDetalleExpediente, int codExpediente) {
        this.detalleExpedientePK = new DetalleExpedientePK(codDetalleExpediente, codExpediente);
    }

    public DetalleExpedientePK getDetalleExpedientePK() {
        return detalleExpedientePK;
    }

    public void setDetalleExpedientePK(DetalleExpedientePK detalleExpedientePK) {
        this.detalleExpedientePK = detalleExpedientePK;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }


    public Expedientes getExpedientes() {
        return expedientes;
    }

    public void setExpedientes(Expedientes expedientes) {
        this.expedientes = expedientes;
    }

    public TiposActuaciones getCodTipoActuacion() {
        return codTipoActuacion;
    }

    public void setCodTipoActuacion(TiposActuaciones codTipoActuacion) {
        this.codTipoActuacion = codTipoActuacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detalleExpedientePK != null ? detalleExpedientePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleExpediente)) {
            return false;
        }
        DetalleExpediente other = (DetalleExpediente) object;
        if ((this.detalleExpedientePK == null && other.detalleExpedientePK != null) || (this.detalleExpedientePK != null && !this.detalleExpedientePK.equals(other.detalleExpedientePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lawyersys.pclsystembacke.DetalleExpediente[ detalleExpedientePK=" + detalleExpedientePK + " ]";
    }

    public byte[] getArchivo() {
        return archivo;
    }

    public void setArchivo(byte[] archivo) {
        this.archivo = archivo;
    }

    public Despachos getCodDespacho() {
        return codDespacho;
    }

    public void setCodDespacho(Despachos codDespacho) {
        this.codDespacho = codDespacho;
    }
    
}
