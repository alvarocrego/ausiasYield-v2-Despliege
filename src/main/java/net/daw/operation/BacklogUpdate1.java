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
public class BacklogUpdate1 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/backlog/form.jsp");
        BacklogBean oBacklogBean;
        BacklogDao oBacklogDao;
        oBacklogBean = new BacklogBean();
        BacklogParam oBacklog = new BacklogParam(request);
        oBacklogBean = oBacklog.loadId(oBacklogBean);
        oBacklogDao = new BacklogDao(oContexto.getEnumTipoConexion());
        try {
            oBacklogBean = oBacklogDao.get(oBacklogBean);
        } catch (Exception e) {
            throw new ServletException("BacklogController: Update Error: Phase 1: " + e.getMessage());
        }
        try {
            oBacklogBean = oBacklog.load(oBacklogBean);
        } catch (NumberFormatException e) {
            oContexto.setVista("jsp/mensaje.jsp");
            return "Tipo de dato incorrecto en uno de los campos del formulario";
        }
        return oBacklogBean;
    }
}
