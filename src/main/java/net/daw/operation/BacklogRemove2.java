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

public class BacklogRemove2 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/mensaje.jsp");
        BacklogBean oBacklogBean = new BacklogBean();
        BacklogParam oBacklogParam = new BacklogParam(request);
        oBacklogBean = oBacklogParam.loadId(oBacklogBean);
        try {
            BacklogDao oBacklogDao = new BacklogDao(oContexto.getEnumTipoConexion());
            oBacklogDao.remove(oBacklogBean);
        } catch (Exception e) {
            throw new ServletException("EntradaController: Remove Error: " + e.getMessage());
        }
        String strMensaje = "Se ha eliminado la información de la entrada con id=" + Integer.toString(oBacklogBean.getId()) + "<br />";
        strMensaje += "<a href=\"Controller?class=backlog&method=list\">Ir al listado de entrada</a><br />";
        String Mensaje = strMensaje;
        return Mensaje;
    }

}
