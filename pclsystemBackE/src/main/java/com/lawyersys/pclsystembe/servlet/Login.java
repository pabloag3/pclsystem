/*
 */
package com.lawyersys.pclsystembe.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawyersys.pclsystembacke.entities.Permisos;
import com.lawyersys.pclsystembacke.entities.Usuarios;
import com.lawyersys.pclsystembe.abm.ABMManagerUsuarios;
import com.lawyersys.pclsystembe.utilidades.Seguridad;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author tatoa
 */
@WebServlet(name = "Login", urlPatterns = {"/Login"})
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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String uri = request.getServletPath();

        if (uri.contains("/login")) {
            
            String username = request.getParameter("username");
            String contrasenha = request.getParameter("contrasenha");
            
            HttpSession session = request.getSession();
            
            if ( username == null || username == "" || contrasenha == null || contrasenha == "" ) {
                response.sendError(response.SC_FORBIDDEN);
            }
            
            Usuarios usuario = (Usuarios) abmManager.find("Usuarios", username);
            if (usuario == null) {
                response.sendError(response.SC_FORBIDDEN);
            } else {
                if (usuario.getContrasenha() == Seguridad.getMd5(contrasenha)) {
                    if (usuario.getCodEstado().getDescripcion() == "HABILITADO" ) {
                        
                        String sessionToken = request.getSession().getId();
                        
                        int rol = usuario.getCodRol().getCodRol();
                        List<Permisos> permisos = (List<Permisos>) (Permisos) abmManager.findPermisosByRol(rol);
                        
                        session.setAttribute("sessionToken", sessionToken);
                        session.setAttribute("usuario", username);
                        session.setAttribute("permisos", permisos);
                        HashMap jsonString = new HashMap();
                        jsonString.put("permisos", permisos);
                        ObjectMapper mapper = new ObjectMapper();
                        String respuesta = mapper.writeValueAsString(jsonString);
                        
                        response.setContentType("text/plain");
                        response.getWriter().write(respuesta);
                        response.getWriter().close();
                    } else {
                        response.sendError(response.SC_FORBIDDEN);
                    }
                } else {
                    response.sendError(response.SC_FORBIDDEN);
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
        processRequest(request, response);
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
        processRequest(request, response);
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
