/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.parameter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import net.daw.bean.UsuarioBean;

/**
 *
 * @author jose
 */
public class UsuarioParam {

    private final HttpServletRequest request;

    public UsuarioParam(HttpServletRequest request) throws Exception {
        this.request = request;
    }

    public UsuarioBean loadId(UsuarioBean oUsuario) throws ServletException {
       try {
            if (request.getParameter("id") != null) {
                oUsuario.setId(Integer.parseInt(request.getParameter("id")));
            } else {
                oUsuario.setId(0);
            }
        } catch (NumberFormatException e) {
            throw new ServletException("Controller: Error: loadId: Formato de datos en parámetros incorrecto " + e.getMessage());
        }
        return oUsuario;
    }

    public UsuarioBean load(UsuarioBean oUsuario) throws NumberFormatException {
        try {
            if ((request.getParameter("login") != null)) {
                oUsuario.setLogin(request.getParameter("login"));
            }
            if ((request.getParameter("password") != null)) {
                oUsuario.setPassword(request.getParameter("password"));
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Controller: Error: load: Formato de datos en parámetros incorrecto " + e.getMessage());
        }
        return oUsuario;
    }
}
