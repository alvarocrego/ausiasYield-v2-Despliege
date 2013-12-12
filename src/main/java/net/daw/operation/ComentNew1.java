/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.operation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.ComentBean;
import net.daw.dao.DocumentoDao;
import net.daw.dao.UsuarioDao;
import net.daw.helper.Contexto;
import net.daw.parameter.ComentParam;

/**
 *
 * @author Jordi Eslava Barrera
 */
public class ComentNew1 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        ComentParam oComentParam = new ComentParam(request);
        ComentBean oComentBean = new ComentBean();
        DocumentoDao oDocumentoDao = new DocumentoDao(oContexto.getEnumTipoConexion());
        UsuarioDao oUsuarioDao = new UsuarioDao(oContexto.getEnumTipoConexion());
        try {
            oComentBean = oComentParam.load(oComentBean);
            oComentBean.setId_documento(oDocumentoDao.get(oComentBean.getId_documento()));
            oComentBean.setId_usuario(oUsuarioDao.get(oComentBean.getId_usuario()));
        } catch (NumberFormatException e) {
            oContexto.setVista("jsp/mensaje.jsp");
            return "Tipo de dato incorrecto en uno de los campos del formulario";
        }
        oContexto.setVista("jsp/comentario/form.jsp");
        return oComentBean;
    }
}
