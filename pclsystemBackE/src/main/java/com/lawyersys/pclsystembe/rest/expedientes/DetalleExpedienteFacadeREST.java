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
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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
import javax.ws.rs.core.PathSegment;
import javax.ws.rs.core.Response;
import org.apache.commons.io.IOUtils;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.springframework.web.bind.annotation.RequestBody;

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
            if ( elem.getCodTipoActuacion().getCodTipoActuacion() == null ) {
                throw new FaltaCargarElemento("Error. Cargar tipo de actuacion.");
            }
            elem.setFecha(new Date());
            abmManager.create(DetalleExpediente.class, elem);
            return Response.ok().build();
        } catch (Exception e) {
            return ErrorManager.manejarError(e, DetalleExpediente.class);
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
            Response.ResponseBuilder response = Response.ok((Object) fileDownload);
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
//            DetalleExpediente deAux = null;
            
//            List<DetalleExpediente> listaAuxiliar = null;
            List<DetalleExpediente> elem = (List<DetalleExpediente>) (Object) abmManager.find("DetalleExpediente", id);
            
//            for (int i = 0; i < elem.size(); i++) {
//                DetalleExpedientePK dePKaux = new DetalleExpedientePK(elem.get(i).getDetalleExpedientePK().getCodDetalleExpediente(),
//                        elem.get(i).getDetalleExpedientePK().getCodExpediente());
//                deAux.setDetalleExpedientePK(dePKaux);
//                deAux.setDescripcion(elem.get(i).getDescripcion());
//                deAux.setFecha(elem.get(i).getFecha());
//                deAux.setArchivo(elem.get(i).getArchivo());
//                deAux.setCodTipoActuacion(elem.get(i).getCodTipoActuacion());
//                deAux.setCodDespacho(elem.get(i).getCodDespacho());
//                deAux.setCodJuez(elem.get(i).getCodJuez());
//                deAux.setCodActuario(elem.get(i).getCodActuario());
//                deAux.setCodUjier(elem.get(i).getCodUjier());
//                listaAuxiliar.add(deAux);
//            }
            
            ObjectMapper mapper = new ObjectMapper();
            String resp = mapper.writeValueAsString(elem);
            return Response.ok(resp).build();
        } catch (Exception e) {
            return ErrorManager.manejarError(e, DetalleExpediente.class);
        }
    }

    @GET
    @Path("listar-por-expediente/{id}")
    public Response findByExpediente(@PathParam("id") String id) throws JsonProcessingException {
        try {
            List<DetalleExpediente> elem = (List<DetalleExpediente>) (Object) abmManager.traerDetallesDeExpedientePorExpediente(id);
            ObjectMapper mapper = new ObjectMapper();
            String resp = mapper.writeValueAsString(elem);
            return Response.ok(resp).build();
        } catch (Exception e) {
            return ErrorManager.manejarError(e, DetalleExpediente.class);
        }
    }
    
    
}
