/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.parameter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import net.daw.bean.BolsaBean;

/**
 *
 * @author al037877
 */
public class BolsaParam {
    
      private HttpServletRequest request;

    public BolsaParam(HttpServletRequest request) throws Exception {
        this.request = request;
    }

    public BolsaBean loadId(BolsaBean oBolsaBean) throws NumberFormatException {
        try {
            if (request.getParameter("id") != null) {
                oBolsaBean.setId(Integer.parseInt(request.getParameter("id")));
            } else {
                oBolsaBean.setId(0);
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Controller: Error: Load: Formato de datos en parámetros incorrecto " + e.getMessage());
        }
        return oBolsaBean;
    }

    public BolsaBean load(BolsaBean oBolsaBean) throws NumberFormatException {
        try {
            if ((request.getParameter("id_documento1") != null)) {
                oBolsaBean.getDocumento1().setId(Integer.parseInt(request.getParameter("id_documento1")));
            }
            if ((request.getParameter("id_documento2") != null)) {
                oBolsaBean.getDocumento2().setId(Integer.parseInt(request.getParameter("id_documento2")));
            }
            if ((request.getParameter("fecha") != null) && (request.getParameter("fecha") != "")) {
                Date dFecha = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("fecha"));
                oBolsaBean.setFecha(dFecha);
            }
           
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Controller: Error: Load: Formato de datos en parámetros incorrecto " + e.getMessage());
        } catch (ParseException ex) {
              Logger.getLogger(BolsaParam.class.getName()).log(Level.SEVERE, null, ex);
          }
        return oBolsaBean;
    }
    
    
}
