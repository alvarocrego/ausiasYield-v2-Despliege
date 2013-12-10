/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.daw.operation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.DocumentoBean;
import net.daw.dao.UsuarioDao;
import net.daw.helper.Contexto;
import net.daw.parameter.DocumentoParam;

/**
 *
 * @author Alvaro
 */
public class DocumentoNew1 implements Operation{
    
    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        DocumentoParam oDocumentoParam = new DocumentoParam(request);
        DocumentoBean oDocumentoBean = new DocumentoBean();
        UsuarioDao oUsuarioDao = new UsuarioDao(oContexto.getEnumTipoConexion());
        try {
            oDocumentoBean = oDocumentoParam.load(oDocumentoBean);
            oDocumentoBean.setUsuario(oUsuarioDao.get(oDocumentoBean.getUsuario()));

        } catch (NumberFormatException e) {
            oContexto.setVista("jsp/mensaje.jsp");
            return "<div class=\"alert alert-error\">Tipo de dato incorrecto en uno de los campos del formulario</div>";
        }
        oContexto.setVista("jsp/documento/form.jsp");
        return oDocumentoBean;
    }
    
}
