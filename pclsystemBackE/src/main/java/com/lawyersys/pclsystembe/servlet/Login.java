/*
 */
package com.lawyersys.pclsystembe.servlet;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawyersys.pclsystembacke.entities.Permisos;
import com.lawyersys.pclsystembacke.entities.Usuarios;
import com.lawyersys.pclsystembe.abm.ABMManagerUsuarios;
import com.lawyersys.pclsystembe.utilidades.Seguridad;
import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author tatoa
 */
//@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

    @EJB
    private ABMManagerUsuarios abmManager;
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NoSuchAlgorithmException {
        response.setContentType("text/html;charset=UTF-8");
        String uri = request.getServletPath();
        
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(1024);
        KeyPair kp = kpg.generateKeyPair();

        if (uri.contains("Login")) {
            
            String username = request.getParameter("username");
            String contrasenha = request.getParameter("contrasenha");
            
            HttpSession session = request.getSession();

            if ( username == null || username == "" || contrasenha == null || contrasenha == "" ) {
                response.sendError(response.SC_FORBIDDEN);
            }
            
            List<Usuarios> elem = (List<Usuarios>) (Object) abmManager.find("Usuarios", username);
            Usuarios usuario = elem.get(0);
            if (usuario == null) {
                response.sendError(response.SC_FORBIDDEN);
                System.out.println("usuario no existe");
            } else {
                if (usuario.getContrasenha().equals(Seguridad.getMd5(contrasenha))) {
                    if (usuario.getCodEstado().getDescripcion().equals("HABILITADO") ) {
                        
                        // se usa HS256
                        Algorithm algorithm = Algorithm.HMAC256("pclsystembacke");
                        String sessionToken = JWT.create().withIssuer(usuario.getDescripcion()).sign(algorithm);
                        
                        int rol = usuario.getCodRol().getCodRol();
                        List<Permisos> permisos = (List<Permisos>) (Object) abmManager.findPermisosByRol(rol);
                        
                        session.setAttribute("sessionToken", sessionToken);
                        session.setAttribute("usuario", username);
                        session.setAttribute("permisos", permisos);
                        HashMap jsonString = new HashMap();
                        jsonString.put("usuarioId", usuario.getCodUsuario());
                        jsonString.put("usuarioCedula", usuario.getCedula().getCedula());
                        jsonString.put("usuarioCodRol", usuario.getCodRol().getCodRol());
                        jsonString.put("permisos", permisos);
                        jsonString.put("tokenId", sessionToken);
                        ObjectMapper mapper = new ObjectMapper();
                        String respuesta = mapper.writeValueAsString(jsonString);
                        
                        response.setContentType("text/plain");
                        response.getWriter().write(respuesta);
                        response.getWriter().close();
                    } else {
                        System.out.println("no habilitado");
                        response.sendError(response.SC_FORBIDDEN);
                    }
                } else {
                    System.out.println("mala contrasenha");
                    response.sendError(response.SC_NOT_ACCEPTABLE);
                }
            }
        } else if (uri.contains("/deslogueo")) {
            HttpSession session = request.getSession();
            session.invalidate();
        }   
    }
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
