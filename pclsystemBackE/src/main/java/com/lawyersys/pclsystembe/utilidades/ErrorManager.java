/*
 */
package com.lawyersys.pclsystembe.utilidades;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.lawyersys.pclsystembacke.entities.Clientes;
import com.lawyersys.pclsystembacke.entities.Empleados;
import com.lawyersys.pclsystembe.error.FaltaCargarElemento;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.NoContentException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 *
 * @author Pablo Aguilar
 */
public class ErrorManager {

    //Tipos de errores conocidos actualmente
    //409: Conflicto.
    //406: No Aceptado
    //404: No encontrado.
    //400: Bad Request.
    //500: Internal Server Error.
    
    @SuppressWarnings({ "unchecked", "rawtypes", "unused" })
    public static HashMap getErrorMessage(Exception e) {
        e.printStackTrace();
        System.out.println("Ocurrio esta excepcion: " + e.getClass().getName() + ": " + e.getMessage());
        HashMap error = new HashMap();
        Throwable causa = e;
        int maxdepth = 50;
        String msg = null;
        Integer codError = null;
        for(int i = 0; i < maxdepth && causa != null; i++) {
            if (causa.getMessage() != null){
                if (causa instanceof SQLException) {
                    String state = ((SQLException) causa).getSQLState();
                    if (state.compareTo("22001") == 0) { //(causa.getMessage().trim().startsWith("ERROR: value too long")) {//SQLState 22001
                        if(causa.getMessage().trim().contains("character varying(")) {
                            codError = 5;
                            String mensaje = causa.getMessage().trim();
                            int posIn = mensaje.trim().indexOf("(") + 1;
                            int posFin = mensaje.trim().indexOf(")");
                            msg = "Texto no debe ser mayor a " + mensaje.substring(posIn, posFin) + " letras.";
                            error.put("msg", msg);
                            error.put("codError", codError);
                        }
                    } else if (state.compareTo("22003") == 0) {//numeric_value_out_of_range
                        codError = 5;
                        msg = "numerico es muy grande.";
                        error.put("msg", msg);
                        error.put("codError", codError);
                    } else if (state.compareTo("42601") == 0) {//syntax error
                        codError = 7;
                        String mensaje = causa.getMessage().trim();
                        int posIn = mensaje.trim().indexOf("near ") + 6;
                        int posFin = mensaje.trim().indexOf("\"", posIn);
                        msg = "Error al insertar estos caracteres " + mensaje.substring(posIn, posFin);
                        error.put("msg", msg);
                        error.put("codError", codError);
                    } else if (state.compareTo("23503") == 0) {//foreign_key_violation
                        codError = 10;
                        String mensaje = causa.getMessage().trim();
                        int posIn = mensaje.trim().indexOf("Key ") + 4;
                        int posFin = mensaje.trim().indexOf("is still", posIn);
                        int pos = mensaje.trim().indexOf("\"", posFin);
                        msg = "Error al eliminar o actualizar el registro con Id: " + mensaje.substring(posIn, posFin)
                                        + " por que esta referenciado por un " + mensaje.substring(pos);
                        error.put("msg", msg);
                        error.put("codError", codError);
                    } else if(state.compareTo("P0001") == 0) {//Errores disparados por triggers o procedimientos almacenados.raise_exception
                        codError = 4;
                        msg = causa.getMessage();
                        error.put("msg", msg.substring(0,msg.lastIndexOf("Where: PL/pgSQL") - 3));
                        error.put("codError", codError);
                    } else if (state.compareTo("23505") == 0) {//unique_violation
                        String mensaje = causa.getMessage().trim();
                        if (causa.getMessage().trim().contains("_ind"))//para indices
                            codError = 1;
                        else if (causa.getMessage().trim().contains("ukc_")) {// para numeros de documentos
                            msg = "El Nro. de Documento ya fue registrado.";
                            error.put("msg", msg);
                            codError = 6;
                        } else {
                            codError = 2;
                            int posIn = mensaje.trim().lastIndexOf("=(") + 2;
                            int posFin = mensaje.trim().lastIndexOf(")");
                            msg = mensaje.substring(posIn, posFin);
                            error.put("msg", msg);
                        }
                        error.put("codError", codError);
                    } else if (state.compareTo("42P01") == 0) { //no existe lo que se ejecuto con query
                        error.put("codError", codError);
                    } else if (state.compareTo("22003") == 0) {
                        codError = 8;
                        msg = "Excedio la cantidad de digitos.";
                    } else if (state.compareTo("23514") == 0) {//Check_invalid
                        codError = 8;
                        msg = "Error al momento de insertar, Consulte con su administrador de base de datos.";
                        error.put("msg", msg);
                        error.put("codError", codError);
                    }
                    break;
                }
                else if (causa instanceof NumberFormatException) {
                    codError = 8;
                    if(causa.getMessage().trim().contains("e+"))
                        msg = "Excedio la cantidad de digitos.";
                    else
                        msg = "Ingresó letras en un campo numérico.";

                    error.put("msg", msg);
                    error.put("codError", codError);
                    break;
                }
                else if (causa instanceof IllegalArgumentException || causa instanceof FaltaCargarElemento) {
                    msg = causa.getMessage();
                    codError = 6;
                    error.put("msg", msg);
                    error.put("codError", codError);
                    break;
                }
                else if (causa instanceof ConstraintViolationException) {
                    Set<ConstraintViolation<?>> constrainExc = ((ConstraintViolationException) causa).getConstraintViolations();
                    List<ConstraintViolation<?>> cont= new ArrayList<>(constrainExc);
                    ConstraintViolation<?> valor = cont.get(0);
                    msg = "" + valor.getPropertyPath();//trae el campo con el conflicto.
                    codError = 3;
                    error.put("msg", msg);
                    error.put("codError", codError);
                    break;
                }
                else if (causa instanceof JsonMappingException){
                    msg = "Error en la lectura de los datos. Si el error persiste consulte con soporte";
                    codError = 8;
                    error.put("msg", msg);
                    error.put("codError", codError);
                    break;
                }
                else if (causa.getCause() == null) {
                    codError = 4;
                    msg = causa.getMessage();
                    error.put("msg", msg);
                    error.put("codError", codError);
                    break;
                }
            } else if(i + 1 == 50 || causa.getMessage() == null) {
                codError = -1;
            }
            causa = causa.getCause();
        }
        if (msg != null) {
            System.out.println("Error detectado en manejador de error" + causa.getMessage());
            causa.printStackTrace();
            return error;
        }
        if (codError != null) {
            System.out.println("Error detectado en manejador de error" + causa.getMessage());
            causa.printStackTrace();
            return error;
        }
        return null;
    }

    public static <T> Response manejarError(Exception e, Class<T> clase) {
        HashMap<String, Object> error = getErrorMessage(e);
        Integer codError = (Integer) error.get("codError");
        String mensaje = null;
        switch (codError) {
            case -1:
                mensaje = "Error no manejado. Favor consulte con soporte.";
                return Response.status(Status.BAD_REQUEST).entity(mensaje).header("Content-Type: text/html; charset=utf-8", "*").build();
            case 1:
                mensaje = "Problemas al intentar guardar";
                if(Empleados.class.getSimpleName().compareTo(clase.getSimpleName()) == 0){
                    mensaje += " el empleado";
                }
                /*
                else if (Listacerradacab.class.getSimpleName().compareTo(clase.getSimpleName()) == 0) {
                    mensaje = "Ya existe una lista de valores con el mismo nombre";
                }
                else if(Evento.class.getSimpleName().compareTo(clase.getSimpleName()) == 0){
                    mensaje = "No se puede realizar la operación. El nombre ya está siendo utilizado por otro Evento.";
                }
                else if(Proceso.class.getSimpleName().compareTo(clase.getSimpleName()) == 0){
                    mensaje = "No se puede realizar la operación. El nombre ya está siendo utilizado por otro Proceso.";
                } 
                else if(GrupoRegla.class.getSimpleName().compareTo(clase.getSimpleName()) == 0){
                    mensaje = "No se puede realizar la operación. El nombre ya está siendo utilizado por otro Grupo de Regla.";
                }*/
                else
                    mensaje = "Ya existe un " + clase.getSimpleName() + " con el mismo codigo";
                mensaje += ", Favor consulte con soporte.";
                return Response.status(Status.CONFLICT).entity(mensaje).header("Content-Type: text/html; charset=utf-8", "*").build();
            /*case 2:
                if(CanalesVentas.class.getSimpleName().compareTo(clase.getSimpleName()) == 0) {
                    mensaje = "Ya existe un canal de ventas con el mismo codigo";
                } else if(Evento.class.getSimpleName().compareTo(clase.getSimpleName()) == 0 ) {
                    if(error.get("msg") != null)
                        mensaje = "No se puede realizar la operación. El Campo de Origen " + ((String) error.get("msg")).toUpperCase() + 
                                  " ya está siendo utilizado por otro atributo.";
                    else
                        mensaje = "No se puede realizar la operación. Tienes atributos con el mismo nombre.";
                } else if(Proceso.class.getSimpleName().compareTo(clase.getSimpleName()) == 0) {
                    mensaje = "No se puede realizar la operación. El nombre " + ((String) error.get("msg")).toUpperCase() + 
                              " ya está siendo utilizado por otro Proceso.";
                } else if(ConceptoComision.class.getSimpleName().compareTo(clase.getSimpleName()) == 0) {
                mensaje = "No se puede realizar la operación. El nombre con el grupo de regla" + 
                          " ya está siendo utilizado por otro Concepto Comisión.";
                } else
                    mensaje = "Ya existe un " + clase.getSimpleName() + " con el mismo codigo";

                return Response.status(Status.CONFLICT).entity(mensaje).header("Content-Type: text/html; charset=utf-8", "*").build();
            */case 3:
                mensaje = "Excedio la cantidad maxima de dígitos en campo ";
                if(Empleados.class.getSimpleName().compareTo(clase.getSimpleName()) == 0) {
                    String campoError = (String) error.get("msg");
                    if(campoError.compareTo("cedula") == 0)
                        campoError = "Nro. de Documento";
                    mensaje += campoError + ".";
                } else if (Clientes.class.getSimpleName().compareTo(clase.getSimpleName()) == 0){
                    String campoError = (String) error.get("msg");
                    if(campoError.compareTo("cedulaRucCliente") == 0)
                        campoError = "Nro. de Documento";
                    else if(campoError.compareTo("nombreCompleto") == 0)
                        campoError = "Nombre y Apellido";
                    
                    mensaje += campoError + ".";
                }
                return Response.status(Status.CONFLICT).entity(mensaje).header("Content-Type: text/html; charset=utf-8", "*").build();
            case 4:
                mensaje = (String) error.get("msg");
                return Response.status(Status.CONFLICT).entity(mensaje).header("Content-Type: application/json; charset=utf-8", "*").build();
            case 5:
                mensaje = "El contenido de uno de los campos de tipo " + (String) error.get("msg");
                return Response.status(Status.BAD_REQUEST).entity(mensaje).header("Content-Type: application/json; charset=utf-8", "*").build();
            case 6:
                mensaje = (String) error.get("msg");
                return Response.status(Status.CONFLICT).entity(mensaje).header("Content-Type: application/json; charset=utf-8", "*").build();
            case 7:
                mensaje = (String) error.get("msg");
                return Response.status(Status.BAD_REQUEST).entity(mensaje).header("Content-Type: application/json; charset=utf-8", "*").build();
            case 8:
                mensaje = (String) error.get("msg");
                return Response.status(Status.BAD_REQUEST).entity(mensaje).header("Content-Type: application/json; charset=utf-8", "*").build();
            case 9:
                mensaje = (String) error.get("msg");
                return Response.status(Status.CONFLICT).entity(mensaje).header("Content-Type: application/json; charset=utf-8", "*").build();
            case 10:
                return Response.status(Status.CONFLICT).build();
            default:
                return Response.status(Status.BAD_REQUEST).build();
        }
    }
    
    // gracias Porfi

}
