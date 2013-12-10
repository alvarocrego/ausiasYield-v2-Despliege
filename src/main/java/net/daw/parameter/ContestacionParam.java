/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.parameter;

import java.text.ParseException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import net.daw.bean.ContestacionBean;

/**
 *
 * @author rafa
 */
public class ContestacionParam {

    private final HttpServletRequest request;

    public ContestacionParam(HttpServletRequest request) throws Exception {
        this.request = request;
    }

    public ContestacionBean loadId(ContestacionBean oContestacion) throws ServletException {
        try {
            if (request.getParameter("id") != null) {
                oContestacion.setId(Integer.parseInt(request.getParameter("id")));
            } else {
                oContestacion.setId(0);
            }
        } catch (NumberFormatException e) {
            throw new ServletException("Controller: Error: loadId: Formato de datos en parámetros incorrecto " + e.getMessage());
        }
        return oContestacion;

    }

    public ContestacionBean load(ContestacionBean oContestacion) throws NumberFormatException, ParseException {
        try {
            if ((request.getParameter("id_usuario") != null)) {
                oContestacion.getUsuario().setId(Integer.parseInt(request.getParameter("id_usuario")));
            }
            if ((request.getParameter("id_pregunta") != null)) {
                oContestacion.getPregunta().setId(Integer.parseInt(request.getParameter("id_pregunta")));
            }
            if ((request.getParameter("id_opcion") != null)) {
                oContestacion.getOpcion().setId(Integer.parseInt(request.getParameter("id_opcion")));
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException("ContestacionParam: Error: load: Formato de datos en parámetros incorrecto " + e.getMessage());
        }
        return oContestacion;
    }
}
