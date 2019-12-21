package com.lawyersys.pclsystembe.rest.usuarios;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawyersys.pclsystembacke.entities.Empleados;
import com.lawyersys.pclsystembe.abm.ABMManagerUsuarios;
import com.lawyersys.pclsystembacke.entities.Usuarios;
import com.lawyersys.pclsystembe.utilidades.ErrorManager;
import com.lawyersys.pclsystembe.error.FaltaCargarElemento;
import com.lawyersys.pclsystembe.utilidades.Mail;
import com.lawyersys.pclsystembe.utilidades.RandomText;
import com.lawyersys.pclsystembe.utilidades.Seguridad;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author tatoa
 */
@Stateless
@Path("usuarios")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsuariosREST {
    
    @EJB
    private ABMManagerUsuarios abmManager;
    
    Mail mail = new Mail();

    public UsuariosREST() {
    }
    
    @POST
    @Path("guardar")
    public Response create(@RequestBody() String entity) throws IOException, FaltaCargarElemento{
        try {
            ObjectMapper mapper = new ObjectMapper();
            Usuarios elem = mapper.readValue(entity, Usuarios.class);
            
            // valida que el empleado exista en el sistema
            List<Empleados> empleadoAuxList = (List<Empleados>) (Object) abmManager.find("Empleados", elem.getCedula().getCedula());
            if (empleadoAuxList.isEmpty()) {
                throw new FaltaCargarElemento("Error. El empleado no existe.");
            }
            
            // valida que el nombre de usuario no exista en el sistema
            List<Usuarios> usuarioAuxList = (List<Usuarios>) (Object) abmManager.traerUsuarioPorNombreUsuario(elem.getUsuario());
            if (!usuarioAuxList.isEmpty()) {
                Usuarios usuarioAux = usuarioAuxList.get(0);
                if ( usuarioAux.getUsuario().contains(elem.getUsuario()) ) {
                    throw new FaltaCargarElemento("Error. Usuario ya existe.");
                }
            }
            
            
            if ( elem.getCedula() == null ) {
                throw new FaltaCargarElemento("Error. Cargar cedula.");
            }
            if ( elem.getUsuario() == null ) {
                throw new FaltaCargarElemento("Error. Cargar usuario.");
            }
            if ( elem.getContrasenha() == null ) {
                throw new FaltaCargarElemento("Error. Cargar contraseña.");
            }
            if ( elem.getCorreoElectronico() == null ) {
                throw new FaltaCargarElemento("Error. Cargar correo electrónico.");
            }
            elem.setContrasenha(Seguridad.getMd5(elem.getContrasenha()));
            abmManager.create(Usuarios.class, elem);
            return Response.ok().build();
        } catch (Exception e) {
            return ErrorManager.manejarError(e, Usuarios.class);
        }
    }

    @PUT
    @Path("actualizar/{id}")
    public Response edit(@RequestBody() String entity) throws IOException, FaltaCargarElemento {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Usuarios elem = mapper.readValue(entity, Usuarios.class);
            
             // valida que el empleado exista en el sistema
            List<Empleados> empleadoAuxList = (List<Empleados>) (Object) abmManager.find("Empleados", elem.getCedula().getCedula());
            if (empleadoAuxList.isEmpty()) {
                throw new FaltaCargarElemento("Error. El empleado no existe.");
            }
            
            if ( elem.getCedula() == null ) {
                throw new FaltaCargarElemento("Error. Cargar cedula.");
            }
            if ( elem.getUsuario() == null ) {
                throw new FaltaCargarElemento("Error. Cargar usuario.");
            }
            if ( elem.getContrasenha() == null ) {
                throw new FaltaCargarElemento("Error. Cargar contraseña.");
            }
            if ( elem.getCorreoElectronico() == null ) {
                throw new FaltaCargarElemento("Error. Cargar correo electrónico.");
            }
            
            List<Usuarios> list = (List<Usuarios>) (Object) abmManager.find("Usuarios", elem.getUsuario());
            Usuarios userAuxiliarBD = list.get(0);
            if (!elem.getContrasenha().equals(userAuxiliarBD.getContrasenha())) {
                elem.setContrasenha(Seguridad.getMd5(elem.getContrasenha()));
            }            
            abmManager.edit(Usuarios.class, elem);
            return Response.ok().build();
        } catch (Exception e) {
            return ErrorManager.manejarError(e, Usuarios.class);
        }
        
    }

    @GET
    @Path("traer/{id}")
    public Response traer(@PathParam("id") String id) throws JsonProcessingException {
        try {
            List<Usuarios> elem = (List<Usuarios>) (Object) abmManager.find("Usuarios", id);
            ObjectMapper mapper = new ObjectMapper();
            String resp = mapper.writeValueAsString(elem);
            return Response.ok(resp).build();
        } catch (Exception e) {
            return ErrorManager.manejarError(e, Usuarios.class);
        }
    }
    
    @GET
    @Path("traer-por-cedula/{cedula}")
    public Response traerUsuarioPorCedula(@PathParam("cedula") String cedula) throws JsonProcessingException {
        try {
            List<Usuarios> elem = (List<Usuarios>) (Object) abmManager.traerUsuarioPorCedula(cedula);
            ObjectMapper mapper = new ObjectMapper();
            String resp = mapper.writeValueAsString(elem);
            return Response.ok(resp).build();
        } catch (Exception e) {
            return ErrorManager.manejarError(e, Usuarios.class);
        }
    }


    @GET
    @Path("listar")
    public Response traerTodos() throws JsonProcessingException {
        try {
            List<Usuarios> elem = (List<Usuarios>) (Object) abmManager.findAll("Usuarios");
            ObjectMapper mapper = new ObjectMapper();
            String resp = mapper.writeValueAsString(elem);
            return Response.ok(resp).build();
        } catch (Exception e) {
            return ErrorManager.manejarError(e, Usuarios.class);
        }
 
    }
    
    @GET
    @Path("recuperar-contrasenha/{usuario}")
    public Response recuperarContrasenha(@PathParam("usuario") String usuarioARecuperar ) throws JsonProcessingException {
        try {
            List<Usuarios> elem = (List<Usuarios>) (Object) abmManager.traerUsuarioPorNombreUsuario(usuarioARecuperar);
            Usuarios usuario = elem.get(0);
            
            String nuevaContrasenha = RandomText.generateRandomString(8);
            usuario.setContrasenha(Seguridad.getMd5(nuevaContrasenha));
            
            abmManager.edit(Usuarios.class, usuario);
            
            mail.enviaStartTLS(usuario.getCorreoElectronico(), "Lawyersys - recuperacion de clave", 
                    "Su contrasenha nueva en el sistema sera: " + nuevaContrasenha);
            
            
            return Response.ok().build();
        } catch (Exception e) {
            return ErrorManager.manejarError(e, Usuarios.class);
        }
 
    }

}
