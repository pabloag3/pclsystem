package com.lawyersys.pclsystembacke.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
 * @author tatoa
 */
@Entity
@Table(name = "recibos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Recibos.findAll", query = "SELECT r FROM Recibos r")
    , @NamedQuery(name = "Recibos.findByCodRecibo", query = "SELECT r FROM Recibos r WHERE r.codRecibo = :codRecibo")
    , @NamedQuery(name = "Recibos.findByDescripcion", query = "SELECT r FROM Recibos r WHERE r.descripcion = :descripcion")
    , @NamedQuery(name = "Recibos.findByMonto", query = "SELECT r FROM Recibos r WHERE r.monto = :monto")
    , @NamedQuery(name = "Recibos.findByArchivo", query = "SELECT r FROM Recibos r WHERE r.archivo = :archivo")
    , @NamedQuery(name = "Recibos.findByFacturado", query = "SELECT r FROM Recibos r WHERE r.facturado = :facturado")
    , @NamedQuery(name = "Recibos.findByMontoTexto", query = "SELECT r FROM Recibos r WHERE r.montoTexto = :montoTexto")
    , @NamedQuery(name = "Recibos.findByFechaEmision", query = "SELECT r FROM Recibos r WHERE r.fechaEmision = :fechaEmision")})
public class Recibos implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cod_recibo")
    private Integer codRecibo;
    
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
    
    @JoinColumns({
        @JoinColumn(name = "cod_factura", referencedColumnName = "cod_factura")
        , @JoinColumn(name = "nro_factura", referencedColumnName = "nro_factura")})
    @ManyToOne
    private Facturas facturas;
    
    @OneToMany(mappedBy = "codRecibo")
    @JsonIgnore
    private List<Pagos> pagosList;

    public Recibos() {
    }

    public Recibos(Integer codRecibo) {
        this.codRecibo = codRecibo;
    }

    public Recibos(Integer codRecibo, String descripcion, int monto, boolean facturado, String montoTexto, Date fechaEmision) {
        this.codRecibo = codRecibo;
        this.descripcion = descripcion;
        this.monto = monto;
        this.facturado = facturado;
        this.montoTexto = montoTexto;
        this.fechaEmision = fechaEmision;
    }

    public Integer getCodRecibo() {
        return codRecibo;
    }

    public void setCodRecibo(Integer codRecibo) {
        this.codRecibo = codRecibo;
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

    public Facturas getFacturas() {
        return facturas;
    }

    public void setFacturas(Facturas facturas) {
        this.facturas = facturas;
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
        return "com.lawyersys.pclsystembacke.entities.Recibos[ codRecibo=" + codRecibo + " ]";
    }
    
}
