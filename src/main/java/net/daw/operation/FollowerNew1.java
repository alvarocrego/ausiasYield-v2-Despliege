/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.operation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.FollowerBean;
import net.daw.bean.UsuarioBean;
import net.daw.dao.UsuarioDao;
import net.daw.helper.Contexto;
import net.daw.parameter.FollowerParam;

/**
 *
 * @author al037570
 */
public class FollowerNew1 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");

        //Parte para saber el tipo de usuario
        UsuarioBean oUsuarioBean;
        oUsuarioBean = (UsuarioBean) request.getSession().getAttribute("usuarioBean");
        java.lang.Enum tipoUsuario = oUsuarioBean.getTipoUsuario();
        //

        FollowerParam oFollowerParam = new FollowerParam(request);
        FollowerBean oFollowerBean = new FollowerBean();
        UsuarioDao oUsuarioDao1 = new UsuarioDao(oContexto.getEnumTipoConexion());
        UsuarioDao oUsuarioDao2 = new UsuarioDao(oContexto.getEnumTipoConexion());
        //Validacion
        if (tipoUsuario.equals(net.daw.helper.Enum.TipoUsuario.Profesor)) {

            try {
                oFollowerBean = oFollowerParam.load(oFollowerBean);
                oFollowerBean.setUsuario1(oUsuarioDao1.get(oFollowerBean.getUsuario1()));
                oFollowerBean = oFollowerParam.load(oFollowerBean);
                oFollowerBean.setUsuario2(oUsuarioDao2.get(oFollowerBean.getUsuario2()));
            } catch (NumberFormatException e) {
                oContexto.setVista("jsp/mensaje.jsp");
                return "Tipo de dato incorrecto en uno de los campos del formulario";
            }
            oContexto.setVista("jsp/follower/form.jsp");
            return oFollowerBean;
        } else {
            //Mostramos el MENSAJE
            oContexto.setVista("jsp/mensaje.jsp");
            return "<span class=\"label label-important\">¡¡¡ No estás autorizado a entrar aquí !!!<span>";

        }

    }

}
