/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.operation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.BacklogBean;
import net.daw.helper.Contexto;
import net.daw.parameter.BacklogParam;

/**
 *
 * @author Edu Membrillas
 */
public class BacklogRemove1 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/confirmForm.jsp");
        BacklogBean oBacklogBean = new BacklogBean();
        BacklogParam oBacklogParam = new BacklogParam(request);
        oBacklogBean = oBacklogParam.loadId(oBacklogBean);
        return "borrar el Backlog " + oBacklogBean.getId();

    }
}
