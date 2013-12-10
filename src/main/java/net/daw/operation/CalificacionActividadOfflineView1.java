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
import net.daw.dao.OpcionDao;
import net.daw.dao.ActividadofflineDao;
import net.daw.dao.UsuarioDao;
import net.daw.helper.Contexto;
import net.daw.parameter.CalificacionActividadOfflineParam;

/**
 *
 * @author al037805
 */
public class CalificacionActividadOfflineView1 implements Operation{
    
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
        UsuarioDao oUsuarioDao = new UsuarioDao(oContexto.getEnumTipoConexion());
        ActividadofflineDao oActividadOfflineDao = new ActividadofflineDao(oContexto.getEnumTipoConexion());

        //Validacion
        if (tipoUsuario.equals(net.daw.helper.Enum.TipoUsuario.Profesor)
                || tipoUsuario.equals(net.daw.helper.Enum.TipoUsuario.Alumno)) {
            try {
                oCalificacionActividadOfflineBean = oCalificacionActividadOfflineDao.get(oCalificacionActividadOfflineBean);
                oCalificacionActividadOfflineBean.setUsuario(oUsuarioDao.get(oCalificacionActividadOfflineBean.getUsuario()));
                oCalificacionActividadOfflineBean.setActividad_offline(oActividadOfflineDao.get(oCalificacionActividadOfflineBean.getActividad_offline()));
            } catch (Exception e) {
                throw new ServletException("UsuarioController: View Error: Phase 1: " + e.getMessage());
            }

            return oCalificacionActividadOfflineBean;
        } else {
            //Mostramos el MENSAJE
            oContexto.setVista("jsp/mensaje.jsp");
            return "<span class=\"label label-important\">¡¡¡ No estás autorizado a entrar aquí !!!<span>";
        }
    }
    
}
