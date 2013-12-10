/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.operation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.MetadocumentoBean;
import net.daw.dao.MetadocumentoDao;
import net.daw.helper.Contexto;
import net.daw.parameter.MetadocumentoParam;

/**
 *
 * @author al037431
 */
public class MetadocumentoRemove2 implements Operation{
    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/mensaje.jsp");   
        MetadocumentoBean oMetadocumentoBean = new MetadocumentoBean(); 
        MetadocumentoParam oMetadocumentoParam = new MetadocumentoParam(request);
        oMetadocumentoBean = oMetadocumentoParam.loadId(oMetadocumentoBean);
        try {
            MetadocumentoDao oMetadocumentoDao = new MetadocumentoDao(oContexto.getEnumTipoConexion());
            oMetadocumentoDao.remove(oMetadocumentoBean);
        } catch (Exception e) {
            throw new ServletException("MetadocumentoController: Remove Error: " + e.getMessage());
        }
        String Mensaje = ("Se ha eliminado la información del repositorio con id=" + Integer.toString(oMetadocumentoBean.getId()));
        return Mensaje;
    }
    
}
