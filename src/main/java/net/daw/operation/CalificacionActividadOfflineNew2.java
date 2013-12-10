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
public class CalificacionActividadOfflineNew2 implements Operation{
    
    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");

        //Parte para saber el tipo de usuario
        UsuarioBean oUsuarioBean;
        oUsuarioBean = (UsuarioBean) request.getSession().getAttribute("usuarioBean");
        java.lang.Enum tipoUsuario = oUsuarioBean.getTipoUsuario();
        //

        if (tipoUsuario.equals(net.daw.helper.Enum.TipoUsuario.Profesor)) {
            switch (oContexto.getSearchingFor()) {
                case "usuario": {
                    oContexto.setVista("jsp/usuario/list.jsp");
                    oContexto.setClase("usuario");
                    oContexto.setMetodo("list");
                    oContexto.setFase("1");
                    oContexto.setSearchingFor("usuario");
                    oContexto.setClaseRetorno("CalificacionActividadOffline");
                    oContexto.setMetodoRetorno("new");
                    oContexto.setFaseRetorno("1");
                    oContexto.removeParam("id_usuario");
                    UsuarioList1 oOperacion = new UsuarioList1();
                    return oOperacion.execute(request, response);
                }
                case "actividadoffline": {
                    oContexto.setVista("jsp/actividadOffline/list.jsp");
                    oContexto.setClase("actividadoffline");
                    oContexto.setMetodo("list");
                    oContexto.setFase("1");
                    oContexto.setSearchingFor("actividadoffline");
                    oContexto.setClaseRetorno("CalificacionActividadOffline");
                    oContexto.setMetodoRetorno("update");
                    oContexto.setFaseRetorno("1");
                    oContexto.removeParam("id_actividadoffline");
                    ActividadofflineList1 oOperacion = new ActividadofflineList1();
                    return oOperacion.execute(request, response);
                }
                default:
                    oContexto.setVista("jsp/mensaje.jsp");
                    CalificacionActividadOfflineBean oCalificacionActividadOfflineBean = new CalificacionActividadOfflineBean();
                    CalificacionActividadOfflineDao oCalificacionActividadOfflineDao = new CalificacionActividadOfflineDao(oContexto.getEnumTipoConexion());
                    CalificacionActividadOfflineParam oCalificacionActividadOfflineParam = new CalificacionActividadOfflineParam(request);
                    oCalificacionActividadOfflineBean = oCalificacionActividadOfflineParam.loadId(oCalificacionActividadOfflineBean);
                    try {
                        oCalificacionActividadOfflineBean = oCalificacionActividadOfflineParam.load(oCalificacionActividadOfflineBean);
                    } catch (NumberFormatException e) {
                        return "Tipo de dato incorrecto en uno de los campos del formulario";
                    }
                    try {
                        oCalificacionActividadOfflineDao.set(oCalificacionActividadOfflineBean);
                    } catch (Exception e) {
                        throw new ServletException("CalificacionActividadOfflineController: Update Error: Phase 2: " + e.getMessage());
                    }
                    String strMensaje = "Se ha añadido la información de la Calificacion Actividad Offline con id=" + Integer.toString(oCalificacionActividadOfflineBean.getId()) + "<br />";
                    strMensaje += "<a href=\"Controller?class=CalificacionActividadOffline&method=list&filter=id_actividadoffline&filteroperator=equals&filtervalue=" + oCalificacionActividadOfflineBean.getActividad_offline().getId() + "\">Ver la calificacion de esta actividad offline</a><br />";
                    strMensaje += "<a href=\"Controller?class=CalificacionActividadOffline&method=view&id=" + oCalificacionActividadOfflineBean.getId() + "\">Ver la calificacion creada</a><br />";
                    return strMensaje;

            }
        } else {
            //Mostramos el MENSAJE
            oContexto.setVista("jsp/mensaje.jsp");
            return "<span class=\"label label-important\">¡¡¡ No estás autorizado a entrar aquí !!!<span>";

        }

    }
    
}
