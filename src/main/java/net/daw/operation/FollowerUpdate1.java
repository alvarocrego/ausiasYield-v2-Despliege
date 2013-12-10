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
public class FollowerUpdate1 implements Operation {

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
        if (tipoUsuario.equals(net.daw.helper.Enum.TipoUsuario.Profesor)) {
//            try {
//                oFollowerBean = oFollowerDao.get(oFollowerBean);
//            } catch (Exception e) {
//                throw new ServletException("FollowerController: Update Error: Phase 1: " + e.getMessage());
//            }
            try {
                oFollowerBean = oFollowerParam.load(oFollowerBean);

                //sacar de la bd los dos usuarios del follower
                //sólo si vengo del listado de followers -> cargar
                //si vengo de elegitr usuarios entonces no cargar
                if (oFollowerBean.getUsuario1().getId() != 0 && oFollowerBean.getUsuario2().getId() != 0) {
                    oFollowerDao.get(oFollowerBean);
                }

                UsuarioDao oUsuarioDao = new UsuarioDao(oContexto.getEnumTipoConexion());
                oFollowerBean.setUsuario1(oUsuarioDao.get(oFollowerBean.getUsuario1()));
                oFollowerBean.setUsuario2(oUsuarioDao.get(oFollowerBean.getUsuario2()));

            } catch (NumberFormatException e) {
                oContexto.setVista("jsp/mensaje.jsp");
                return "Tipo de dato incorrecto en uno de los campos del formulario";
            }
            return oFollowerBean;
        } else {
            //Mostramos el MENSAJE
            oContexto.setVista("jsp/mensaje.jsp");
            return "<span class=\"label label-important\">¡¡¡ No estás autorizado a entrar aquí !!!<span>";

        }
    }

}
