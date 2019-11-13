package com.lawyersys.pclsystembe.rest.actividades;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.lawyersys.pclsystembacke.entities.Actividades;
import com.lawyersys.pclsystembe.abm.ABMManagerActividades;
import com.lawyersys.pclsystembe.error.FaltaCargarElemento;
import com.lawyersys.pclsystembe.utilidades.ErrorManager;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author tatoa
 */
@Stateless
@Path("actividades")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ActividadesFacadeREST {

    public ActividadesFacadeREST() {
    }
    
    @EJB
    private ABMManagerActividades abmManager;

    @POST
    @Path("guardar")
    public Response create(@RequestBody() String entity) throws IOException, FaltaCargarElemento {
        try {
             ObjectMapper mapper = new ObjectMapper();
            Actividades elem = mapper.readValue(entity, Actividades.class);   
            if ( elem.getDescripcion()== null ) {
                throw new FaltaCargarElemento("Error. Cargar descripcion.");
            }
            if ( elem.getFecha()== null ) {
                throw new FaltaCargarElemento("Error. Cargar fecha.");
            }
            abmManager.create(Actividades.class, elem);
            return Response.ok().build();
        } catch (Exception e) {
            return ErrorManager.manejarError(e, Actividades.class);
        }
    }

    @PUT
    @Path("actualizar/{id}")
    public Response edit(@RequestBody() String entity) throws IOException, FaltaCargarElemento {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Actividades elem = mapper.readValue(entity, Actividades.class);  
            if ( elem.getDescripcion()== null ) {
                throw new FaltaCargarElemento("Error. Cargar descripcion.");
            }
            if ( elem.getFecha()== null ) {
                throw new FaltaCargarElemento("Error. Cargar fecha.");
            }
            abmManager.edit(Actividades.class, elem);
            return Response.ok().build();
        } catch (Exception e) {
            return ErrorManager.manejarError(e, Actividades.class);
        }
    }

    @GET
    @Path("traer/{id}")
    public Response find(@PathParam("id") String id) throws JsonProcessingException {
        try {
            List<Actividades> elem = (List<Actividades>) (Object) abmManager.find("Actividades", id);
            ObjectMapper mapper = new ObjectMapper();
            String resp = mapper.writeValueAsString(elem);
            return Response.ok(resp).build();
        } catch (Exception e) {
            return ErrorManager.manejarError(e, Actividades.class);
        }
    }
    
    @GET
    @Path("traer-pendientes-caducado-del-empleado/{id}")
    public Response traerPendienteCaducadoDelEmpleado (@PathParam("id") String id) throws JsonProcessingException {
        try {
            List<Actividades> elem = (List<Actividades>) (Object) abmManager.traerPendienteCaducadoDelEmpleado(id);
            ObjectMapper mapper = new ObjectMapper();
            String resp = mapper.writeValueAsString(elem);
            return Response.ok(resp).build();
        } catch (Exception e) {
            return ErrorManager.manejarError(e, Actividades.class);
        }
    }
    
    @GET
    @Path("traer-pendientes-caducados")
    public Response traerPendientesCaducados () throws JsonProcessingException {
        try {
            List<Actividades> elem = (List<Actividades>) (Object) abmManager.traerPendientesCaducados();
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            String resp = mapper.writeValueAsString(elem);
            return Response.ok(resp).build();
        } catch (Exception e) {
            return ErrorManager.manejarError(e, Actividades.class);
        }
    }

    @GET
    @Path("listar")
    public Response findAll() throws JsonProcessingException {
        try {
            List<Actividades> elem = (List<Actividades>) (Object) abmManager.findAll("Actividades");
            ObjectMapper mapper = new ObjectMapper();
            String resp = mapper.writeValueAsString(elem);
            return Response.ok(resp).build();
        } catch (Exception e) {
            return ErrorManager.manejarError(e, Actividades.class);
        }
    }
    
    @GET
    @Path("/reporte-actividades-a-vencer")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response traerActividadesAVencer() throws JRException, ClassNotFoundException, SQLException, IOException {
        
        try {
            
            // EMPIEZA A GENERAR EL ARCHIVO DEL REPORTE
            // compilar el archivo del reporte jasper
            String sourceFileName = "C:\\pclSystemFiles\\jasperFiles\\reporte\\ActividadesQueVencenEn30Dias.jrxml";
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
            byte[] pdfBytes = JasperExportManager.exportReportToPdf(print);

            String fileName = "C:\\pclSystemFiles\\jasperFiles\\reporte\\actividadesAVencer" + ".pdf";
            writeFile(pdfBytes, fileName);
            
            ResponseBuilder response = Response.ok((Object) new File(fileName));
            response.header("Content-Disposition", "attachment;filename=" + fileName);
            return response.build();
        } catch (Exception e) {
            return ErrorManager.manejarError(e, Actividades.class);
        }

    }
    
    @GET
    @Path("/reporte-presentacion-notificacion-a-vencer")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response traerPresentacionesNotificacionesAVencer() throws JRException, ClassNotFoundException, SQLException, IOException {
        
        try {
            
            // EMPIEZA A GENERAR EL ARCHIVO DEL REPORTE
            // compilar el archivo del reporte jasper
            String sourceFileName = "C:\\pclSystemFiles\\jasperFiles\\reporte\\PresentacionNotificacionAVencer.jrxml";
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
            byte[] pdfBytes = JasperExportManager.exportReportToPdf(print);

            String fileName = "C:\\pclSystemFiles\\jasperFiles\\reporte\\PresentacionNotificacionAVencer" + ".pdf";
            writeFile(pdfBytes, fileName);
            
            ResponseBuilder response = Response.ok((Object) new File(fileName));
            response.header("Content-Disposition", "attachment;filename=" + fileName);
            return response.build();
        } catch (Exception e) {
            return ErrorManager.manejarError(e, Actividades.class);
        }

    }
    
    @GET
    @Path("/reporte-audiencias-del-mes")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response traerAudienciasDelMes() throws JRException, ClassNotFoundException, SQLException, IOException {
        
        try {
            
            // EMPIEZA A GENERAR EL ARCHIVO DEL REPORTE
            // compilar el archivo del reporte jasper
            String sourceFileName = "C:\\pclSystemFiles\\jasperFiles\\reporte\\AudienciasDelMes.jrxml";
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
            byte[] pdfBytes = JasperExportManager.exportReportToPdf(print);

            String fileName = "C:\\pclSystemFiles\\jasperFiles\\reporte\\AudienciasDelMes.pdf";
            writeFile(pdfBytes, fileName);
            
            ResponseBuilder response = Response.ok((Object) new File(fileName));
            response.header("Content-Disposition", "attachment;filename=" + fileName);
            return response.build();
        } catch (Exception e) {
            return ErrorManager.manejarError(e, Actividades.class);
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
