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
public class CalificacionActividadOfflineRemove2 implements Operation{
    
     @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");

        //Parte para saber el tipo de usuario
        UsuarioBean oUsuarioBean;
        oUsuarioBean = (UsuarioBean) request.getSession().getAttribute("usuarioBean");
        java.lang.Enum tipoUsuario = oUsuarioBean.getTipoUsuario();
        //

        oContexto.setVista("jsp/mensaje.jsp");
        CalificacionActividadOfflineBean oCalificacionActividadOfflineBean = new CalificacionActividadOfflineBean();
        CalificacionActividadOfflineParam oCalificacionActividadOfflineParam = new CalificacionActividadOfflineParam(request);
        oCalificacionActividadOfflineBean = oCalificacionActividadOfflineParam.loadId(oCalificacionActividadOfflineBean);

        //Validacion
        if (tipoUsuario.equals(net.daw.helper.Enum.TipoUsuario.Profesor)) {

            try {
                CalificacionActividadOfflineDao oCalificacionActividadOfflineDAO = new CalificacionActividadOfflineDao(oContexto.getEnumTipoConexion());
                oCalificacionActividadOfflineDAO.remove(oCalificacionActividadOfflineBean);
            } catch (Exception e) {
                throw new ServletException("CalificacionActividadOfflineController: Remove Error: " + e.getMessage());
            }
            String strMensaje = "Se ha eliminado la información de la calificacion con id=" + Integer.toString(oCalificacionActividadOfflineBean.getId()) + "<br />";
            strMensaje += "<a href=\"Controller?class=CalificacionActividadOffline&method=list\">Ir al listado de calificaciones</a><br />";
            String Mensaje = strMensaje;
            return Mensaje;
        } else {
            //Mostramos el MENSAJE
            oContexto.setVista("jsp/mensaje.jsp");
            return "<span class=\"label label-important\">¡¡¡ No estás autorizado a entrar aquí !!!<span>";

        }
    }
    
}
