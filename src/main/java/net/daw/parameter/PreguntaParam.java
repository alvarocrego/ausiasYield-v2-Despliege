/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.parameter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import net.daw.bean.PreguntaBean;

/**
 *
 * @author rafa
 */
public class PreguntaParam {

    private final HttpServletRequest request;

    public PreguntaParam(HttpServletRequest request) throws Exception {
        this.request = request;
    }

    public PreguntaBean loadId(PreguntaBean oPregunta) throws ServletException {
        try {
            if (request.getParameter("id") != null) {
                oPregunta.setId(Integer.parseInt(request.getParameter("id")));
            } else {
                oPregunta.setId(0);
            }
        } catch (NumberFormatException e) {
            throw new ServletException("Controller: Error: loadId: Formato de datos en parámetros incorrecto " + e.getMessage());
        }
        return oPregunta;

    }

    public PreguntaBean load(PreguntaBean oPregunta) throws NumberFormatException {
        try {
            if ((request.getParameter("descripcion") != null)) {
                oPregunta.setDescripcion(request.getParameter("descripcion"));
            }
            if ((request.getParameter("id_cuestionario") != null)) {
                oPregunta.getCuestionario().setId(Integer.parseInt(request.getParameter("id_cuestionario")));
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Controller: Error: load: Formato de datos en parámetros incorrecto " + e.getMessage());
        }
        return oPregunta;
    }

//    public PreguntaBean loadCuestionario(PreguntaBean oPregunta) throws NumberFormatException {
//        try {
//            if ((request.getParameter("id_tipopregunta") != null)) {
//                TipopreguntaBean oTipopregunta = new TipopreguntaBean(Integer.parseInt(request.getParameter("id_tipopregunta")));
//                oPregunta.setCuestionario(oTipopregunta);
//            }
//        } catch (NumberFormatException e) {
//            throw new NumberFormatException("Controller: Error: loadCuestionario: Formato de datos en parámetros incorrecto " + e.getMessage());
//        }
//        return oPregunta;
//    }
}
