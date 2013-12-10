/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.daw.operation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.LenguajeBean;
import net.daw.helper.Contexto;
import net.daw.parameter.LenguajeParam;

/**
 *
 * @author al037294
 */
public class LenguajeRemove1 implements Operation{
    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/confirmForm.jsp");
        LenguajeBean oLenguajeBean = new LenguajeBean();   
        LenguajeParam oLenguajeParam = new LenguajeParam(request);
        oLenguajeBean = oLenguajeParam.loadId(oLenguajeBean);
        return "Borrar el Lenguaje " + oLenguajeBean.getId();
    }
}
