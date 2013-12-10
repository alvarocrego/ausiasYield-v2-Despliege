/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.operation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.CalificacionActividadOfflineBean;
import net.daw.bean.UsuarioBean;
import net.daw.dao.ActividadofflineDao;
import net.daw.dao.UsuarioDao;
import net.daw.helper.Contexto;
import net.daw.parameter.CalificacionActividadOfflineParam;

/**
 *
 * @author Crash
 */
public class CalificacionActividadOfflineNew1 implements Operation{
    
    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");

        //Parte para saber el tipo de usuario
        UsuarioBean oUsuarioBean;
        oUsuarioBean = (UsuarioBean) request.getSession().getAttribute("usuarioBean");
        java.lang.Enum tipoUsuario = oUsuarioBean.getTipoUsuario();
        //

        CalificacionActividadOfflineParam oCalificacionActividadOfflineParam = new CalificacionActividadOfflineParam(request);
        CalificacionActividadOfflineBean oCalificacionActividadOfflineBean = new CalificacionActividadOfflineBean();
        UsuarioDao oUsuarioDao = new UsuarioDao(oContexto.getEnumTipoConexion());
        ActividadofflineDao oActividadofflineDao = new ActividadofflineDao(oContexto.getEnumTipoConexion());
        //Validacion
        if (tipoUsuario.equals(net.daw.helper.Enum.TipoUsuario.Profesor)) {

            try {
                oCalificacionActividadOfflineBean = oCalificacionActividadOfflineParam.load(oCalificacionActividadOfflineBean);
                oCalificacionActividadOfflineBean.setUsuario(oUsuarioDao.get(oCalificacionActividadOfflineBean.getUsuario()));
                oCalificacionActividadOfflineBean = oCalificacionActividadOfflineParam.load(oCalificacionActividadOfflineBean);
                oCalificacionActividadOfflineBean.setActividad_offline(oActividadofflineDao.get(oCalificacionActividadOfflineBean.getActividad_offline()));
                //oCalificacionActividadOfflineBean = oCalificacionActividadOfflineParam.load(oCalificacionActividadOfflineBean);
                
            } catch (NumberFormatException e) {
                oContexto.setVista("jsp/mensaje.jsp");
                return "Tipo de dato incorrecto en uno de los campos del formulario";
            }
            oContexto.setVista("jsp/calificacion_actividad_offline/form.jsp");
            return oCalificacionActividadOfflineBean;
        } else {
            //Mostramos el MENSAJE
            oContexto.setVista("jsp/mensaje.jsp");
            return "<span class=\"label label-important\">¡¡¡ No estás autorizado a entrar aquí !!!<span>";

        }

    }
    
}
