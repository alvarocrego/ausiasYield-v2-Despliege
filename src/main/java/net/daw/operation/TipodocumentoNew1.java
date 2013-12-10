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
 * @author Jacobo Segovia
 */
public class TipodocumentoNew1 implements Operation {
    
      @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        EstadoParam oEstadoParam = new EstadoParam(request);
        EstadoBean oEstadoBean = new EstadoBean();       
        try {
            oEstadoBean = oEstadoParam.load(oEstadoBean);
        } catch (NumberFormatException e) {
            oContexto.setVista("jsp/mensaje.jsp");
            return "Tipo de dato incorrecto en uno de los campos del formulario";
        }
        oContexto.setVista("jsp/tipodocumento/form.jsp");
        return oEstadoBean;
    }
    
}
