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
public class FollowerUpdate2 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");

        //Parte para saber el tipo de usuario
        UsuarioBean oUsuarioBean;
        oUsuarioBean = (UsuarioBean) request.getSession().getAttribute("usuarioBean");
        java.lang.Enum tipoUsuario = oUsuarioBean.getTipoUsuario();
        //
        //Validacion
        if (tipoUsuario.equals(net.daw.helper.Enum.TipoUsuario.Profesor)) {

            switch (oContexto.getSearchingFor()) {
                case "usuario1": {
                    oContexto.setVista("jsp/usuario/list.jsp");
                    oContexto.setClase("usuario");
                    oContexto.setMetodo("list");
                    oContexto.setFase("1");
                    oContexto.setSearchingFor("usuario1");
                    oContexto.setClaseRetorno("follower");
                    oContexto.setMetodoRetorno("update");
                    oContexto.setFaseRetorno("1");
                    oContexto.removeParam("id_usuario1");
                    UsuarioList1 oOperacion = new UsuarioList1();
                    return oOperacion.execute(request, response);
                }
                case "usuario2": {
                    oContexto.setVista("jsp/usuario/list.jsp");
                    oContexto.setClase("usuario");
                    oContexto.setMetodo("list");
                    oContexto.setFase("1");
                    oContexto.setSearchingFor("usuario2");
                    oContexto.setClaseRetorno("follower");
                    oContexto.setMetodoRetorno("update");
                    oContexto.setFaseRetorno("1");
                    oContexto.removeParam("id_usuario2");
                    UsuarioList1 oOperacion = new UsuarioList1();
                    return oOperacion.execute(request, response);
                }
                default:
                    oContexto.setVista("jsp/mensaje.jsp");
                    FollowerBean oFollowerBean = new FollowerBean();
                    FollowerDao oFollowerDao = new FollowerDao(oContexto.getEnumTipoConexion());
                    FollowerParam oFollowerParam = new FollowerParam(request);
                    oFollowerBean = oFollowerParam.loadId(oFollowerBean);
                    oFollowerBean = oFollowerDao.get(oFollowerBean);
                    try {
                        oFollowerBean = oFollowerParam.load(oFollowerBean);
                    } catch (NumberFormatException e) {
                        return "Tipo de dato incorrecto en uno de los campos del formulario";
                    }
                    try {
                        oFollowerDao.set(oFollowerBean);
                    } catch (Exception e) {
                        throw new ServletException("FollowerController: Update Error: Phase 2: " + e.getMessage());
                    }
                    String strMensaje = "Se ha modificado la información de la follower con id=" + Integer.toString(oFollowerBean.getId()) + "<br />";
                    strMensaje += "<a href=\"Controller?class=follower&method=view&id=" + oFollowerBean.getId() + "\">Ver follower modificada</a><br />";
                    return strMensaje;
            }
        } else {
            //Mostramos el MENSAJE
            oContexto.setVista("jsp/mensaje.jsp");
            return "<span class=\"label label-important\">¡¡¡ No estás autorizado a entrar aquí !!!<span>";

        }

    }

}
