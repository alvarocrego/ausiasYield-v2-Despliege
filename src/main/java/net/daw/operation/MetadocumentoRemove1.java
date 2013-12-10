/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.operation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.MetadocumentoBean;
import net.daw.helper.Contexto;
import net.daw.parameter.MetadocumentoParam;

/**
 *
 * @author al037431
 */
public class MetadocumentoRemove1 implements Operation{
     @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/confirmForm.jsp");
        MetadocumentoBean oMetadocumentoBean = new MetadocumentoBean();   
        MetadocumentoParam oMetadocumentoParam = new MetadocumentoParam(request);
        oMetadocumentoBean = oMetadocumentoParam.loadId(oMetadocumentoBean);
        return "Borrar el Metadocumento " + oMetadocumentoBean.getId();
    }
}
