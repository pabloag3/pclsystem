package com.lawyersys.pclsystembacke.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author carlo
 */
@Entity
@Table(name = "usuarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuarios.findAll", query = "SELECT u FROM Usuarios u")
    , @NamedQuery(name = "Usuarios.findByCodUsuario", query = "SELECT u FROM Usuarios u WHERE u.codUsuario = :codUsuario")
    , @NamedQuery(name = "Usuarios.findByUsuario", query = "SELECT u FROM Usuarios u WHERE u.usuario = :usuario")
    , @NamedQuery(name = "Usuarios.findByContrasenha", query = "SELECT u FROM Usuarios u WHERE u.contrasenha = :contrasenha")
    , @NamedQuery(name = "Usuarios.findByCorreoElectronico", query = "SELECT u FROM Usuarios u WHERE u.correoElectronico = :correoElectronico")
    , @NamedQuery(name = "Usuarios.findByDescripcion", query = "SELECT u FROM Usuarios u WHERE u.descripcion = :descripcion")
    , @NamedQuery(name = "Usuarios.findByCodRol", query = "SELECT u FROM Usuarios u WHERE u.codRol = :codRol")
})
public class Usuarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cod_usuario")
    private Integer codUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "usuario")
    private String usuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "contrasenha")
    private String contrasenha;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "correo_electronico")
    private String correoElectronico;
    @Size(max = 100)
    @Column(name = "descripcion")
    private String descripcion;
    @JoinColumn(name = "cedula", referencedColumnName = "cedula")
    @ManyToOne(optional = false)
    private Empleados cedula;
    @JoinColumn(name = "cod_estado", referencedColumnName = "cod_estado")
    @ManyToOne(optional = false)
    private EstadosUsuarios codEstado;
    @JoinColumn(name = "cod_rol", referencedColumnName = "cod_rol")
    @ManyToOne(optional = false)
    private RolesUsuario codRol;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codUsuario")
    @JsonIgnore
    private List<Reportes> reportesList;

    public Usuarios() {
    }

    public Usuarios(Integer codUsuario) {
        this.codUsuario = codUsuario;
    }

    public Usuarios(Integer codUsuario, String usuario, String contrasenha, String correoElectronico) {
        this.codUsuario = codUsuario;
        this.usuario = usuario;
        this.contrasenha = contrasenha;
        this.correoElectronico = correoElectronico;
    }

    public Integer getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(Integer codUsuario) {
        this.codUsuario = codUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenha() {
        return contrasenha;
    }

    public void setContrasenha(String contrasenha) {
        this.contrasenha = contrasenha;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Empleados getCedula() {
        return cedula;
    }

    public void setCedula(Empleados cedula) {
        this.cedula = cedula;
    }

    public EstadosUsuarios getCodEstado() {
        return codEstado;
    }

    public void setCodEstado(EstadosUsuarios codEstado) {
        this.codEstado = codEstado;
    }

    public RolesUsuario getCodRol() {
        return codRol;
    }

    public void setCodRol(RolesUsuario codRol) {
        this.codRol = codRol;
    }

    @XmlTransient
    public List<Reportes> getReportesList() {
        return reportesList;
    }

    public void setReportesList(List<Reportes> reportesList) {
        this.reportesList = reportesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codUsuario != null ? codUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuarios)) {
            return false;
        }
        Usuarios other = (Usuarios) object;
        if ((this.codUsuario == null && other.codUsuario != null) || (this.codUsuario != null && !this.codUsuario.equals(other.codUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lawyersys.pclsystembacke.Usuarios[ codUsuario=" + codUsuario + " ]";
    }
    
}
