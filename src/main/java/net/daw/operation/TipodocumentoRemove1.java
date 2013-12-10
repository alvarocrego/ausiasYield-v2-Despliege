/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.operation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.TipodocumentoBean;
import net.daw.helper.Contexto;
import net.daw.parameter.TipodocumentoParam;

/**
 *
 * @author Jacobo Segovia
 */
public class TipodocumentoRemove1 implements Operation{
    
     @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/confirmForm.jsp");
        TipodocumentoBean oTipodocumentoBean = new TipodocumentoBean();   
        TipodocumentoParam oTipodocumentoParam = new TipodocumentoParam(request);
        oTipodocumentoBean = oTipodocumentoParam.loadId(oTipodocumentoBean);
        return "Borrar el Tipodocumento " + oTipodocumentoBean.getId();
    }
    
}
