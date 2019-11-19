package com.lawyersys.pclsystembe.rest.facturacion;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawyersys.pclsystembacke.entities.Recibos;
import com.lawyersys.pclsystembe.abm.ABMManagerFacturacion;
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
@Path("recibos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RecibosFacadeREST  {

    public RecibosFacadeREST() {
    }

    @EJB
    private ABMManagerFacturacion abmManager;
    
    private static String UPLOADED_RECIBOS = "C:\\pclSystemFiles\\facturasRecibos\\";

    @POST
    @Path("guardar")
    public Response create(@RequestBody() String entity) throws IOException, FaltaCargarElemento {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Recibos elem = mapper.readValue(entity, Recibos.class);   
            if ( elem.getDescripcion()== null ) {
                throw new FaltaCargarElemento("Error. Cargar descripcion.");
            }
            if ( elem.getMonto() == 0 ) {
                throw new FaltaCargarElemento("Error. Cargar monto.");
            }
            if ( elem.getMontoTexto() == "" ) {
                throw new FaltaCargarElemento("Error. Cargar el monto en letras.");
            }
            abmManager.create(Recibos.class, elem);
            return Response.ok().build();
        } catch (Exception e) {
            return ErrorManager.manejarError(e, Recibos.class);
        }
    }

    @PUT
    @Path("actualizar/{id}")
    public Response edit(@RequestBody() String entity) throws IOException, FaltaCargarElemento {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Recibos elem = mapper.readValue(entity, Recibos.class);
            if ( elem.getMonto() == 0 ) {
                throw new FaltaCargarElemento("Error. Cargar monto.");
            }
            if ( elem.getMonto() == 0 ) {
                throw new FaltaCargarElemento("Error. Cargar monto.");
            }
            abmManager.edit(Recibos.class, elem);
            return Response.ok().build();
        } catch (Exception e) {
            return ErrorManager.manejarError(e, Recibos.class);
        }
    }

    @GET
    @Path("traer/{id}")
    public Response find(@PathParam("id") String id) throws JsonProcessingException {
        try {
            List<Recibos> elem = (List<Recibos>) (Object) abmManager.find("Recibos", id);
            ObjectMapper mapper = new ObjectMapper();
            String resp = mapper.writeValueAsString(elem);
            return Response.ok(resp).build();
        } catch (Exception e) {
            return ErrorManager.manejarError(e, Recibos.class);
        }
    }
    
    @GET
    @Path("/traer-archivo-recibo/{codRecibo}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response downloadFileWithGet(@PathParam("codRecibo") String codRecibo) throws JRException, ClassNotFoundException, SQLException, IOException {
        
        try {
            
            // EMPIEZA A GENERAR EL ARCHIVO DEL RECIBO
            // compilar el archivo del reporte jasper
            String sourceFileName = "C:\\pclSystemFiles\\jasperFiles\\Recibo.jrxml";
            String jasperReport =  JasperCompileManager.compileReportToFile(sourceFileName);
            
            // traemos la factura solicitada
            Recibos recibo = new Recibos();
            List<Recibos> reciboAux = (List<Recibos>) (Object) abmManager.find("Recibos", codRecibo);
            if (!reciboAux.isEmpty()) {
                recibo = reciboAux.get(0);
            } else {
                throw new FaltaCargarElemento("Error. El recibo no existe.");
            }
            
            // mapea los parametros que pasara al archivo jasper
            Map parameters = new HashMap();
            parameters.put("v_cod_recibo",(recibo.getCodRecibo()));
            
            // establece la conexion a la base de datos
            String cadenaConexion = "jdbc:postgresql://localhost:5432/lawyersys";
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(cadenaConexion, "postgres", "postgres");
            
            //Aqui se llena el reporte (se ejecuta la consulta)
            JasperPrint print = new JasperPrint();
            print = JasperFillManager.fillReport(jasperReport, parameters, connection);
            byte[] pdfBytes = JasperExportManager.exportReportToPdf(print);

            String fileName = UPLOADED_RECIBOS + " " + recibo.getCodRecibo() + ".pdf";
            writeFile(pdfBytes, fileName);
            
            ResponseBuilder response = Response.ok((Object) new File(fileName));
            response.header("Content-Disposition", "attachment;filename=" + fileName);
            return response.build();
        } catch (Exception e) {
            return ErrorManager.manejarError(e, Recibos.class);
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

    @GET
    @Path("listar")
    public Response findAll() throws JsonProcessingException {
        try {
            List<Recibos> elem = (List<Recibos>) (Object) abmManager.findAll("Recibos");
            ObjectMapper mapper = new ObjectMapper();
            String resp = mapper.writeValueAsString(elem);
            return Response.ok(resp).build();
        } catch (Exception e) {
            return ErrorManager.manejarError(e, Recibos.class);
        }
    }
    
    @GET
    @Path("traer-recibos-de-cuenta/{id}")
    public Response traerFacturasDeCuenta(@PathParam("id") String codCuenta) throws JsonProcessingException {
        try {
            List<Recibos> elem = (List<Recibos>) (Object) abmManager.traerRecibosDeCuenta(codCuenta);
            ObjectMapper mapper = new ObjectMapper();
            String resp = mapper.writeValueAsString(elem);
            return Response.ok(resp).build();
        } catch (Exception e) {
            return ErrorManager.manejarError(e, Recibos.class);
        }
    }
    
}
