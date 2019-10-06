/*
 */
package com.lawyersys.pclsystembe.rest.expedientes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawyersys.pclsystembacke.entities.DetalleExpediente;
import com.lawyersys.pclsystembacke.entities.DetalleExpedientePK;
import com.lawyersys.pclsystembe.abm.ABMManagerExpedientes;
import com.lawyersys.pclsystembe.error.FaltaCargarElemento;
import com.lawyersys.pclsystembe.utilidades.ErrorManager;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import javax.ws.rs.core.PathSegment;
import javax.ws.rs.core.Response;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Pablo Aguilar
 */
@Stateless
@Path("detalleexpediente")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DetalleExpedienteFacadeREST {

    private DetalleExpedientePK getPrimaryKey(PathSegment pathSegment) {
        /*
         * pathSemgent represents a URI path segment and any associated matrix parameters.
         * URI path part is supposed to be in form of 'somePath;codDetalleExpediente=codDetalleExpedienteValue;codExpediente=codExpedienteValue'.
         * Here 'somePath' is a result of getPath() method invocation and
         * it is ignored in the following code.
         * Matrix parameters are used as field names to build a primary key instance.
         */
        com.lawyersys.pclsystembacke.entities.DetalleExpedientePK key = new com.lawyersys.pclsystembacke.entities.DetalleExpedientePK();
        javax.ws.rs.core.MultivaluedMap<String, String> map = pathSegment.getMatrixParameters();
        java.util.List<String> codDetalleExpediente = map.get("codDetalleExpediente");
        if (codDetalleExpediente != null && !codDetalleExpediente.isEmpty()) {
            key.setCodDetalleExpediente(new java.lang.Integer(codDetalleExpediente.get(0)));
        }
        java.util.List<String> codExpediente = map.get("codExpediente");
        if (codExpediente != null && !codExpediente.isEmpty()) {
            key.setCodExpediente(new java.lang.Integer(codExpediente.get(0)));
        }
        return key;
    }

    public DetalleExpedienteFacadeREST() {
    }

    @EJB
    private ABMManagerExpedientes abmManager;

    private static String UPLOADED_FOLDER = "C:\\pclSystemFiles\\detallesExpedienteArchivos\\";
    
    @POST
    @Path("guardar")
    public Response create(@RequestBody() String entity) throws IOException, FaltaCargarElemento {
        try {
            ObjectMapper mapper = new ObjectMapper();
            DetalleExpediente elem = mapper.readValue(entity, DetalleExpediente.class);   
            if ( elem.getDescripcion()== null ) {
                throw new FaltaCargarElemento("Error. Cargar descripcion.");
            }
            if ( elem.getFecha() == null ) {
                throw new FaltaCargarElemento("Error. Cargar fecha.");
            }
            if ( elem.getArchivo() == null ) {
                throw new FaltaCargarElemento("Error. Cargar archivo.");
            }
            abmManager.create(DetalleExpediente.class, elem);
            return Response.ok().build();
        } catch (Exception e) {
            return ErrorManager.manejarError(e, DetalleExpediente.class);
        }
    }
    
    @POST
    @Path("subir-archivo")
    public Response singleFileUpload(@RequestParam("file") MultipartFile file) {

        if (file.isEmpty()) {
            //redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return Response.ok("redirect:uploadStatus").build();
        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy.HH.mm.ss");  
        LocalDateTime now = LocalDateTime.now();
        try {
            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            java.nio.file.Path path = (java.nio.file.Path) Paths.get(UPLOADED_FOLDER + dtf.format(now) +file.getOriginalFilename());
            Files.write(path, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String mensaje = UPLOADED_FOLDER + dtf.format(now) +file.getOriginalFilename();
        
        return Response.ok(mensaje).build();
    }
    

    @PUT
    @Path("actualizar/{id}")
    public Response edit(@RequestBody() String entity) throws IOException, FaltaCargarElemento {
        try {
            ObjectMapper mapper = new ObjectMapper();
            DetalleExpediente elem = mapper.readValue(entity, DetalleExpediente.class);  
            if ( elem.getDescripcion()== null ) {
                throw new FaltaCargarElemento("Error. Cargar descripcion.");
            }
            if ( elem.getFecha() == null ) {
                throw new FaltaCargarElemento("Error. Cargar fecha.");
            }
            if ( elem.getArchivo() == null ) {
                throw new FaltaCargarElemento("Error. Cargar archivo.");
            }
            abmManager.edit(DetalleExpediente.class, elem);
            return Response.ok().build();
        } catch (Exception e) {
            return ErrorManager.manejarError(e, DetalleExpediente.class);
        }
    }

    @GET
    @Path("traer/{id}")
    public Response find(@PathParam("id") String id) throws JsonProcessingException {
        try {
            List<DetalleExpediente> elem = (List<DetalleExpediente>) (Object) abmManager.find("DetalleExpediente", id);
            ObjectMapper mapper = new ObjectMapper();
            String resp = mapper.writeValueAsString(elem);
            return Response.ok(resp).build();
        } catch (Exception e) {
            return ErrorManager.manejarError(e, DetalleExpediente.class);
        }
    }

    @GET
    @Path("listar")
    public Response findAll() throws JsonProcessingException {
        try {
            List<DetalleExpediente> elem = (List<DetalleExpediente>) (Object) abmManager.findAll("DetalleExpediente");
            ObjectMapper mapper = new ObjectMapper();
            String resp = mapper.writeValueAsString(elem);
            return Response.ok(resp).build();
        } catch (Exception e) {
            return ErrorManager.manejarError(e, DetalleExpediente.class);
        }
    }
    
    
}
