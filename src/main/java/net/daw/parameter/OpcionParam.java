/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.parameter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import net.daw.bean.OpcionBean;
import net.daw.bean.PreguntaBean;

/**
 *
 * @author rafa
 */
public class OpcionParam {

    private final HttpServletRequest request;

    public OpcionParam(HttpServletRequest request) throws Exception {
        this.request = request;
    }

    public OpcionBean loadId(OpcionBean oOpcion) throws ServletException {
        try {
            if (request.getParameter("id") != null) {
                oOpcion.setId(Integer.parseInt(request.getParameter("id")));
            } else {
                oOpcion.setId(0);
            }
        } catch (NumberFormatException e) {
            throw new ServletException("Controller: Error: loadId: Formato de datos en parámetros incorrecto " + e.getMessage());
        }
        return oOpcion;

    }

    public OpcionBean load(OpcionBean oOpcion) throws NumberFormatException {
        try {
            if ((request.getParameter("descripcion") != null)) {
                oOpcion.setDescripcion(request.getParameter("descripcion"));
            }
            if ((request.getParameter("id_pregunta") != null)) {
                oOpcion.getPregunta().setId(Integer.parseInt(request.getParameter("id_pregunta")));
            }
            if ((request.getParameter("correcta") != null)) {
                String correcta = request.getParameter("correcta");
                oOpcion.setCorrecta( correcta.equals("true") );
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Controller: Error: load: Formato de datos en parámetros incorrecto " + e.getMessage());
        }
        return oOpcion;
    }

//    public OpcionBean loadTipoOpcion(OpcionBean oOpcion) throws NumberFormatException {
//        try {
//            if ((request.getParameter("id_tipoopcion") != null)) {
//                TipoopcionBean oTipoopcion = new TipoopcionBean(Integer.parseInt(request.getParameter("id_tipoopcion")));
//                oOpcion.setTipoOpcion(oTipoopcion);
//            }
//        } catch (NumberFormatException e) {
//            throw new NumberFormatException("Controller: Error: loadTipoOpcion: Formato de datos en parámetros incorrecto " + e.getMessage());
//        }
//        return oOpcion;
//    }
}
