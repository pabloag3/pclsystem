/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lawyersys.pclsystembacke.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "cuentas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cuentas.findAll", query = "SELECT c FROM Cuentas c")
    , @NamedQuery(name = "Cuentas.findByCodCuenta", query = "SELECT c FROM Cuentas c WHERE c.cuentasPK.codCuenta = :codCuenta")
    , @NamedQuery(name = "Cuentas.findByCodCliente", query = "SELECT c FROM Cuentas c WHERE c.cuentasPK.codCliente = :codCliente")
    , @NamedQuery(name = "Cuentas.findByCuentaCliente", query = "SELECT c FROM Cuentas c WHERE c.cuentasPK.codCuenta = :codCuenta AND c.cuentasPK.codCliente = :codCliente")
    , @NamedQuery(name = "Cuentas.findByTotal", query = "SELECT c FROM Cuentas c WHERE c.total = :total")
    , @NamedQuery(name = "Cuentas.findBySaldo", query = "SELECT c FROM Cuentas c WHERE c.saldo = :saldo")
    , @NamedQuery(name = "Cuentas.findByEstado", query = "SELECT c FROM Cuentas c WHERE c.estado = :estado")})
public class Cuentas implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    protected CuentasPK cuentasPK;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "total")
    private int total;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "saldo")
    private int saldo;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private boolean estado;
    
    @Size(max = 2147483647)
    @Column(name = "descripcion")
    private String descripcion;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cuentas")
    @JsonIgnore
    private List<Facturas> facturasList;
    
    @JoinColumn(name = "cod_caso", referencedColumnName = "cod_caso")
    @ManyToOne(optional = false)
    private Casos codCaso;
    
    @JoinColumn(name = "cod_cliente", referencedColumnName = "cod_cliente", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Clientes clientes;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cuentas")
    @JsonIgnore
    private List<Pagos> pagosList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cuentas")
    @JsonIgnore
    private List<DetalleCuenta> detalleCuentaList;

    public Cuentas() {
    }

    public Cuentas(CuentasPK cuentasPK) {
        this.cuentasPK = cuentasPK;
    }

    public Cuentas(CuentasPK cuentasPK, int total, int saldo, boolean estado) {
        this.cuentasPK = cuentasPK;
        this.total = total;
        this.saldo = saldo;
        this.estado = estado;
    }

    public Cuentas(int codCuenta, int codCliente) {
        this.cuentasPK = new CuentasPK(codCuenta, codCliente);
    }

    public CuentasPK getCuentasPK() {
        return cuentasPK;
    }

    public void setCuentasPK(CuentasPK cuentasPK) {
        this.cuentasPK = cuentasPK;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @XmlTransient
    public List<Facturas> getFacturasList() {
        return facturasList;
    }

    public void setFacturasList(List<Facturas> facturasList) {
        this.facturasList = facturasList;
    }

    public Casos getCodCaso() {
        return codCaso;
    }

    public void setCodCaso(Casos codCaso) {
        this.codCaso = codCaso;
    }

    public Clientes getClientes() {
        return clientes;
    }

    public void setClientes(Clientes clientes) {
        this.clientes = clientes;
    }

    @XmlTransient
    public List<Pagos> getPagosList() {
        return pagosList;
    }

    public void setPagosList(List<Pagos> pagosList) {
        this.pagosList = pagosList;
    }

    @XmlTransient
    public List<DetalleCuenta> getDetalleCuentaList() {
        return detalleCuentaList;
    }

    public void setDetalleCuentaList(List<DetalleCuenta> detalleCuentaList) {
        this.detalleCuentaList = detalleCuentaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cuentasPK != null ? cuentasPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cuentas)) {
            return false;
        }
        Cuentas other = (Cuentas) object;
        if ((this.cuentasPK == null && other.cuentasPK != null) || (this.cuentasPK != null && !this.cuentasPK.equals(other.cuentasPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lawyersys.pclsystembacke.Cuentas[ cuentasPK=" + cuentasPK + " ]";
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}
