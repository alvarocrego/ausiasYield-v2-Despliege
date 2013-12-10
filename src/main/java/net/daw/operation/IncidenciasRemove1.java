/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.operation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.IncidenciasBean;
import net.daw.helper.Contexto;
import net.daw.parameter.IncidenciasParam;


/**
 *
 * @author Enrique
 */
public class IncidenciasRemove1 implements Operation{
    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/confirmForm.jsp");
        IncidenciasBean oIncidenciasBean = new IncidenciasBean();   
        IncidenciasParam oIncidenciasParam = new IncidenciasParam(request);
        oIncidenciasBean = oIncidenciasParam.loadId(oIncidenciasBean);
        return "Borrar la Incidencia " + oIncidenciasBean.getId();

    }
}

