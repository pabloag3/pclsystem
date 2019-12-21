/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lawyersys.notificacion;

import java.util.Date;

/**
 *
 * @author carlo
 */
public class Actividad {

    String descripcion;
    String observacion;
    String cedula;
    String fecha;
    int diaNotificable;
    
   
    
    Actividad() {
        
    }

    public Actividad(String descripcion, String observacion, String cedula, String fecha, int diaNotificable) {
        this.descripcion = descripcion;
        this.observacion = observacion;
        this.cedula = cedula;
        this.fecha = fecha;
        this.diaNotificable = diaNotificable;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getDiaNotificable() {
        return diaNotificable;
    }

    public void setDiaNotificable(int diaNotificable) {
        this.diaNotificable = diaNotificable;
    }

    @Override
    public String toString() {
        return "Actividad{" + "descripcion=" + descripcion + ", observacion=" + observacion + ", cedula=" + cedula + ", fecha=" + fecha + ", diaNotificable=" + diaNotificable + '}';
    }

    

  
    
    
    
            
    
}
