/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.daw.operation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.MetadocumentosBean;
import net.daw.dao.DocumentoDao;
import net.daw.dao.MetadocumentoDao;
import net.daw.helper.Contexto;
import net.daw.parameter.MetadocumentosParam;

/**
 *
 * @author Alvaro
 */
public class MetadocumentosNew1 implements Operation{
    
    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        MetadocumentosParam oMetadocumentosParam = new MetadocumentosParam(request);
        MetadocumentosBean oMetadocumentosBean = new MetadocumentosBean();
        DocumentoDao oDocumentoDao = new DocumentoDao(oContexto.getEnumTipoConexion());
        MetadocumentoDao oMetadocumentoDao = new MetadocumentoDao(oContexto.getEnumTipoConexion());
        try {
            oMetadocumentosBean = oMetadocumentosParam.load(oMetadocumentosBean);
            oMetadocumentosBean = oMetadocumentosParam.load(oMetadocumentosBean);
            oMetadocumentosBean.setDocumento(oDocumentoDao.get(oMetadocumentosBean.getDocumento()));
            oMetadocumentosBean.setMetadocumento(oMetadocumentoDao.get(oMetadocumentosBean.getMetadocumento()));
        } catch (NumberFormatException e) {
            oContexto.setVista("jsp/mensaje.jsp");
            return "Tipo de dato incorrecto en uno de los campos del formulario";
        }
        oContexto.setVista("jsp/metadocumentos/form.jsp");
        return oMetadocumentosBean;
    }
}
