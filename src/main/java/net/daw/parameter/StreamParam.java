/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author al037721
 */

package net.daw.parameter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import net.daw.bean.StreamBean;

/**
 *
 * @author rafa
 */
public class StreamParam {

    private final HttpServletRequest request;

    public StreamParam(HttpServletRequest request) throws Exception {
        this.request = request;
    }

    public StreamBean loadId(StreamBean oStream) throws NumberFormatException {
        try {
            if (request.getParameter("id") != null) {
                oStream.setId(Integer.parseInt(request.getParameter("id")));
            } else {
                oStream.setId(0);
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Controller: Error: Load: Formato de datos en parámetros incorrecto " + e.getMessage());
        }
        return oStream;
    }

    public StreamBean load(StreamBean oStream) throws NumberFormatException, ParseException {
        try {
            if ((request.getParameter("contenido") != null)) {
                oStream.setContenido(request.getParameter("contenido"));
            }
            if ((request.getParameter("fecha") != null)) {
                SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
                Date fecha = formatoFecha.parse(request.getParameter("fecha"));
                oStream.setFecha(fecha);
            }
            if ((request.getParameter("id_usuario") != null)) {
                oStream.getUsuario().setId(Integer.parseInt(request.getParameter("id_usuario")));
            }
            
            
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Controller: Error: Load: Formato de datos en parámetros incorrecto " + e.getMessage());
        } catch (ParseException e) {
            throw new NumberFormatException("Controller: Error: Load: Formato de fecha en parámetros incorrecto " + e.getMessage());
        }
        return oStream;
    }
}