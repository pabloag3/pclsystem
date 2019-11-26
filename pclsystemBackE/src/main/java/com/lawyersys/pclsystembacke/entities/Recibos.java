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
@Table(name = "recibos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Recibos.findAll", query = "SELECT r FROM Recibos r")
    , @NamedQuery(name = "Recibos.findByCodRecibo", query = "SELECT r FROM Recibos r WHERE r.codRecibo = :codRecibo")
    , @NamedQuery(name = "Recibos.findByDescripcion", query = "SELECT r FROM Recibos r WHERE r.descripcion = :descripcion")
    , @NamedQuery(name = "Recibos.findByMonto", query = "SELECT r FROM Recibos r WHERE r.monto = :monto")})
public class Recibos implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cod_recibo")
    private Integer codRecibo;
    
    @JoinColumn(name = "cod_pago", referencedColumnName = "cod_pago")
    @ManyToOne(optional = false)
    private Pagos codPago;

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "descripcion")
    private String descripcion;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "monto")
    private int monto;
    
    @Size(max = 2147483647)
    @Column(name = "archivo")
    private String archivo;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "facturado")
    private boolean facturado;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "monto_texto")
    private String montoTexto;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_emision")
    @Temporal(TemporalType.DATE)
    private Date fechaEmision;

    public Recibos() {
    }
    
    public Recibos(Integer codRecibo) {
        this.codRecibo = codRecibo;
    }

    public Integer getCodRecibo() {
        return codRecibo;
    }

    public void setCodRecibo(Integer codRecibo) {
        this.codRecibo = codRecibo;
    }

    public Pagos getCodPago() {
        return codPago;
    }

    public void setCodPago(Pagos codPago) {
        this.codPago = codPago;
    }

    public Recibos(Integer codRecibo, String descripcion, int monto) {
        this.codRecibo = codRecibo;
        this.descripcion = descripcion;
        this.monto = monto;
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
    
    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }
    
    public boolean getFacturado() {
        return facturado;
    }

    public void setFacturado(boolean facturado) {
        this.facturado = facturado;
    }

    public String getMontoTexto() {
        return montoTexto;
    }

    public void setMontoTexto(String montoTexto) {
        this.montoTexto = montoTexto;
    }
    
    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codRecibo != null ? codRecibo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Recibos)) {
            return false;
        }
        Recibos other = (Recibos) object;
        if ((this.codRecibo == null && other.codRecibo != null) || (this.codRecibo != null && !this.codRecibo.equals(other.codRecibo))) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "com.lawyersys.pclsystembacke.Recibos[ recibosPK=" + codRecibo + " ]";
    }
    
}
