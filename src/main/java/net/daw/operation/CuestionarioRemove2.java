/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.operation;

/**
 *
 * @author al037213
 */
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.daw.bean.CuestionarioBean;
import net.daw.bean.UsuarioBean;
import net.daw.dao.CuestionarioDao;
import net.daw.helper.Contexto;
import net.daw.parameter.CuestionarioParam;

public class CuestionarioRemove2 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");

        //Parte para saber el tipo de usuario
        UsuarioBean oUsuarioBean;
        oUsuarioBean = (UsuarioBean) request.getSession().getAttribute("usuarioBean");
        java.lang.Enum tipoUsuario = oUsuarioBean.getTipoUsuario();
        //

        oContexto.setVista("jsp/mensaje.jsp");
        CuestionarioBean oCuestionarioBean = new CuestionarioBean();
        CuestionarioParam oCuestionarioParam = new CuestionarioParam(request);
        oCuestionarioBean = oCuestionarioParam.loadId(oCuestionarioBean);

        //Validacion
        if (tipoUsuario.equals(net.daw.helper.Enum.TipoUsuario.Profesor)) {

            try {
                CuestionarioDao oCuestionarioDAO = new CuestionarioDao(oContexto.getEnumTipoConexion());
                oCuestionarioDAO.remove(oCuestionarioBean);
            } catch (Exception e) {
                throw new ServletException("CuestionarioController: Remove Error: " + e.getMessage());
            }
            String Mensaje = ("Se ha eliminado la información del cuestionario con id=" + Integer.toString(oCuestionarioBean.getId()));
            return Mensaje;
        } else {
            //Mostramos el MENSAJE
            oContexto.setVista("jsp/mensaje.jsp");
            return "<span class=\"label label-important\">¡¡¡ No estás autorizado a entrar aquí !!!<span>";

        }

    }
}
