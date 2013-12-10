/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.operation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.EstadoBean;
import net.daw.helper.Contexto;
import net.daw.parameter.EstadoParam;

/**
 *
 * @author Diana Ortega
 */
public class EstadoRemove1 implements Operation{
    
     @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/confirmForm.jsp");
        EstadoBean oEstadoBean = new EstadoBean();   
        EstadoParam oEstadoParam = new EstadoParam(request);
        oEstadoBean = oEstadoParam.loadId(oEstadoBean);
        return "Borrar el Estado " + oEstadoBean.getId();
    }
    
}
