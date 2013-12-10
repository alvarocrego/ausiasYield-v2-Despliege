/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.daw.parameter;

import java.text.ParseException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import net.daw.bean.MetadocumentosBean;

/**
 *
 * @author Alvaro Crego
 */
public class MetadocumentosParam {
    
     private final HttpServletRequest request;

    public MetadocumentosParam(HttpServletRequest request) throws Exception {
        this.request = request;
    }

    public MetadocumentosBean loadId(MetadocumentosBean oMetadocumentos) throws ServletException {
        try {
            if (request.getParameter("id") != null) {
                oMetadocumentos.setId(Integer.parseInt(request.getParameter("id")));
            } else {
                oMetadocumentos.setId(0);
            }
        } catch (NumberFormatException e) {
            throw new ServletException("Controller: Error: loadId: Formato de datos en parámetros incorrecto " + e.getMessage());
        }
        return oMetadocumentos;

    }

    public MetadocumentosBean load(MetadocumentosBean oMetadocumentos) throws NumberFormatException, ParseException {
        try {
            if ((request.getParameter("id_metadocumento") != null)) {
                oMetadocumentos.getMetadocumento().setId(Integer.parseInt(request.getParameter("id_metadocumento")));
            }
            if ((request.getParameter("id_documento") != null)) {
                oMetadocumentos.getDocumento().setId(Integer.parseInt(request.getParameter("id_documento")));
            }
            if ((request.getParameter("orden") != null)) {
                oMetadocumentos.setOrden(Integer.parseInt(request.getParameter("orden")));
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException("MetadocumentosParam: Error: load: Formato de datos en parámetros incorrecto " + e.getMessage());
        }
        return oMetadocumentos;
    }
}
