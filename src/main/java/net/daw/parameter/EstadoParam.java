/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.daw.parameter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import net.daw.bean.EstadoBean;

/**
 *
 * @author Diana
 */
public class EstadoParam {
    
      private HttpServletRequest request;

    public EstadoParam(HttpServletRequest request) throws Exception {
        this.request = request;
    }

    public EstadoBean loadId(EstadoBean oEstadoBean) throws NumberFormatException {
        try {
            if (request.getParameter("id") != null) {
                oEstadoBean.setId(Integer.parseInt(request.getParameter("id")));
            } else {
                oEstadoBean.setId(0);
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Controller: Error: Load: Formato de datos en parámetros incorrecto " + e.getMessage());
        }
        return oEstadoBean;
    }

    public EstadoBean load(EstadoBean oEstadoBean) throws NumberFormatException {
        try {
            if ((request.getParameter("nombre") != null)) {
                oEstadoBean.setNombre(request.getParameter("nombre"));
            }
           
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Controller: Error: Load: Formato de datos en parámetros incorrecto " + e.getMessage());
        }
        return oEstadoBean;
    }

    
}
