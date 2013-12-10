/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.operation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.CalificacionActividadOfflineBean;
import net.daw.bean.UsuarioBean;
import net.daw.helper.Contexto;
import net.daw.parameter.CalificacionActividadOfflineParam;

/**
 *
 * @author Crash
 */
public class CalificacionActividadOfflineRemove1 implements Operation{
    
    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");

        //Parte para saber el tipo de usuario
        UsuarioBean oUsuarioBean;
        oUsuarioBean = (UsuarioBean) request.getSession().getAttribute("usuarioBean");
        java.lang.Enum tipoUsuario = oUsuarioBean.getTipoUsuario();
        //

        oContexto.setVista("jsp/confirmForm.jsp");
        CalificacionActividadOfflineBean oCalificacionActividadOfflineBean = new CalificacionActividadOfflineBean();
        CalificacionActividadOfflineParam oCalificacionActividadOfflineParam = new CalificacionActividadOfflineParam(request);
        oCalificacionActividadOfflineBean = oCalificacionActividadOfflineParam.loadId(oCalificacionActividadOfflineBean);

        if (tipoUsuario.equals(net.daw.helper.Enum.TipoUsuario.Profesor)) {
            return "Borrar la calificacion " + oCalificacionActividadOfflineBean.getId();
        } else {
            //Mostramos el MENSAJE
            oContexto.setVista("jsp/mensaje.jsp");
            return "<span class=\"label label-important\">¡¡¡ No estás autorizado a entrar aquí !!!<span>";

        }
    }
    
}
