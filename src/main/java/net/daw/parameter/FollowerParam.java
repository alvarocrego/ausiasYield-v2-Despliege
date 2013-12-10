/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.daw.parameter;

import java.text.ParseException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import net.daw.bean.FollowerBean;

/**
 *
 * @author al037570
 */
public class FollowerParam {
    
    private final HttpServletRequest request;

    public FollowerParam(HttpServletRequest request) throws Exception {
        this.request = request;
    }

    public FollowerBean loadId(FollowerBean oFollower) throws ServletException {
        try {
            if (request.getParameter("id") != null) {
                oFollower.setId(Integer.parseInt(request.getParameter("id")));
            } else {
                oFollower.setId(0);
            }
        } catch (NumberFormatException e) {
            throw new ServletException("Controller: Error: loadId: Formato de datos en parámetros incorrecto " + e.getMessage());
        }
        return oFollower;

    }

    public FollowerBean load(FollowerBean oFollower) throws NumberFormatException, ParseException {
        try {
            if ((request.getParameter("id_usuario1") != null)) {
                oFollower.getUsuario1().setId(Integer.parseInt(request.getParameter("id_usuario1")));
            }
            if ((request.getParameter("id_usuario2") != null)) {
                oFollower.getUsuario2().setId(Integer.parseInt(request.getParameter("id_usuario2")));
            }            
        } catch (NumberFormatException e) {
            throw new NumberFormatException("FollowerParam: Error: load: Formato de datos en parámetros incorrecto " + e.getMessage());
        }
        return oFollower;
    }
    
    
}
