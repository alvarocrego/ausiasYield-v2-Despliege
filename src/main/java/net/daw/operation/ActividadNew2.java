/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.operation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.daw.bean.ActividadBean;
import net.daw.bean.UsuarioBean;
import net.daw.dao.ActividadDao;
import net.daw.helper.Contexto;
import net.daw.parameter.ActividadParam;

public class ActividadNew2 implements Operation {

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
        ActividadDao oActividadDao = new ActividadDao(oContexto.getEnumTipoConexion());
        ActividadParam oActividadParam = new ActividadParam(request);
        oActividadBean = oActividadParam.loadId(oActividadBean);

        //Validacion
        if (tipoUsuario.equals(net.daw.helper.Enum.TipoUsuario.Profesor)) {

            try {
                oActividadBean = oActividadParam.load(oActividadBean);
            } catch (NumberFormatException e) {
                return "Tipo de dato incorrecto en uno de los campos del formulario";
            }
            try {
                oActividadDao.set(oActividadBean);
            } catch (Exception e) {
                throw new ServletException("ActividadController: New Error: Phase 2: " + e.getMessage());
            }
            return "Se ha añadido la información de la actividad con id=" + Integer.toString(oActividadBean.getId());
        } else {
            //Mostramos el MENSAJE
            oContexto.setVista("jsp/mensaje.jsp");
            return "<span class=\"label label-important\">¡¡¡ No estás autorizado a entrar aquí !!!<span>";

        }
    }
}
