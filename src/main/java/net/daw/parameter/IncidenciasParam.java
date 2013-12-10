/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.parameter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import net.daw.bean.IncidenciasBean;

/**
 *
 * @author al037431
 */
public class IncidenciasParam {

    private HttpServletRequest request;

    public IncidenciasParam(HttpServletRequest request) throws Exception {
        this.request = request;
    }

    public IncidenciasBean loadId(IncidenciasBean oIncidencias) throws NumberFormatException {
        try {
            if (request.getParameter("id") != null) {
                oIncidencias.setId(Integer.parseInt(request.getParameter("id")));
            } else {
                oIncidencias.setId(0);
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Controller: Error: Load: Formato de datos en parámetros incorrecto " + e.getMessage());
        }
        return oIncidencias;
    }

    public IncidenciasBean load(IncidenciasBean oIncidencias) throws NumberFormatException, ParseException {
        try {
            if ((request.getParameter("id") != null)) {
                oIncidencias.setId(Integer.parseInt(request.getParameter("id")));
            }
            if ((request.getParameter("resumen") != null)) {
                oIncidencias.setResumen(request.getParameter("resumen"));
            }
            if ((request.getParameter("cambios") != null)) {
                oIncidencias.setCambios(request.getParameter("cambios"));
            }

            if ((request.getParameter("id_estado") != null)) {
                oIncidencias.getEstado().setId(Integer.parseInt(request.getParameter("id_estado")));
            }

            if ((request.getParameter("id_repositorio") != null)) {
                oIncidencias.getRepositorio().setId(Integer.parseInt(request.getParameter("id_repositorio")));
            }
            if ((request.getParameter("id_usuario") != null)) {
                oIncidencias.getUsuario().setId(Integer.parseInt(request.getParameter("id_usuario")));
            }
            
           
           if ((request.getParameter("fechaAlta") != null)) {
                oIncidencias.setFechaAlta(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("fechaAlta")));
            }
           
            if ((request.getParameter("fechaResolucion") != null)) {
                oIncidencias.setFechaResolucion(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("fechaResolucion")));
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Controller: Error: Load: Formato de datos en parámetros incorrecto " + e.getMessage());
        }
        return oIncidencias;
    }
}
