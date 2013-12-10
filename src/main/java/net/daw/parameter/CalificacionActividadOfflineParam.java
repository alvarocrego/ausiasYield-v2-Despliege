/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.parameter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import net.daw.bean.CalificacionActividadOfflineBean;

/**
 *
 * @author al037805
 */
public class CalificacionActividadOfflineParam {
    
    private final HttpServletRequest request;

    public CalificacionActividadOfflineParam(HttpServletRequest request) throws Exception {
        this.request = request;
    }

    public CalificacionActividadOfflineBean loadId(CalificacionActividadOfflineBean oCalificacionActividadOffline) throws NumberFormatException, ServletException {
        try {
            if (request.getParameter("id") != null) {
                oCalificacionActividadOffline.setId(Integer.parseInt(request.getParameter("id")));
            } else {
                oCalificacionActividadOffline.setId(0);
            }
        } catch (NumberFormatException e) {
            throw new ServletException("Controller: Error: loadId: Formato de datos en parámetros incorrecto " + e.getMessage());
        }
        return oCalificacionActividadOffline;
    }

    public CalificacionActividadOfflineBean load(CalificacionActividadOfflineBean oCalificacionActividadOffline) throws NumberFormatException, ParseException {
        try {
            if ((request.getParameter("id_usuario") != null)) {
                oCalificacionActividadOffline.getUsuario().setId(Integer.parseInt(request.getParameter("id_usuario")));
            }
            if ((request.getParameter("fecha") != null)) {
                SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
                Date fecha = formatoFecha.parse(request.getParameter("fecha"));
                oCalificacionActividadOffline.setFecha(fecha);
            }
            if ((request.getParameter("id_actividadoffline") != null)) {
                oCalificacionActividadOffline.getActividad_offline().setId(Integer.parseInt(request.getParameter("id_actividadoffline")));
            }
            
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Controller: Error: Load: Formato de datos en parámetros incorrecto " + e.getMessage());
        } catch (ParseException e) {
            throw new NumberFormatException("Controller: Error: Load: Formato de fecha en parámetros incorrecto " + e.getMessage());
        }
        return oCalificacionActividadOffline;
    }
    
}
