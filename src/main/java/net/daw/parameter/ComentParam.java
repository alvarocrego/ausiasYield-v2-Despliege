/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.parameter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import net.daw.bean.ComentBean;

/**
 *
 * @author Jordi Eslava Barrera
 */
public class ComentParam {

    private HttpServletRequest request;

    public ComentParam(HttpServletRequest request) throws Exception {
        this.request = request;
    }

    public ComentBean loadId(ComentBean oComent) throws NumberFormatException {
        try {
            if (request.getParameter("id") != null) {
                oComent.setId(Integer.parseInt(request.getParameter("id")));
            } else {
                oComent.setId(0);
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Controller: Error: Load: Formato de datos en parámetros incorrecto " + e.getMessage());
        }
        return oComent;
    }

    public ComentBean load(ComentBean oComent) throws NumberFormatException, ParseException {
        try {
            if ((request.getParameter("id") != null)) {
                oComent.setId(Integer.parseInt(request.getParameter("id")));
            }
            if ((request.getParameter("titulo") != null)) {
                oComent.setTitulo(request.getParameter("titulo"));
            }
            if ((request.getParameter("contenido") != null)) {
                oComent.setContenido(request.getParameter("contenido"));
            }
            if ((request.getParameter("fecha") != null)) {
                oComent.setFecha(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("fecha")));
            }
            if ((request.getParameter("id_usuario") != null)) {
                oComent.getId_usuario().setId(Integer.parseInt(request.getParameter("id_usuario")));
            }
            if ((request.getParameter("id_documento") != null)) {
                oComent.getId_documento().setId(Integer.parseInt(request.getParameter("id_documento")));
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException("ComentParam: Error: Load: Formato de datos en parámetros incorrecto " + e.getMessage());
        }
        return oComent;
    }
}
