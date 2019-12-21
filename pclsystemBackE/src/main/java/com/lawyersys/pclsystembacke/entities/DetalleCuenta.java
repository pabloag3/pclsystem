package com.lawyersys.pclsystembacke.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "detalle_cuenta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleCuenta.findAll", query = "SELECT d FROM DetalleCuenta d")
    , @NamedQuery(name = "DetalleCuenta.findByCodDetalleCuenta", query = "SELECT d FROM DetalleCuenta d WHERE d.detalleCuentaPK.codDetalleCuenta = :codDetalleCuenta")
    , @NamedQuery(name = "DetalleCuenta.findByCodCuenta", query = "SELECT d FROM DetalleCuenta d WHERE d.detalleCuentaPK.codCuenta = :codCuenta")
    , @NamedQuery(name = "DetalleCuenta.findByDescripcion", query = "SELECT d FROM DetalleCuenta d WHERE d.descripcion = :descripcion")
    , @NamedQuery(name = "DetalleCuenta.findByMonto", query = "SELECT d FROM DetalleCuenta d WHERE d.monto = :monto")
    , @NamedQuery(name = "DetalleCuenta.findByEstado", query = "SELECT d FROM DetalleCuenta d WHERE d.estado = :estado")
    , @NamedQuery(name = "DetalleCuenta.findBySaldoDetalleCta", query = "SELECT d FROM DetalleCuenta d WHERE d.saldoDetalleCta = :saldoDetalleCta")
    , @NamedQuery(name = "DetalleCuenta.traerUltimoDetalleCta", query = "SELECT d FROM DetalleCuenta d ORDER BY d.detalleCuentaPK.codDetalleCuenta DESC")
})
public class DetalleCuenta implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    protected DetalleCuentaPK detalleCuentaPK;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "descripcion")
    private String descripcion;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "monto")
    private int monto;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private boolean estado;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "saldo_detalle_cta")
    private int saldoDetalleCta;
    
    @JoinColumn(name = "cod_cuenta", referencedColumnName = "cod_cuenta", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Cuentas cuentas;
    
    @JoinColumn(name = "cod_expediente", referencedColumnName = "cod_expediente")
    @ManyToOne(optional = false)
    private Expedientes codExpediente;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    
    public DetalleCuenta() {
    }

    public DetalleCuenta(DetalleCuentaPK detalleCuentaPK) {
        this.detalleCuentaPK = detalleCuentaPK;
    }

    public DetalleCuenta(DetalleCuentaPK detalleCuentaPK, String descripcion, int monto, boolean estado, int saldoDetalleCta) {
        this.detalleCuentaPK = detalleCuentaPK;
        this.descripcion = descripcion;
        this.monto = monto;
        this.estado = estado;
        this.saldoDetalleCta = saldoDetalleCta;
    }

    public DetalleCuenta(int codDetalleCuenta, int codCuenta) {
        this.detalleCuentaPK = new DetalleCuentaPK(codDetalleCuenta, codCuenta);
    }

    public DetalleCuentaPK getDetalleCuentaPK() {
        return detalleCuentaPK;
    }

    public void setDetalleCuentaPK(DetalleCuentaPK detalleCuentaPK) {
        this.detalleCuentaPK = detalleCuentaPK;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public int getSaldoDetalleCta() {
        return saldoDetalleCta;
    }

    public void setSaldoDetalleCta(int saldoDetalleCta) {
        this.saldoDetalleCta = saldoDetalleCta;
    }

    public Cuentas getCuentas() {
        return cuentas;
    }

    public void setCuentas(Cuentas cuentas) {
        this.cuentas = cuentas;
    }
    
    public Expedientes getCodExpediente() {
        return codExpediente;
    }

    public void setCodExpediente(Expedientes codExpediente) {
        this.codExpediente = codExpediente;
    }
    
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detalleCuentaPK != null ? detalleCuentaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleCuenta)) {
            return false;
        }
        DetalleCuenta other = (DetalleCuenta) object;
        if ((this.detalleCuentaPK == null && other.detalleCuentaPK != null) || (this.detalleCuentaPK != null && !this.detalleCuentaPK.equals(other.detalleCuentaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lawyersys.pclsystembacke.DetalleCuenta[ detalleCuentaPK=" + detalleCuentaPK + " ]";
    }
    
}
