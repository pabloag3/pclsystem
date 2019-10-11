/*
 */
package com.lawyersys.pclsystembe.rest.expedientes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawyersys.pclsystembacke.entities.DocumentosEntregados;
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
import javax.ws.rs.core.Response;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Pablo Aguilar
 */
@Stateless
@Path("documentosentregados")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DocumentosEntregadosFacadeREST {


    public DocumentosEntregadosFacadeREST() {
    }

    @EJB
    private ABMManagerExpedientes abmManager;

    private static String UPLOADED_FOLDER = "C:\\pclSystemFiles\\documentosEntregados\\";
    
    @POST
    @Path("guardar")
    public Response create(@RequestBody() String entity) throws IOException, FaltaCargarElemento {
        try {
            ObjectMapper mapper = new ObjectMapper();
            DocumentosEntregados elem = mapper.readValue(entity, DocumentosEntregados.class);   
            if ( elem.getDescripcion()== null ) {
                throw new FaltaCargarElemento("Error. Cargar descripcion.");
            }
            abmManager.create(DocumentosEntregados.class, elem);
            return Response.ok().build();
        } catch (Exception e) {
            return ErrorManager.manejarError(e, DocumentosEntregados.class);
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
            DocumentosEntregados elem = mapper.readValue(entity, DocumentosEntregados.class);
            if ( elem.getDescripcion()== null ) {
                throw new FaltaCargarElemento("Error. Cargar descripcion.");
            }
            abmManager.edit(DocumentosEntregados.class, elem);
            return Response.ok().build();
        } catch (Exception e) {
            return ErrorManager.manejarError(e, DocumentosEntregados.class);
        }
    }

    @GET
    @Path("traer-por-cliente/{id}")
    public Response find(@PathParam("id") String id) throws JsonProcessingException {
        try {
            List<DocumentosEntregados> elem = (List<DocumentosEntregados>) (Object) abmManager.find("DocumentosEntregados", id);
            ObjectMapper mapper = new ObjectMapper();
            String resp = mapper.writeValueAsString(elem);
            return Response.ok(resp).build();
        } catch (Exception e) {
            return ErrorManager.manejarError(e, DocumentosEntregados.class);
        }
    }
    
    @GET
    @Path("traer-documentos-por-cliente/{id}")
    public Response traerDocumentosPorCliente(@PathParam("id") String id) throws JsonProcessingException {
        try {
            List<DocumentosEntregados> elem = (List<DocumentosEntregados>) (Object) abmManager.findDocumentosPorCliente(id);
            ObjectMapper mapper = new ObjectMapper();
            String resp = mapper.writeValueAsString(elem);
            return Response.ok(resp).build();
        } catch (Exception e) {
            return ErrorManager.manejarError(e, DocumentosEntregados.class);
        }
    }

    @GET
    @Path("listar")
    public Response findAll() throws JsonProcessingException {
        try {
            List<DocumentosEntregados> elem = (List<DocumentosEntregados>) (Object) abmManager.findAll("DocumentosEntregados");
            ObjectMapper mapper = new ObjectMapper();
            String resp = mapper.writeValueAsString(elem);
            return Response.ok(resp).build();
        } catch (Exception e) {
            return ErrorManager.manejarError(e, DocumentosEntregados.class);
        }
    }
    
}
