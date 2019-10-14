/*
 */
package com.lawyersys.pclsystembe.rest.expedientes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawyersys.pclsystembacke.entities.DocumentosEntregados;
import com.lawyersys.pclsystembe.abm.ABMManagerExpedientes;
import com.lawyersys.pclsystembe.error.FaltaCargarElemento;
import com.lawyersys.pclsystembe.utilidades.ErrorManager;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.RequestBody;

import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.jboss.resteasy.spi.UnhandledException;

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
    @Path("/subir-archivo")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFile(MultipartFormDataInput input) {

        String fileName = "";

        Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
        List<InputPart> inputParts = uploadForm.get("file");

        for (InputPart inputPart : inputParts) {

            try {

                MultivaluedMap<String, String> header = inputPart.getHeaders();
                fileName = getFileName(header);

                //convert the uploaded file to inputstream
                InputStream inputStream = inputPart.getBody(InputStream.class,null);

                byte [] bytes = IOUtils.toByteArray(inputStream);

                //constructs upload file path
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy.HH.mm.ss");
                LocalDateTime now = LocalDateTime.now();
                
                fileName = UPLOADED_FOLDER + dtf.format(now) + fileName;

                writeFile(bytes,fileName);

                System.out.println("fileName");

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return Response.ok(fileName).build();

    }

    /**
     * header sample
     * {
     * 	Content-Type=[image/png], 
     * 	Content-Disposition=[form-data; name="file"; filename="filename.extension"]
     * }
     **/
    //get uploaded filename, is there a easy way in RESTEasy?
    private String getFileName(MultivaluedMap<String, String> header) {

        String[] contentDisposition = header.getFirst("Content-Disposition").split(";");

        for (String filename : contentDisposition) {
                if ((filename.trim().startsWith("filename"))) {

                        String[] name = filename.split("=");

                        String finalFileName = name[1].trim().replaceAll("\"", "");
                        return finalFileName;
                }
        }
        
        return "unknown";
    }

    //save to somewhere
    private void writeFile(byte[] content, String filename) throws IOException {

        File file = new File(filename);

        if (!file.exists()) {
                file.createNewFile();
        }

        FileOutputStream fop = new FileOutputStream(file);

        fop.write(content);
        fop.flush();
        fop.close();

    }
    
    @GET
    @Path("/download")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response downloadFileWithGet(@QueryParam("file") String file) {
        
        try {
            File fileDownload = new File(file);
            ResponseBuilder response = Response.ok((Object) fileDownload);
            response.header("Content-Disposition", "attachment;filename=" + file);
            return response.build();
        } catch (NullPointerException e) {
            return Response.noContent().build();
        }

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
