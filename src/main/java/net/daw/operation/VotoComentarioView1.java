/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.operation;

import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.VotoComentarioBean;
import net.daw.dao.ComentDao;
import net.daw.dao.UsuarioDao;
import net.daw.dao.VotoComentarioDao;
import net.daw.helper.Contexto;
import net.daw.parameter.VotoComentarioParam;

/**
 *
 * @author Diana
 */
public class VotoComentarioView1 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/votocomentario/form.jsp");
        VotoComentarioBean oVotoComentarioBean;
        VotoComentarioDao oVotoComentarioDao;
        oVotoComentarioBean = new VotoComentarioBean();
        VotoComentarioParam oVotoComentarioParam = new VotoComentarioParam(request);
        oVotoComentarioBean = oVotoComentarioParam.loadId(oVotoComentarioBean);
        oVotoComentarioDao = new VotoComentarioDao(oContexto.getEnumTipoConexion());
        UsuarioDao oUsuarioDao = new UsuarioDao(oContexto.getEnumTipoConexion());
        ComentDao oComentDao = new ComentDao(oContexto.getEnumTipoConexion());
        try {
            oVotoComentarioBean = oVotoComentarioDao.get(oVotoComentarioBean);
            oVotoComentarioBean.setId_usuario(oUsuarioDao.get(oVotoComentarioBean.getId_usuario()));
            oVotoComentarioBean.setId_comentario(oComentDao.get(oVotoComentarioBean.getId_comentario()));
        } catch (Exception e) {
            throw new ServletException("Id_comentarioController: View Error: Phase 1: " + e.getMessage());
        }

        return oVotoComentarioBean;
    }

}
