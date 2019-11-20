package com.lawyersys.pclsystembe.rest.facturacion;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawyersys.pclsystembacke.entities.Facturas;
import com.lawyersys.pclsystembacke.entities.FacturasPK;
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
import javax.ws.rs.core.PathSegment;
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
 * @author Pablo Aguilar
 */
@Stateless
@Path("facturas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FacturasFacadeREST {

    private static String UPLOADED_FACTURAS = "C:\\pclSystemFiles\\facturasRecibos\\";
    
    private FacturasPK getPrimaryKey(PathSegment pathSegment) {
        /*
         * pathSemgent represents a URI path segment and any associated matrix parameters.
         * URI path part is supposed to be in form of 'somePath;codFactura=codFacturaValue;nroFactura=nroFacturaValue'.
         * Here 'somePath' is a result of getPath() method invocation and
         * it is ignored in the following code.
         * Matrix parameters are used as field names to build a primary key instance.
         */
        com.lawyersys.pclsystembacke.entities.FacturasPK key = new com.lawyersys.pclsystembacke.entities.FacturasPK();
        javax.ws.rs.core.MultivaluedMap<String, String> map = pathSegment.getMatrixParameters();
        java.util.List<String> codFactura = map.get("codFactura");
        if (codFactura != null && !codFactura.isEmpty()) {
            key.setCodFactura(new java.lang.Integer(codFactura.get(0)));
        }
        java.util.List<String> nroFactura = map.get("nroFactura");
        if (nroFactura != null && !nroFactura.isEmpty()) {
            key.setNroFactura(nroFactura.get(0));
        }
        return key;
    }

    public FacturasFacadeREST() {
    }

    @EJB
    private ABMManagerFacturacion abmManager;

    @POST
    @Path("guardar")
    public Response create(@RequestBody() String entity) throws IOException, FaltaCargarElemento {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Facturas elem = mapper.readValue(entity, Facturas.class);   
            if ( elem.getCodCliente().getCodCliente() == 0 ) {
                throw new FaltaCargarElemento("Error. Cargar cliente.");
            }
            if ( elem.getMontoTotal() == 0 ) {
                throw new FaltaCargarElemento("Error. Cargar monto total.");
            }
            if ( elem.getCedulaEmisor().getCedula() == null ) {
                throw new FaltaCargarElemento("Error. Cargar cedula del emisor.");
            }
            abmManager.create(Facturas.class, elem);
            return Response.ok().build();
        } catch (Exception e) {
            return ErrorManager.manejarError(e, Facturas.class);
        }
    }
    
    @POST
    @Path("crear-factura-de-recibo")
    public Response crearFacturaDeRecibo(@RequestBody() String entity) throws IOException, FaltaCargarElemento {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Facturas elem = mapper.readValue(entity, Facturas.class);   
            if ( elem.getCodCliente().getCodCliente() == 0 ) {
                throw new FaltaCargarElemento("Error. Cargar cliente.");
            }
            if ( elem.getMontoTotal() == 0 ) {
                throw new FaltaCargarElemento("Error. Cargar monto total.");
            }
            if ( elem.getCedulaEmisor().getCedula() == null ) {
                throw new FaltaCargarElemento("Error. Cargar cedula del emisor.");
            }
            if ( elem.getCodPago().getCodPago() == null ) {
                throw new FaltaCargarElemento("Error. Cargar codigo del pago.");
            }
            abmManager.crearFacturaDeRecibo(elem);
            return Response.ok().build();
        } catch (Exception e) {
            return ErrorManager.manejarError(e, Facturas.class);
        }
    }

    @PUT
    @Path("actualizar/{id}")
    public Response edit(@RequestBody() String entity) throws IOException, FaltaCargarElemento {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Facturas elem = mapper.readValue(entity, Facturas.class);
            if ( elem.getCodCliente().getCodCliente() == 0 ) {
                throw new FaltaCargarElemento("Error. Cargar cliente.");
            }
            if ( elem.getMontoTotal() == 0 ) {
                throw new FaltaCargarElemento("Error. Cargar monto total.");
            }
            if ( elem.getCedulaEmisor().getCedula() == null ) {
                throw new FaltaCargarElemento("Error. Cargar cedula del emisor.");
            }
            abmManager.edit(Facturas.class, elem);
            return Response.ok().build();
        } catch (Exception e) {
            return ErrorManager.manejarError(e, Facturas.class);
        }
    }

    @GET
    @Path("traer/{id}")
    public Response find(@PathParam("id") String id) throws JsonProcessingException {
        try {
            List<Facturas> elem = (List<Facturas>) (Object) abmManager.find("Facturas", id);
            ObjectMapper mapper = new ObjectMapper();
            String resp = mapper.writeValueAsString(elem);
            return Response.ok(resp).build();
        } catch (Exception e) {
            return ErrorManager.manejarError(e, Facturas.class);
        }
    }
    
    @GET
    @Path("/traer-archivo-factura/{codFactura}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response downloadFileWithGet(@PathParam("codFactura") String codFactura) throws JRException, ClassNotFoundException, SQLException, IOException {
        
        try {
            
            // EMPIEZA A GENERAR EL ARCHIVO DE LA FACTURA
            // compilar el archivo del reporte jasper
            String sourceFileName = "C:\\pclSystemFiles\\jasperFiles\\Factura.jrxml";
            String jasperReport =  JasperCompileManager.compileReportToFile(sourceFileName);
            
            // traemos la factura solicitada
            Facturas factura = new Facturas();
            List<Facturas> facturaAux = (List<Facturas>) (Object) abmManager.find("Facturas", codFactura);
            if (!facturaAux.isEmpty()) {
                factura = facturaAux.get(0);
            } else {
                throw new FaltaCargarElemento("Error. La factura no existe.");
            }
            
            // mapea los parametros que pasara al archivo jasper
            Map parameters = new HashMap();
            parameters.put("v_cod_factura",(factura.getFacturasPK().getCodFactura()));
            
            // establece la conexion a la base de datos
            String cadenaConexion = "jdbc:postgresql://localhost:5432/lawyersys";
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(cadenaConexion, "postgres", "postgres");
            
            //Aqui se llena el reporte (se ejecuta la consulta)
            JasperPrint print = new JasperPrint();
            print = JasperFillManager.fillReport(jasperReport, parameters, connection);
            byte[] pdfBytes = JasperExportManager.exportReportToPdf(print);

            String fileName = UPLOADED_FACTURAS + factura.getCedulaEmisor().getCedula() 
                    + " " + factura.getFacturasPK().getNroFactura() + ".pdf";
            writeFile(pdfBytes, fileName);
            
            ResponseBuilder response = Response.ok((Object) new File(fileName));
            response.header("Content-Disposition", "attachment;filename=" + fileName);
            return response.build();
        } catch (Exception e) {
            return ErrorManager.manejarError(e, Facturas.class);
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
            List<Facturas> elem = (List<Facturas>) (Object) abmManager.findAll("Facturas");
            ObjectMapper mapper = new ObjectMapper();
            String resp = mapper.writeValueAsString(elem);
            return Response.ok(resp).build();
        } catch (Exception e) {
            return ErrorManager.manejarError(e, Facturas.class);
        }
    }
    
    @GET
    @Path("traer-facturas-de-cuenta/{id}")
    public Response traerFacturasDeCuenta(@PathParam("id") String codCuenta) throws JsonProcessingException {
        try {
            List<Facturas> elem = (List<Facturas>) (Object) abmManager.traerFacturasDeCuenta(codCuenta);
            ObjectMapper mapper = new ObjectMapper();
            String resp = mapper.writeValueAsString(elem);
            return Response.ok(resp).build();
        } catch (Exception e) {
            return ErrorManager.manejarError(e, Facturas.class);
        }
    }
    
}
