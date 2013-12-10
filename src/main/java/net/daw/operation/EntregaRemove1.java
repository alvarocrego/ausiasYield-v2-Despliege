/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.operation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.EntregaBean;
import net.daw.helper.Contexto;
import net.daw.parameter.EntregaParam;

/**
 *
 * @author al037431
 */
public class EntregaRemove1 implements Operation{
     @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/confirmForm.jsp");
        EntregaBean oEntregaBean = new EntregaBean();   
        EntregaParam oEntregaParam = new EntregaParam(request);
        oEntregaBean = oEntregaParam.loadId(oEntregaBean);
        return "Borrar el Entrega " + oEntregaBean.getId();
    }
}
