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
import net.daw.dao.UsuarioDao;
import net.daw.helper.Contexto;
import net.daw.parameter.BacklogParam;

/**
 *
 * @author Edu Membrillas
 */
public class BacklogView1 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/backlog/form.jsp");
        BacklogBean oBacklogBean;
        BacklogDao oBacklogDao;
        oBacklogBean = new BacklogBean();
        BacklogParam oBacklogParam = new BacklogParam(request);
        oBacklogBean = oBacklogParam.loadId(oBacklogBean);
        oBacklogDao = new BacklogDao(oContexto.getEnumTipoConexion());
        UsuarioDao oUsuarioDao = new UsuarioDao(oContexto.getEnumTipoConexion());
        try {
            oBacklogBean = oBacklogDao.get(oBacklogBean);
            oBacklogBean.setUsuario(oUsuarioDao.get(oBacklogBean.getUsuario()));
        } catch (Exception e) {
            throw new ServletException("EntradaController: View Error: Phase 1: " + e.getMessage());
        }
        
        return oBacklogBean;
    }
}
