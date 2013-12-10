/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.operation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.CalificacionActividadOfflineBean;
import net.daw.bean.UsuarioBean;
import net.daw.dao.CalificacionActividadOfflineDao;
import net.daw.helper.Contexto;
import net.daw.parameter.CalificacionActividadOfflineParam;

/**
 *
 * @author Crash
 */
public class CalificacionActividadOfflineUpdate1 implements Operation{
    
    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");

        //Parte para saber el tipo de usuario
        UsuarioBean oUsuarioBean;
        oUsuarioBean = (UsuarioBean) request.getSession().getAttribute("usuarioBean");
        java.lang.Enum tipoUsuario = oUsuarioBean.getTipoUsuario();
        //

        oContexto.setVista("jsp/calificacion_actividad_offline/form.jsp");
        CalificacionActividadOfflineBean oCalificacionActividadOfflineBean;
        CalificacionActividadOfflineDao oCalificacionActividadOfflineDao;
        oCalificacionActividadOfflineBean = new CalificacionActividadOfflineBean();
        CalificacionActividadOfflineParam oCalificacionActividadOfflineParam = new CalificacionActividadOfflineParam(request);
        oCalificacionActividadOfflineBean = oCalificacionActividadOfflineParam.loadId(oCalificacionActividadOfflineBean);
        oCalificacionActividadOfflineDao = new CalificacionActividadOfflineDao(oContexto.getEnumTipoConexion());
        if (tipoUsuario.equals(net.daw.helper.Enum.TipoUsuario.Profesor)) {
            try {
                oCalificacionActividadOfflineBean = oCalificacionActividadOfflineDao.get(oCalificacionActividadOfflineBean);
            } catch (Exception e) {
                throw new ServletException("CalificacionActividadOfflineController: Update Error: Phase 1: " + e.getMessage());
            }
            try {
                oCalificacionActividadOfflineBean = oCalificacionActividadOfflineParam.load(oCalificacionActividadOfflineBean);
            } catch (NumberFormatException e) {
                oContexto.setVista("jsp/mensaje.jsp");
                return "Tipo de dato incorrecto en uno de los campos del formulario";
            }
            return oCalificacionActividadOfflineBean;
        } else {
            //Mostramos el MENSAJE
            oContexto.setVista("jsp/mensaje.jsp");
            return "<span class=\"label label-important\">¡¡¡ No estás autorizado a entrar aquí !!!<span>";

        }
    }
    
}
