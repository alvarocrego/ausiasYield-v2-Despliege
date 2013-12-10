/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.operation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.FollowerBean;
import net.daw.bean.UsuarioBean;
import net.daw.dao.FollowerDao;
import net.daw.dao.UsuarioDao;
import net.daw.helper.Contexto;
import net.daw.parameter.FollowerParam;

/**
 *
 * @author al037570
 */
public class FollowerView1 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        //Parte para saber el tipo de usuario
        UsuarioBean oUsuarioBean;
        oUsuarioBean = (UsuarioBean) request.getSession().getAttribute("usuarioBean");
        java.lang.Enum tipoUsuario = oUsuarioBean.getTipoUsuario();
        //

        oContexto.setVista("jsp/follower/form.jsp");
        FollowerBean oFollowerBean;
        FollowerDao oFollowerDao;
        oFollowerBean = new FollowerBean();
        FollowerParam oFollowerParam = new FollowerParam(request);
        oFollowerBean = oFollowerParam.loadId(oFollowerBean);
        oFollowerDao = new FollowerDao(oContexto.getEnumTipoConexion());
        UsuarioDao oUsuarioDao1 = new UsuarioDao(oContexto.getEnumTipoConexion());
        UsuarioDao oUsuarioDao2 = new UsuarioDao(oContexto.getEnumTipoConexion());
     
        //Validacion
        if (tipoUsuario.equals(net.daw.helper.Enum.TipoUsuario.Profesor)
                || tipoUsuario.equals(net.daw.helper.Enum.TipoUsuario.Alumno)) {
            try {
                oFollowerBean = oFollowerDao.get(oFollowerBean);
                oFollowerBean.setUsuario1(oUsuarioDao1.get(oFollowerBean.getUsuario1()));
                oFollowerBean.setUsuario2(oUsuarioDao2.get(oFollowerBean.getUsuario2()));
            } catch (Exception e) {
                throw new ServletException("UsuarioController: View Error: Phase 1: " + e.getMessage());
            }

            return oFollowerBean;
        } else {
            //Mostramos el MENSAJE
            oContexto.setVista("jsp/mensaje.jsp");
            return "<span class=\"label label-important\">¡¡¡ No estás autorizado a entrar aquí !!!<span>";
        }
    }
}
