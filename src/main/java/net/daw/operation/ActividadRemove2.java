/*
 * To change this template, choose Tools | Templates
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

import net.daw.bean.ActividadBean;
import net.daw.bean.UsuarioBean;
import net.daw.dao.ActividadDao;
import net.daw.helper.Contexto;
import net.daw.parameter.ActividadParam;

public class ActividadRemove2 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");

        //Parte para saber el tipo de usuario
        UsuarioBean oUsuarioBean;
        oUsuarioBean = (UsuarioBean) request.getSession().getAttribute("usuarioBean");
        java.lang.Enum tipoUsuario = oUsuarioBean.getTipoUsuario();
        //

        oContexto.setVista("jsp/mensaje.jsp");
        ActividadBean oActividadBean = new ActividadBean();
        ActividadParam oActividadParam = new ActividadParam(request);
        oActividadBean = oActividadParam.loadId(oActividadBean);

        //Validacion
        if (tipoUsuario.equals(net.daw.helper.Enum.TipoUsuario.Profesor)) {

            try {
                ActividadDao oActividadDAO = new ActividadDao(oContexto.getEnumTipoConexion());
                oActividadDAO.remove(oActividadBean);
            } catch (Exception e) {
                throw new ServletException("ActividadController: Remove Error: " + e.getMessage());
            }
            String Mensaje = ("Se ha eliminado la información de la actividad con id=" + Integer.toString(oActividadBean.getId()));
            return Mensaje;
        } else {
            //Mostramos el MENSAJE
            oContexto.setVista("jsp/mensaje.jsp");
            return "<span class=\"label label-important\">¡¡¡ No estás autorizado a entrar aquí !!!<span>";

        }

    }
}