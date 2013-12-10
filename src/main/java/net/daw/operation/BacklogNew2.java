/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.operation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.BacklogBean;
import net.daw.dao.BacklogDao;
import net.daw.helper.Contexto;
import net.daw.parameter.BacklogParam;

/**
 *
 * @author Edu Membrillas
 */
public class BacklogNew2 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        switch (oContexto.getSearchingFor()) {
            case "usuario": {
                oContexto.setVista("jsp/usuario/list.jsp");
                oContexto.setClase("usuario");
                oContexto.setMetodo("list");
                oContexto.setFase("1");
                oContexto.setSearchingFor("usuario");
                oContexto.setClaseRetorno("backlog");
                oContexto.setMetodoRetorno("new");
                oContexto.setFaseRetorno("1");
                oContexto.removeParam("id_usuario");
                UsuarioList1 oOperacion = new UsuarioList1();
                return oOperacion.execute(request, response);
            }
            case "requerimiento": {
                oContexto.setVista("jsp/requerimiento/list.jsp");
                oContexto.setClase("requerimiento");
                oContexto.setMetodo("list");
                oContexto.setFase("1");
                oContexto.setSearchingFor("requerimiento");
                oContexto.setClaseRetorno("backlog");
                oContexto.setMetodoRetorno("new");
                oContexto.setFaseRetorno("1");
                oContexto.removeParam("id_requerimiento");
                RequerimientoList1 oOperacion = new RequerimientoList1();
                return oOperacion.execute(request, response);
            }
            default:
                oContexto.setVista("jsp/mensaje.jsp");
                BacklogBean oBacklogBean = new BacklogBean();
                BacklogDao oBacklogDao = new BacklogDao(oContexto.getEnumTipoConexion());
                BacklogParam oBacklogParam = new BacklogParam(request);
                oBacklogBean = oBacklogParam.loadId(oBacklogBean);
                try {
                    oBacklogBean = oBacklogParam.load(oBacklogBean);
                } catch (NumberFormatException e) {
                    return "Tipo de dato incorrecto en uno de los campos del formulario";
                }
                try {
                    oBacklogDao.set(oBacklogBean);
                } catch (Exception e) {
                    throw new ServletException("BacklogController: Update Error: Phase 2: " + e.getMessage());
                }
                String strMensaje = "Se ha añadido la información de backlog con id=" + Integer.toString(oBacklogBean.getId()) + "<br />";
                strMensaje += "<a href=\"Controller?class=backlog&method=list&filter=id_usuario&filteroperator=equals&filtervalue=" + oBacklogBean.getUsuario().getId() + "\">Ver Backlogs de este Usuario</a><br />";
                strMensaje += "<a href=\"Controller?class=backlog&method=list&filter=id_requerimiento&filteroperator=equals&filtervalue=" + oBacklogBean.getRequerimiento().getId() + "\">Ver Backlogs de este Requerimiento</a><br />";
                strMensaje += "<a href=\"Controller?class=backlog&method=view&id=" + oBacklogBean.getId() + "\">Ver Backlog creado en el formulario</a><br />";
                return strMensaje;
        }
    }
}
