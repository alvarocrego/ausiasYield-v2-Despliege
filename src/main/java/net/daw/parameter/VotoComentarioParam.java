/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.parameter;

import javax.servlet.http.HttpServletRequest;
import net.daw.bean.VotoComentarioBean;

/**
 *
 * @author Diana Ortega
 */
public class VotoComentarioParam {
    
      private HttpServletRequest request;

    public VotoComentarioParam(HttpServletRequest request) throws Exception {
        this.request = request;
    }

    public VotoComentarioBean loadId(VotoComentarioBean oVotoComentarioBean) throws NumberFormatException {
        try {
            if (request.getParameter("id") != null) {
                oVotoComentarioBean.setId(Integer.parseInt(request.getParameter("id")));
            } else {
                oVotoComentarioBean.setId(0);
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Controller: Error: Load: Formato de datos en parámetros incorrecto " + e.getMessage());
        }
        return oVotoComentarioBean;
    }

    public VotoComentarioBean load(VotoComentarioBean oVotoComentarioBean) throws NumberFormatException {
        try {
            if ((request.getParameter("id_usuario") != null)) {
                oVotoComentarioBean.getId_usuario().setId(Integer.parseInt(request.getParameter("id_usuario")));
            }
            if ((request.getParameter("id_comentario") != null)) {
               oVotoComentarioBean.getId_comentario().setId(Integer.parseInt(request.getParameter("id_comentario")));
            }
            if ((request.getParameter("valor") != null)) {
                oVotoComentarioBean.setValor(Integer.parseInt(request.getParameter("valor")));
            }
           
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Controller: Error: Load: Formato de datos en parámetros incorrecto " + e.getMessage());
        }
        return oVotoComentarioBean;
    }

    
}
    

