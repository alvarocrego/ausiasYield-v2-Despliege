/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.operation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.ComentBean;
import net.daw.dao.ComentDao;
import net.daw.dao.DocumentoDao;
import net.daw.dao.UsuarioDao;
import net.daw.helper.Contexto;
import net.daw.parameter.ComentParam;

/**
 *
 * @author Jordi Eslava Barrera
 */
public class ComentUpdate1 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/comentario/form.jsp");
        ComentBean oComentBean;
        ComentDao oComentDao;
        oComentBean = new ComentBean();
        ComentParam oComentParam = new ComentParam(request);
        oComentBean = oComentParam.loadId(oComentBean);
        oComentDao = new ComentDao(oContexto.getEnumTipoConexion());
        try {
            oComentBean = oComentDao.get(oComentBean);
        } catch (Exception e) {
            throw new ServletException("ComentController: Update Error: Phase 1: " + e.getMessage());
        }
        try {
            oComentBean = oComentParam.load(oComentBean);
        } catch (NumberFormatException e) {
            oContexto.setVista("jsp/mensaje.jsp");
            return "Tipo de dato incorrecto en uno de los campos del formulario";
        }
        DocumentoDao oDocumentoDao = new DocumentoDao(oContexto.getEnumTipoConexion());
        UsuarioDao oUsuarioDao = new UsuarioDao(oContexto.getEnumTipoConexion());
        oComentBean.setId_documento(oDocumentoDao.get(oComentBean.getId_documento()));
        oComentBean.setId_usuario(oUsuarioDao.get(oComentBean.getId_usuario()));

        return oComentBean;
    }
}
