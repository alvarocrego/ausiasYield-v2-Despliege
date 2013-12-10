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
import net.daw.helper.Contexto;
import net.daw.parameter.FollowerParam;

/**
 *
 * @author al037570
 */
public class FollowerRemove2 implements Operation{
    
        @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");

        //Parte para saber el tipo de usuario
        UsuarioBean oUsuarioBean;
        oUsuarioBean = (UsuarioBean) request.getSession().getAttribute("usuarioBean");
        java.lang.Enum tipoUsuario = oUsuarioBean.getTipoUsuario();
        //

        oContexto.setVista("jsp/mensaje.jsp");
        FollowerBean oFollowerBean = new FollowerBean();
        FollowerParam oFollowerParam = new FollowerParam(request);
        oFollowerBean = oFollowerParam.loadId(oFollowerBean);

        //Validacion
        if (tipoUsuario.equals(net.daw.helper.Enum.TipoUsuario.Profesor)) {

            try {
                FollowerDao oFollowerDAO = new FollowerDao(oContexto.getEnumTipoConexion());
                oFollowerDAO.remove(oFollowerBean);
            } catch (Exception e) {
                throw new ServletException("FollowerController: Remove Error: " + e.getMessage());
            }
            String strMensaje = "Se ha eliminado la información de la follower con id=" + Integer.toString(oFollowerBean.getId()) + "<br />";
            strMensaje += "<a href=\"Controller?class=follower&method=list\">Ir al listado de followers</a><br />";
            String Mensaje = strMensaje;
            return Mensaje;
        } else {
            //Mostramos el MENSAJE
            oContexto.setVista("jsp/mensaje.jsp");
            return "<span class=\"label label-important\">¡¡¡ No estás autorizado a entrar aquí !!!<span>";

        }
    }
    
}
