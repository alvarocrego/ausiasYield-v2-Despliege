/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.parameter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import net.daw.bean.ActividadBean;

/**
 *
 * @author rafa
 */
public class ActividadParam {

    private final HttpServletRequest request;

    public ActividadParam(HttpServletRequest request) throws Exception {
        this.request = request;
    }

    public ActividadBean loadId(ActividadBean oActividad) throws NumberFormatException {
        try {
            if (request.getParameter("id") != null) {
                oActividad.setId(Integer.parseInt(request.getParameter("id")));
            } else {
                oActividad.setId(0);
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Controller: Error: Load: Formato de datos en parámetros incorrecto " + e.getMessage());
        }
        return oActividad;
    }

    public ActividadBean load(ActividadBean oActividad) throws NumberFormatException, ParseException {
        try {
            if ((request.getParameter("enunciado") != null)) {
                oActividad.setEnunciado(request.getParameter("enunciado"));
            }
            if ((request.getParameter("fecha") != null)) {
                SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
                Date fecha = formatoFecha.parse(request.getParameter("fecha"));
                oActividad.setFecha(fecha);
            }
            if ((request.getParameter("evaluacion") != null)) {
                oActividad.setEvaluacion(Integer.parseInt(request.getParameter("evaluacion")));
            }
            if ((request.getParameter("activo") != null)) {
                String activo = request.getParameter("activo");
                oActividad.setActivo( activo.equals("true") );
            }
            
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Controller: Error: Load: Formato de datos en parámetros incorrecto " + e.getMessage());
        } catch (ParseException e) {
            throw new NumberFormatException("Controller: Error: Load: Formato de fecha en parámetros incorrecto " + e.getMessage());
        }
        return oActividad;
    }
}