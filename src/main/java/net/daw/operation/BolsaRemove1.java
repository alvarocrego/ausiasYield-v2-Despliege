/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.daw.operation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.BolsaBean;
import net.daw.helper.Contexto;
import net.daw.parameter.BolsaParam;

/**
 *
 * @author Jacobo Segovia
 */
public class BolsaRemove1 implements Operation{
    
    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/confirmForm.jsp");
        BolsaBean oBolsaBean = new BolsaBean();
        BolsaParam oBolsaParam = new BolsaParam(request);
        oBolsaBean = oBolsaParam.loadId(oBolsaBean);
        return "Borrar la bolsa " + oBolsaBean.getId();

    }
}
