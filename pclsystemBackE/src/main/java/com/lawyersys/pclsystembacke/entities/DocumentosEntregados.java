/*
 */
package com.lawyersys.pclsystembacke.entities;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Pablo Aguilar
 */
@Entity
@Table(name = "documentos_entregados")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DocumentosEntregados.findAll", query = "SELECT d FROM DocumentosEntregados d")
    , @NamedQuery(name = "DocumentosEntregados.findByCodDocumento", query = "SELECT d FROM DocumentosEntregados d WHERE d.codDocumento = :codDocumento")
    , @NamedQuery(name = "DocumentosEntregados.findByDescripcion", query = "SELECT d FROM DocumentosEntregados d WHERE d.descripcion = :descripcion")
    , @NamedQuery(name = "DocumentosEntregados.findByCodCliente", query = "SELECT d FROM DocumentosEntregados d WHERE d.codCliente.codCliente = :codCliente")
    , @NamedQuery(name = "DocumentosEntregados.findByArchivo", query = "SELECT d FROM DocumentosEntregados d WHERE d.archivo = :archivo")
})
public class DocumentosEntregados implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cod_documento")
    private Integer codDocumento;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "descripcion")
    private String descripcion;
    
    @Size(max = 2147483647)
    @Column(name = "archivo")
    private String archivo;
    
    @JoinColumn(name = "cod_cliente", referencedColumnName = "cod_cliente")
    @ManyToOne(optional = false)
    private Clientes codCliente;

    public DocumentosEntregados() {
    }

    public DocumentosEntregados(Integer codDocumento) {
        this.codDocumento = codDocumento;
    }

    public DocumentosEntregados(Integer codDocumento, String descripcion) {
        this.codDocumento = codDocumento;
        this.descripcion = descripcion;
    }

    public Integer getCodDocumento() {
        return codDocumento;
    }

    public void setCodDocumento(Integer codDocumento) {
        this.codDocumento = codDocumento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public Clientes getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(Clientes codCliente) {
        this.codCliente = codCliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codDocumento != null ? codDocumento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DocumentosEntregados)) {
            return false;
        }
        DocumentosEntregados other = (DocumentosEntregados) object;
        if ((this.codDocumento == null && other.codDocumento != null) || (this.codDocumento != null && !this.codDocumento.equals(other.codDocumento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lawyersys.pclsystembacke.entities.DocumentosEntregados[ codDocumento=" + codDocumento + " ]";
    }
    
}
