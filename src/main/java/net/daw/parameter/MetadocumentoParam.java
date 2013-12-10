/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.parameter;

import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import net.daw.bean.MetadocumentoBean;

/**
 *
 * @author Alvaro
 */
public class MetadocumentoParam {

    private HttpServletRequest request;

    public MetadocumentoParam(HttpServletRequest request) throws Exception {
        this.request = request;
    }

    public MetadocumentoBean loadId(MetadocumentoBean oMetadocumentoBean) throws NumberFormatException {
        try {
            if (request.getParameter("id") != null) {
                oMetadocumentoBean.setId(Integer.parseInt(request.getParameter("id")));
            } else {
                oMetadocumentoBean.setId(0);
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Controller: Error: Load: Formato de datos en parámetros incorrecto " + e.getMessage());
        }
        return oMetadocumentoBean;
    }

    public MetadocumentoBean load(MetadocumentoBean oMetadocumentoBean) throws NumberFormatException {
        try {
            if ((request.getParameter("titulo") != null)) {
                oMetadocumentoBean.setTitulo(request.getParameter("titulo"));
                
            }
            if ((request.getParameter("fecha") != null)) {
            oMetadocumentoBean.setFecha(request.getParameter("fecha"));
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Controller: Error: Load: Formato de datos en parámetros incorrecto " + e.getMessage());
        }
        return oMetadocumentoBean;
    }

}
