package com.lawyersys.pclsystembacke.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "empleados")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Empleados.findAll", query = "SELECT e FROM Empleados e")
    , @NamedQuery(name = "Empleados.findByCedula", query = "SELECT e FROM Empleados e WHERE e.cedula = :cedula")
    , @NamedQuery(name = "Empleados.findByNombre", query = "SELECT e FROM Empleados e WHERE e.nombre = :nombre")
    , @NamedQuery(name = "Empleados.findByRuc", query = "SELECT e FROM Empleados e WHERE e.ruc = :ruc")
    , @NamedQuery(name = "Empleados.findByApellido", query = "SELECT e FROM Empleados e WHERE e.apellido = :apellido")
    , @NamedQuery(name = "Empleados.findByFechaNacimiento", query = "SELECT e FROM Empleados e WHERE e.fechaNacimiento = :fechaNacimiento")
    , @NamedQuery(name = "Empleados.findByTelefono", query = "SELECT e FROM Empleados e WHERE e.telefono = :telefono")
    , @NamedQuery(name = "Empleados.findByDireccion", query = "SELECT e FROM Empleados e WHERE e.direccion = :direccion")})

public class Empleados implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "cedula")
    private String cedula;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "nombre")
    private String nombre;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "ruc")
    private String ruc;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "apellido")
    private String apellido;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "telefono")
    private String telefono;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "direccion")
    private String direccion;
    
    @JoinColumn(name = "cod_estado", referencedColumnName = "cod_estado")
    @ManyToOne(optional = false)
    private EstadosEmpleados codEstado;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cedulaEmisor")
    @JsonIgnore
    private List<Facturas> facturasList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cedulaResponsable")
    @JsonIgnore
    private List<Actividades> actividadesList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cedula")
    @JsonIgnore
    private List<Usuarios> usuariosList;

    public Empleados() {
    }

    public Empleados(String cedula) {
        this.cedula = cedula;
    }

    public Empleados(String cedula, String nombre, String ruc, String apellido, Date fechaNacimiento, String telefono, String direccion) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.ruc = ruc;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @XmlTransient
    public List<Facturas> getFacturasList() {
        return facturasList;
    }

    public void setFacturasList(List<Facturas> facturasList) {
        this.facturasList = facturasList;
    }

    @XmlTransient
    public List<Actividades> getActividadesList() {
        return actividadesList;
    }

    public void setActividadesList(List<Actividades> actividadesList) {
        this.actividadesList = actividadesList;
    }

    @XmlTransient
    public List<Usuarios> getUsuariosList() {
        return usuariosList;
    }

    public void setUsuariosList(List<Usuarios> usuariosList) {
        this.usuariosList = usuariosList;
    }
    
    public EstadosEmpleados getCodEstado() {
        return codEstado;
    }

    public void setCodEstado(EstadosEmpleados codEstado) {
        this.codEstado = codEstado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cedula != null ? cedula.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empleados)) {
            return false;
        }
        Empleados other = (Empleados) object;
        if ((this.cedula == null && other.cedula != null) || (this.cedula != null && !this.cedula.equals(other.cedula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lawyersys.pclsystembacke.Empleados[ cedula=" + cedula + " ]";
    }
    
}
