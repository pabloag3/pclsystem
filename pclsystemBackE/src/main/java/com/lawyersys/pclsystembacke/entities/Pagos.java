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
 * @author carlo
 */
@Entity
@Table(name = "pagos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pagos.findAll", query = "SELECT p FROM Pagos p")
    , @NamedQuery(name = "Pagos.findByCodPago", query = "SELECT p FROM Pagos p WHERE p.codPago = :codPago")
    , @NamedQuery(name = "Pagos.findByFechaPago", query = "SELECT p FROM Pagos p WHERE p.fechaPago = :fechaPago")
    , @NamedQuery(name = "Pagos.findByMontoPagado", query = "SELECT p FROM Pagos p WHERE p.montoPagado = :montoPagado")
    , @NamedQuery(name = "Pagos.findByNroComproBanco", query = "SELECT p FROM Pagos p WHERE p.nroComproBanco = :nroComproBanco")
    , @NamedQuery(name = "Pagos.findByEntidadFinanciera", query = "SELECT p FROM Pagos p WHERE p.entidadFinanciera = :entidadFinanciera")
    , @NamedQuery(name = "Pagos.findByNroTarjeta", query = "SELECT p FROM Pagos p WHERE p.nroTarjeta = :nroTarjeta")
    , @NamedQuery(name = "Pagos.findByNroSerieCheque", query = "SELECT p FROM Pagos p WHERE p.nroSerieCheque = :nroSerieCheque")
    , @NamedQuery(name = "Pagos.findByNroCuentaCheque", query = "SELECT p FROM Pagos p WHERE p.nroCuentaCheque = :nroCuentaCheque")
    , @NamedQuery(name = "Pagos.findByFechaVencCheque", query = "SELECT p FROM Pagos p WHERE p.fechaVencCheque = :fechaVencCheque")})
public class Pagos implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cod_pago")
    private Integer codPago;
    
    @JoinColumn(name = "cod_cuenta", referencedColumnName = "cod_cuenta")
    @ManyToOne(optional = false)
    private Cuentas codCuenta;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_pago")
    @Temporal(TemporalType.DATE)
    private Date fechaPago;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "monto_pagado")
    private int montoPagado;
    
    @Size(max = 150)
    @Column(name = "nro_compro_banco")
    private String nroComproBanco;
    
    @Size(max = 50)
    @Column(name = "entidad_financiera")
    private String entidadFinanciera;
    
    @Size(max = 3)
    @Column(name = "nro_tarjeta")
    private String nroTarjeta;
    
    @Size(max = 20)
    @Column(name = "nro_serie_cheque")
    private String nroSerieCheque;
    
    @Size(max = 20)
    @Column(name = "nro_cuenta_cheque")
    private String nroCuentaCheque;
    
    @Column(name = "fecha_venc_cheque")
    @Temporal(TemporalType.DATE)
    private Date fechaVencCheque;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codRecibo")
    @JsonIgnore
    private List<Recibos> recibosList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codPago")
    @JsonIgnore
    private List<DetalleFactura> detalleFacturaList;
    
    @JoinColumn(name = "cod_tipo_pago", referencedColumnName = "cod_tipo_pago")
    @ManyToOne(optional = false)
    private TiposPagos codTipoPago;

    public Pagos() {
    }

    public Pagos(Integer codPago, Date fechaPago, int montoPagado) {
        this.codPago = codPago;
        this.fechaPago = fechaPago;
        this.montoPagado = montoPagado;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public int getMontoPagado() {
        return montoPagado;
    }

    public void setMontoPagado(int montoPagado) {
        this.montoPagado = montoPagado;
    }

    public String getNroComproBanco() {
        return nroComproBanco;
    }

    public void setNroComproBanco(String nroComproBanco) {
        this.nroComproBanco = nroComproBanco;
    }

    public String getEntidadFinanciera() {
        return entidadFinanciera;
    }

    public void setEntidadFinanciera(String entidadFinanciera) {
        this.entidadFinanciera = entidadFinanciera;
    }

    public String getNroTarjeta() {
        return nroTarjeta;
    }

    public void setNroTarjeta(String nroTarjeta) {
        this.nroTarjeta = nroTarjeta;
    }

    public String getNroSerieCheque() {
        return nroSerieCheque;
    }

    public void setNroSerieCheque(String nroSerieCheque) {
        this.nroSerieCheque = nroSerieCheque;
    }

    public String getNroCuentaCheque() {
        return nroCuentaCheque;
    }

    public void setNroCuentaCheque(String nroCuentaCheque) {
        this.nroCuentaCheque = nroCuentaCheque;
    }

    public Date getFechaVencCheque() {
        return fechaVencCheque;
    }

    public void setFechaVencCheque(Date fechaVencCheque) {
        this.fechaVencCheque = fechaVencCheque;
    }

    @XmlTransient
    public List<Recibos> getRecibosList() {
        return recibosList;
    }

    public void setRecibosList(List<Recibos> recibosList) {
        this.recibosList = recibosList;
    }

    @XmlTransient
    public List<DetalleFactura> getDetalleFacturaList() {
        return detalleFacturaList;
    }

    public void setDetalleFacturaList(List<DetalleFactura> detalleFacturaList) {
        this.detalleFacturaList = detalleFacturaList;
    }

    public TiposPagos getCodTipoPago() {
        return codTipoPago;
    }

    public void setCodTipoPago(TiposPagos codTipoPago) {
        this.codTipoPago = codTipoPago;
    }

    public Pagos(Integer codPago) {
        this.codPago = codPago;
    }

    public Integer getCodPago() {
        return codPago;
    }

    public void setCodPago(Integer codPago) {
        this.codPago = codPago;
    }

    public Cuentas getCodCuenta() {
        return codCuenta;
    }

    public void setCodCuenta(Cuentas codCuenta) {
        this.codCuenta = codCuenta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codPago != null ? codPago.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pagos)) {
            return false;
        }
        Pagos other = (Pagos) object;
        if ((this.codPago == null && other.codPago != null) || (this.codPago != null && !this.codPago.equals(other.codPago))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lawyersys.pclsystembacke.entities.Pagos[ codPago=" + codPago + " ]";
    }
    
}
