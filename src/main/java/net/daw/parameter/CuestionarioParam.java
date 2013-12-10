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
import net.daw.bean.CuestionarioBean;

/**
 *
 * @author rafa
 */
public class CuestionarioParam {

    private final HttpServletRequest request;

    public CuestionarioParam(HttpServletRequest request) throws Exception {
        this.request = request;
    }

    public CuestionarioBean loadId(CuestionarioBean oCuestionario) throws NumberFormatException {
        try {
            if (request.getParameter("id") != null) {
                oCuestionario.setId(Integer.parseInt(request.getParameter("id")));
            } else {
                oCuestionario.setId(0);
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Controller: Error: Load: Formato de datos en parámetros incorrecto " + e.getMessage());
        }
        return oCuestionario;
    }

    public CuestionarioBean load(CuestionarioBean oCuestionario) throws NumberFormatException, ParseException {
        try {
            if ((request.getParameter("descripcion") != null)) {
                oCuestionario.setDescripcion(request.getParameter("descripcion"));
            }
            if ((request.getParameter("fecha") != null)) {
                SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
                Date fecha = formatoFecha.parse(request.getParameter("fecha"));
                oCuestionario.setFecha(fecha);
            }
            if ((request.getParameter("evaluacion") != null)) {
                oCuestionario.setEvaluacion(Integer.parseInt(request.getParameter("evaluacion")));
            }
            if ((request.getParameter("activo") != null)) {
                String activo = request.getParameter("activo");
                oCuestionario.setActivo( activo.equals("true") );
            }
            
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Controller: Error: Load: Formato de datos en parámetros incorrecto " + e.getMessage());
        } catch (ParseException e) {
            throw new NumberFormatException("Controller: Error: Load: Formato de fecha en parámetros incorrecto " + e.getMessage());
        }
        return oCuestionario;
    }
}