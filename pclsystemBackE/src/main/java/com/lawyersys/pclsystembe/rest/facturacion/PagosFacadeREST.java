package com.lawyersys.pclsystembe.rest.facturacion;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawyersys.pclsystembacke.entities.Pagos;
import com.lawyersys.pclsystembe.abm.ABMManagerFacturacion;
import com.lawyersys.pclsystembe.dtos.ListaPagosDTO;
import com.lawyersys.pclsystembe.error.FaltaCargarElemento;
import com.lawyersys.pclsystembe.utilidades.ErrorManager;
import com.lawyersys.pclsystembe.utilidades.Log;
import java.io.IOException;
import java.util.ArrayList;
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
@Path("pagos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PagosFacadeREST {

    public PagosFacadeREST() {
    }
    
    @EJB
    private ABMManagerFacturacion abmManager;

    @POST
    @Path("guardar/{username}")
    public Response create(@PathParam("username") String username,
            @RequestBody() ArrayList<Pagos> listaPagos) throws IOException, FaltaCargarElemento {        
        try {
//            ObjectMapper mapper = new ObjectMapper();
//            Pagos elem = mapper.readValue(entity, Pagos.class);    

            for (Pagos elem : listaPagos) {
                
                if ( elem.getMontoPagado() == 0 ) {
                    throw new FaltaCargarElemento("Error. Cargar monto pagado.");
                }
                if ( elem.getMontoPagado()== -1 ) {
                    elem.setMontoPagado(0);
                }
                if ( elem.getCodCuenta().getCodCuenta() == 0 ) {
                    throw new FaltaCargarElemento("Error. Cargar cuenta.");
                }
                if ( elem.getCodTipoPago().getCodTipoPago() == 0 ) {
                    throw new FaltaCargarElemento("Error. Cargar tipo de pago.");
                }

                // en caso que se pague con cheque
                if (elem.getCodTipoPago().getCodTipoPago() == 2) {
                    if (elem.getNroCuentaCheque() == null) {
                        throw new FaltaCargarElemento("Error. Cargar nro de cuenta del cheque.");
                    }
                    if (elem.getFechaVencCheque() == null) {
                        throw new FaltaCargarElemento("Error. Cargar fecha del vencimiento del cheque.");
                    }
                    if (elem.getNroSerieCheque() == null) {
                        throw new FaltaCargarElemento("Error. Cargar nro de serie del cheque.");
                    }
                    if (elem.getEntidadFinanciera() == null) {
                        throw new FaltaCargarElemento("Error. Cargar entidad financiera.");
                    }
                } else if (elem.getCodTipoPago().getCodTipoPago() == 3 || elem.getCodTipoPago().getCodTipoPago() == 4) {
                    // en caso que se pague con tarjeta de credito o debito
                    if (elem.getEntidadFinanciera() == null) {
                        throw new FaltaCargarElemento("Error. Cargar entidad financiera.");
                    }
                    if (elem.getNroComproBanco() == null) {
                        throw new FaltaCargarElemento("Error. Cargar numero de comprobante del banco.");
                    }
                    if (elem.getNroTarjeta() == null) {
                        throw new FaltaCargarElemento("Error. Cargar numero de tarjeta.");
                    }
                } else if (elem.getCodTipoPago().getCodTipoPago() == 5) {
                    if (elem.getNroComproBanco() == null) {
                        throw new FaltaCargarElemento("Error. Cargar numero de comprobante del banco.");
                    }
                    if (elem.getEntidadFinanciera() == null) {
                        throw new FaltaCargarElemento("Error. Cargar entidad financiera.");
                    }
                }
                if (elem.getCodExpediente().getCodExpediente() == null || elem.getCodExpediente().getCodExpediente() == 0) {
                    throw new FaltaCargarElemento("Error. Cargar expediente.");
                }

                abmManager.create(Pagos.class, elem);
                List<Pagos> ultimoPago = (List<Pagos>) (Object) abmManager.traerUltimoPago();
                Log.escribir("INFORMACION", username + " Accion: Crear pago: " + ultimoPago.get(0).getCodPago());
            }
            return Response.ok().build();
            
        } catch (Exception e) {
            return ErrorManager.manejarError(e, Pagos.class);
        }
    }

    @PUT
    @Path("actualizar/{id}/{username}")
    public Response edit(@PathParam("username") String username,
            @RequestBody() String entity) throws IOException, FaltaCargarElemento {
        try {
            ObjectMapper mapper = new ObjectMapper();
            ListaPagosDTO listaPagos = mapper.readValue(entity, ListaPagosDTO.class);
            
            for (Pagos elem : listaPagos.getListaPagos() ) {
                if ( elem.getMontoPagado() == 0 ) {
                    throw new FaltaCargarElemento("Error. Cargar monto pagado.");
                }
                if ( elem.getMontoPagado()== -1 ) {
                    elem.setMontoPagado(0);
                }
                if ( elem.getCodCuenta().getCodCuenta() == 0 ) {
                    throw new FaltaCargarElemento("Error. Cargar cuenta.");
                }
                if ( elem.getCodTipoPago().getCodTipoPago() == 0 ) {
                    throw new FaltaCargarElemento("Error. Cargar tipo de pago.");
                }

                // en caso que se pague con cheque
                if (elem.getCodTipoPago().getCodTipoPago() == 2) {
                    if (elem.getNroCuentaCheque() == null) {
                        throw new FaltaCargarElemento("Error. Cargar nro de cuenta del cheque.");
                    }
                    if (elem.getFechaVencCheque() == null) {
                        throw new FaltaCargarElemento("Error. Cargar fecha del vencimiento del cheque.");
                    }
                    if (elem.getNroSerieCheque() == null) {
                        throw new FaltaCargarElemento("Error. Cargar nro de serie del cheque.");
                    }
                    if (elem.getEntidadFinanciera() == null) {
                        throw new FaltaCargarElemento("Error. Cargar entidad financiera.");
                    }
                } else if (elem.getCodTipoPago().getCodTipoPago() == 3 || elem.getCodTipoPago().getCodTipoPago() == 4) {
                    // en caso que se pague con tarjeta de credito o debito
                    if (elem.getEntidadFinanciera() == null) {
                        throw new FaltaCargarElemento("Error. Cargar entidad financiera.");
                    }
                    if (elem.getNroComproBanco() == null) {
                        throw new FaltaCargarElemento("Error. Cargar numero de comprobante del banco.");
                    }
                    if (elem.getNroTarjeta() == null) {
                        throw new FaltaCargarElemento("Error. Cargar numero de tarjeta.");
                    }
                } else if (elem.getCodTipoPago().getCodTipoPago() == 5) {
                    if (elem.getNroComproBanco() == null) {
                        throw new FaltaCargarElemento("Error. Cargar numero de comprobante del banco.");
                    }
                    if (elem.getEntidadFinanciera() == null) {
                        throw new FaltaCargarElemento("Error. Cargar entidad financiera.");
                    }
                }
                if (elem.getCodExpediente().getCodExpediente() == null || elem.getCodExpediente().getCodExpediente() == 0) {
                    throw new FaltaCargarElemento("Error. Cargar expediente.");
                }

                abmManager.edit(Pagos.class, elem);
                Log.escribir("INFORMACION", username + " Accion: Modificar pago: " + elem.getCodPago());
            }

            return Response.ok().build();
        } catch (Exception e) {
            return ErrorManager.manejarError(e, Pagos.class);
        }
    }

    @GET
    @Path("traer/{id}")
    public Response find(@PathParam("id") String id) throws JsonProcessingException {
        try {
            List<Pagos> elem = (List<Pagos>) (Object) abmManager.find("Pagos", id);
            ObjectMapper mapper = new ObjectMapper();
            String resp = mapper.writeValueAsString(elem);
            return Response.ok(resp).build();
        } catch (Exception e) {
            return ErrorManager.manejarError(e, Pagos.class);
        }
    }

    @GET
    @Path("listar")
    public Response findAll() throws JsonProcessingException {
        try {
            List<Pagos> elem = (List<Pagos>) (Object) abmManager.findAll("Pagos");
            ObjectMapper mapper = new ObjectMapper();
            String resp = mapper.writeValueAsString(elem);
            return Response.ok(resp).build();
        } catch (Exception e) {
            return ErrorManager.manejarError(e, Pagos.class);
        }
    }
    
}
