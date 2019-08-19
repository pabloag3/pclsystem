/*
 */
package com.lawyersys.pclsystembe.rest.config;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author tatoa
 */
@javax.ws.rs.ApplicationPath("pclsystem")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.lawyersys.pclsystembe.rest.actividades.ActividadesFacadeREST.class);
        resources.add(com.lawyersys.pclsystembe.rest.actividades.EstadosActividadesFacadeREST.class);
        resources.add(com.lawyersys.pclsystembe.rest.actividades.TiposActividadesFacadeREST.class);
        resources.add(com.lawyersys.pclsystembe.rest.despachos.ActuariosFacadeREST.class);
        resources.add(com.lawyersys.pclsystembe.rest.despachos.CiudadesFacadeREST.class);
        resources.add(com.lawyersys.pclsystembe.rest.despachos.DepartamentosFacadeREST.class);
        resources.add(com.lawyersys.pclsystembe.rest.despachos.DespachosFacadeREST.class);
        resources.add(com.lawyersys.pclsystembe.rest.despachos.FuerosFacadeREST.class);
        resources.add(com.lawyersys.pclsystembe.rest.despachos.JuecesFacadeREST.class);
        resources.add(com.lawyersys.pclsystembe.rest.despachos.UjieresFacadeREST.class);
        resources.add(com.lawyersys.pclsystembe.rest.expedientes.CasosFacadeREST.class);
        resources.add(com.lawyersys.pclsystembe.rest.expedientes.ClientesFacadeREST.class);
        resources.add(com.lawyersys.pclsystembe.rest.expedientes.DetalleExpedienteFacadeREST.class);
        resources.add(com.lawyersys.pclsystembe.rest.expedientes.EstadosCasoFacadeREST.class);
        resources.add(com.lawyersys.pclsystembe.rest.expedientes.ExpedientesFacadeREST.class);
        resources.add(com.lawyersys.pclsystembe.rest.expedientes.TiposActuacionesFacadeREST.class);
        resources.add(com.lawyersys.pclsystembe.rest.usuarios.EmpleadosREST.class);
        resources.add(com.lawyersys.pclsystembe.rest.usuarios.EstadosUsuariosREST.class);
        resources.add(com.lawyersys.pclsystembe.rest.usuarios.PermisosREST.class);
        resources.add(com.lawyersys.pclsystembe.rest.usuarios.RolesUsuarioREST.class);
        resources.add(com.lawyersys.pclsystembe.rest.usuarios.UsuariosREST.class);
    }
    
}
