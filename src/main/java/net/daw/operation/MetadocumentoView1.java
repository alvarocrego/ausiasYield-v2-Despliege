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
public class MetadocumentoView1 implements Operation{
    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/metadocumento/form.jsp");        
        MetadocumentoBean oMetadocumentoBean;
        MetadocumentoDao oMetadocumentoDao;
        oMetadocumentoBean = new MetadocumentoBean();
        MetadocumentoParam oMetadocumentoParam = new MetadocumentoParam(request);
        oMetadocumentoBean = oMetadocumentoParam.loadId(oMetadocumentoBean);
        oMetadocumentoDao = new MetadocumentoDao(oContexto.getEnumTipoConexion());
        try {
            oMetadocumentoBean = oMetadocumentoDao.get(oMetadocumentoBean);
        } catch (Exception e) {
            throw new ServletException("MetadocumentoController: View Error: Phase 1: " + e.getMessage());
        }
        oMetadocumentoBean = oMetadocumentoParam.load(oMetadocumentoBean);
        return oMetadocumentoBean;
    }
}
