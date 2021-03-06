package com.lawyersys.pclsystembe.rest.expedientes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawyersys.pclsystembacke.entities.Casos;
import com.lawyersys.pclsystembacke.entities.Departamentos;
import com.lawyersys.pclsystembe.abm.ABMManagerDespachos;
import com.lawyersys.pclsystembe.abm.ABMManagerExpedientes;
import com.lawyersys.pclsystembe.error.FaltaCargarElemento;
import com.lawyersys.pclsystembe.utilidades.ErrorManager;
import com.lawyersys.pclsystembe.utilidades.Log;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author Pablo Aguilar
 */
@Stateless
@Path("casos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CasosFacadeREST {

    public CasosFacadeREST() {
    }

    @EJB
    private ABMManagerExpedientes abmManager;
    
    @EJB
    private ABMManagerDespachos abmManagerDespachos;

    @POST
    @Path("guardar/{username}")
    public Response create(@PathParam("username") String username,
            @RequestBody() String entity) throws IOException, FaltaCargarElemento {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Casos elem = mapper.readValue(entity, Casos.class);   
            if ( elem.getDescripcion()== null ) {
                throw new FaltaCargarElemento("Error. Cargar descripcion.");
            }
            abmManager.create(Casos.class, elem);
            Log.escribir("INFORMACION", username + " Accion: Crear caso: " + elem.getDescripcion());
            return Response.ok().build();
        } catch (Exception e) {
            return ErrorManager.manejarError(e, Casos.class);
        }
    }

    @PUT
    @Path("actualizar/{id}/{username}")
    public Response edit(@PathParam("username") String username,
            @RequestBody() String entity) throws IOException, FaltaCargarElemento {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Casos elem = mapper.readValue(entity, Casos.class);
            if ( elem.getDescripcion()== null ) {
                throw new FaltaCargarElemento("Error. Cargar descripcion.");
            }
            abmManager.edit(Casos.class, elem);
            Log.escribir("INFORMACION", username + " Accion: Modificar caso: " + elem.getCodCaso());
            return Response.ok().build();
        } catch (Exception e) {
            return ErrorManager.manejarError(e, Casos.class);
        }
    }

    @GET
    @Path("traer/{id}")
    public Response find(@PathParam("id") String id) throws JsonProcessingException {
        try {
            List<Casos> elem = (List<Casos>) (Object) abmManager.find("Casos", id);
            ObjectMapper mapper = new ObjectMapper();
            String resp = mapper.writeValueAsString(elem);
            return Response.ok(resp).build();
        } catch (Exception e) {
            return ErrorManager.manejarError(e, Casos.class);
        }
    }

    @GET
    @Path("listar")
    public Response findAll() throws JsonProcessingException {
        try {
            List<Casos> elem = (List<Casos>) (Object) abmManager.findAll("Casos");
            ObjectMapper mapper = new ObjectMapper();
            String resp = mapper.writeValueAsString(elem);
            return Response.ok(resp).build();
        } catch (Exception e) {
            return ErrorManager.manejarError(e, Casos.class);
        }
    }
    
    @GET
    @Path("/archivo-reporte-casos-por-jurisdiccion/{codJurisdiccion}/{formato}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response traerReporteCasosPorJurisdiccion(
            @PathParam("codJurisdiccion") String codJurisdiccion,
            @PathParam("formato") String formato
    ) throws JRException, ClassNotFoundException, SQLException, IOException {        
        
        try {
            
            if (!formato.equals("pdf") && !formato.equals("docx") && !formato.equals("xlsx")) {
                throw new FaltaCargarElemento("Error. El formato de archivo no es aceptado.");
            }
            
            // EMPIEZA A GENERAR EL ARCHIVO DE LA FACTURA
            // compilar el archivo del reporte jasper
            String sourceFileName = "C:\\pclSystemFiles\\jasperFiles\\reporte\\CasosPorJurisdiccion.jrxml";
            String jasperReport =  JasperCompileManager.compileReportToFile(sourceFileName);
            
            // verificamos la existencia de la jurisdiccion
            List<Departamentos> departamentoAux = (List<Departamentos>) (Object) abmManagerDespachos.find("Departamentos", codJurisdiccion);
            if (departamentoAux.isEmpty()) {
                throw new FaltaCargarElemento("Error. La jurisdiccion no existe.");
            }
            
            // mapea los parametros que pasara al archivo jasper
            Map parameters = new HashMap();
            parameters.put("v_cod_jurisdiccion", Integer.parseInt(codJurisdiccion));
            
            // establece la conexion a la base de datos
            String cadenaConexion = "jdbc:postgresql://localhost:5432/lawyersys";
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(cadenaConexion, "postgres", "postgres");
            
            //Aqui se llena el reporte (se ejecuta la consulta)
            JasperPrint print = new JasperPrint();
            print = JasperFillManager.fillReport(jasperReport, parameters, connection);
            
            ResponseBuilder response = null;
            String fileName = "";
            
            if (formato.equals("pdf")) {
                
                byte[] pdfBytes = JasperExportManager.exportReportToPdf(print);
                fileName = "C:\\pclSystemFiles\\jasperFiles\\reporte\\casosPorJurisdiccion.pdf";
                writeFile(pdfBytes, fileName);
                
            } else if (formato.equals("docx")) {
                
                fileName = "C:\\pclSystemFiles\\jasperFiles\\reporte\\casosPorJurisdiccion.docx";
                
                JRDocxExporter exporter = new JRDocxExporter();
                exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
                exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, fileName);
                exporter.exportReport();
                
                File file = new File(fileName);
                byte[] fileContent = Files.readAllBytes(file.toPath());
                writeFile(fileContent, fileName);
                
            } else if (formato.equals("xlsx")) {
                
                fileName = "C:\\pclSystemFiles\\jasperFiles\\reporte\\casosPorJurisdiccion.xlsx";
                
                SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
                configuration.setOnePagePerSheet(true);
                configuration.setIgnoreGraphics(false);
                
                try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    OutputStream fileOutputStream = new FileOutputStream(fileName)) {
                    Exporter exporter = new JRXlsxExporter();
                    exporter.setExporterInput(new SimpleExporterInput(print));
                    exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(byteArrayOutputStream));
                    exporter.setConfiguration(configuration);
                    exporter.exportReport();
                    byteArrayOutputStream.writeTo(fileOutputStream);
                }
                
                File file = new File(fileName);
                byte[] fileContent = Files.readAllBytes(file.toPath());
                fileName = fileName;
                writeFile(fileContent, fileName);
                
            }
            
            response = Response.ok((Object) new File(fileName));
            response.header("Content-Disposition", "attachment;filename=" + fileName);
            return response.build();

        } catch (Exception e) {
            return ErrorManager.manejarError(e, Casos.class);
        }

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
    
}
