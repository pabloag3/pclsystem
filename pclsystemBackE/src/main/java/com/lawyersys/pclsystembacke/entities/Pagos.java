/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lawyersys.pclsystembacke.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
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
    , @NamedQuery(name = "Pagos.findByCodPago", query = "SELECT p FROM Pagos p WHERE p.pagosPK.codPago = :codPago")
    , @NamedQuery(name = "Pagos.findByCodCuenta", query = "SELECT p FROM Pagos p WHERE p.pagosPK.codCuenta = :codCuenta")
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
    @EmbeddedId
    protected PagosPK pagosPK;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pagos")
    @JsonIgnore
    private List<Recibos> recibosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pagos")
    @JsonIgnore
    private List<DetalleFactura> detalleFacturaList;
    @ManyToOne(optional = false)
    @JoinColumns({
           @JoinColumn(name = "cod_cuenta", referencedColumnName = "cod_cuenta", insertable = false, updatable = false),
           @JoinColumn(name = "cod_cliente", referencedColumnName = "cod_cliente", insertable = false, updatable = false)
    })
    private Cuentas cuentas;
    @JoinColumn(name = "cod_tipo_pago", referencedColumnName = "cod_tipo_pago")
    @ManyToOne(optional = false)
    private TiposPagos codTipoPago;

    public Pagos() {
    }

    public Pagos(PagosPK pagosPK) {
        this.pagosPK = pagosPK;
    }

    public Pagos(PagosPK pagosPK, Date fechaPago, int montoPagado) {
        this.pagosPK = pagosPK;
        this.fechaPago = fechaPago;
        this.montoPagado = montoPagado;
    }

    public Pagos(int codPago, int codCuenta) {
        this.pagosPK = new PagosPK(codPago, codCuenta);
    }

    public PagosPK getPagosPK() {
        return pagosPK;
    }

    public void setPagosPK(PagosPK pagosPK) {
        this.pagosPK = pagosPK;
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

    public Cuentas getCuentas() {
        return cuentas;
    }

    public void setCuentas(Cuentas cuentas) {
        this.cuentas = cuentas;
    }

    public TiposPagos getCodTipoPago() {
        return codTipoPago;
    }

    public void setCodTipoPago(TiposPagos codTipoPago) {
        this.codTipoPago = codTipoPago;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pagosPK != null ? pagosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pagos)) {
            return false;
        }
        Pagos other = (Pagos) object;
        if ((this.pagosPK == null && other.pagosPK != null) || (this.pagosPK != null && !this.pagosPK.equals(other.pagosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lawyersys.pclsystembacke.Pagos[ pagosPK=" + pagosPK + " ]";
    }
    
}
