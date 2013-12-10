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
public class MetadocumentoNew2 implements Operation{

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/mensaje.jsp");
        MetadocumentoBean oMetadocumentoBean = new MetadocumentoBean();
        MetadocumentoDao oMetadocumentoDao = new MetadocumentoDao(oContexto.getEnumTipoConexion());
        MetadocumentoParam oMetadocumentoParam = new MetadocumentoParam(request);
        oMetadocumentoBean = oMetadocumentoParam.loadId(oMetadocumentoBean);
        try {
            oMetadocumentoBean = oMetadocumentoParam.load(oMetadocumentoBean);
        } catch (NumberFormatException e) {
            return "Tipo de dato incorrecto en uno de los campos del formulario";
        }
        try {
            oMetadocumentoDao.set(oMetadocumentoBean);
        } catch (Exception e) {
            throw new ServletException("MetadocumentoController: Update Error: Phase 2: " + e.getMessage());
        }
        return "Se ha añadido la información del cliente con id=" + Integer.toString(oMetadocumentoBean.getId());
    }
}
