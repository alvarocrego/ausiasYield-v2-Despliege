/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.parameter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import net.daw.bean.RequerimientoBean;

/**
 *
 * @author Jordi Eslava Barrera
 */
public class RequerimientoParam {
private HttpServletRequest request;

    public RequerimientoParam(HttpServletRequest request) throws Exception {
        this.request = request;
    }

    public RequerimientoBean loadId(RequerimientoBean oRequerimiento) throws NumberFormatException {
        try {
            if (request.getParameter("id") != null) {
                oRequerimiento.setId(Integer.parseInt(request.getParameter("id")));
            } else {
                oRequerimiento.setId(0);
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Controller: Error: Load: Formato de datos en parámetros incorrecto " + e.getMessage());
        }
        return oRequerimiento;
    }

    public RequerimientoBean load(RequerimientoBean oRequerimiento) throws NumberFormatException, ParseException {
        try {
            if ((request.getParameter("id") != null)) {
                oRequerimiento.setId(Integer.parseInt(request.getParameter("id")));
            }
            if ((request.getParameter("enunciado") != null)) {
                oRequerimiento.setEnunciado(request.getParameter("enunciado"));
            }
            if ((request.getParameter("fechaalta") != null) && (request.getParameter("fechaalta")) != "") {
                oRequerimiento.setFecha(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("fechaalta")));
            }

        } catch (NumberFormatException e) {
            throw new NumberFormatException("RequerimientoParam: Error: Load: Formato de datos en parámetros incorrecto " + e.getMessage());
        }
        return oRequerimiento;
    }
    

}
