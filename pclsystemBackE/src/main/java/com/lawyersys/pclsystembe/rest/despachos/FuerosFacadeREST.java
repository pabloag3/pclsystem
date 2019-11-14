package com.lawyersys.pclsystembe.rest.despachos;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawyersys.pclsystembacke.entities.Fueros;
import com.lawyersys.pclsystembe.abm.ABMManagerDespachos;
import com.lawyersys.pclsystembe.error.FaltaCargarElemento;
import com.lawyersys.pclsystembe.utilidades.ErrorManager;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author Pablo Aguilar
 */
@Stateless
@Path("fueros")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FuerosFacadeREST {

    public FuerosFacadeREST() {
    }

    @EJB
    private ABMManagerDespachos abmManager;

    @POST
    @Path("guardar")
    public Response create(@RequestBody() String entity) throws IOException, FaltaCargarElemento {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Fueros elem = mapper.readValue(entity, Fueros.class);   
            if ( elem.getTipoFuero()== null ) {
                throw new FaltaCargarElemento("Error. Cargar descripcion.");
            }
            abmManager.create(Fueros.class, elem);
            return Response.ok().build();
        } catch (Exception e) {
            return ErrorManager.manejarError(e, Fueros.class);
        }
    }

    @PUT
    @Path("actualizar/{id}")
    public Response edit(@RequestBody() String entity) throws IOException, FaltaCargarElemento {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Fueros elem = mapper.readValue(entity, Fueros.class);
            if ( elem.getTipoFuero()== null ) {
                throw new FaltaCargarElemento("Error. Cargar descripcion.");
            }
            abmManager.edit(Fueros.class, elem);
            return Response.ok().build();
        } catch (Exception e) {
            return ErrorManager.manejarError(e, Fueros.class);
        }
    }

    @GET
    @Path("traer/{id}")
    public Response find(@PathParam("id") String id) throws JsonProcessingException {
        try {
            List<Fueros> elem = (List<Fueros>) (Object) abmManager.find("Fueros", id);
            ObjectMapper mapper = new ObjectMapper();
            String resp = mapper.writeValueAsString(elem);
            return Response.ok(resp).build();
        } catch (Exception e) {
            return ErrorManager.manejarError(e, Fueros.class);
        }
    }

    @GET
    @Path("listar")
    public Response findAll() throws JsonProcessingException {
        try {
            List<Fueros> elem = (List<Fueros>) (Object) abmManager.findAll("Fueros");
            ObjectMapper mapper = new ObjectMapper();
            String resp = mapper.writeValueAsString(elem);
            return Response.ok(resp).build();
        } catch (Exception e) {
            return ErrorManager.manejarError(e, Fueros.class);
        }
    }
    
    @GET
    @Path("/reporte-monto-por-fuero/{formato}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response traerReporteClientesACobrar(
            @PathParam("formato") String formato
    ) throws JRException, ClassNotFoundException, SQLException, IOException {
        
        try {
            
            if (!formato.equals("pdf") && !formato.equals("docx") && !formato.equals("xls")) {
                throw new FaltaCargarElemento("Error. El formato de archivo no es aceptado.");
            }
            
            // EMPIEZA A GENERAR EL ARCHIVO DEL REPORTE
            // compilar el archivo del reporte jasper
            String sourceFileName = "C:\\pclSystemFiles\\jasperFiles\\reporte\\MontoPorFuero.jrxml";
            String jasperReport =  JasperCompileManager.compileReportToFile(sourceFileName);
            
            // mapea los parametros que pasara al archivo jasper
            Map parameters = new HashMap();
            
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
                fileName = "C:\\pclSystemFiles\\jasperFiles\\reporte\\montoPorFuero.pdf";
                writeFile(pdfBytes, fileName);
                
            } else if (formato.equals("docx")) {
                
                JRDocxExporter exporter = new JRDocxExporter();
                exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
                exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
                        "C:\\pclSystemFiles\\jasperFiles\\reporte\\montoPorFuero.docx");
                exporter.exportReport();
                
                File file = new File("C:\\pclSystemFiles\\jasperFiles\\reporte\\montoPorFuero.docx");
                byte[] fileContent = Files.readAllBytes(file.toPath());
                fileName = "C:\\pclSystemFiles\\jasperFiles\\reporte\\montoPorFuero.docx";
                writeFile(fileContent, fileName);
                
            }
            
            response = Response.ok((Object) new File(fileName));
            response.header("Content-Disposition", "attachment;filename=" + fileName);
            return response.build();
            
        } catch (Exception e) {
            return ErrorManager.manejarError(e, Fueros.class);
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
