/*
 */
package com.lawyersys.pclsystembe.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tatoa
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RolesPermisosDTO {
    
    private int codRol;
    private String descripcion;
    private ArrayList<Integer> permisos;

    public RolesPermisosDTO() {
    }
    
    public int getCodRol() {
        return codRol;
    }

    public void setCodRol(int codRol) {
        this.codRol = codRol;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public ArrayList<Integer> getPermisos() {
        return permisos;
    }

    public void setCodigosPermisos(ArrayList<Integer> permisos) {
        this.permisos = permisos;
    }

    @Override
    public String toString() {
        return "RolesPermisosDTO{" + "codRol=" + codRol + ", descripcion=" + descripcion + ", permisos=" + permisos + '}';
    }

    
}
