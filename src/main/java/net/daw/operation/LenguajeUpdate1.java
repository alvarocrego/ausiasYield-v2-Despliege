/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.daw.operation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.LenguajeBean;
import net.daw.dao.LenguajeDao;
import net.daw.helper.Contexto;
import net.daw.parameter.LenguajeParam;

/**
 *
 * @author al037294
 */
public class LenguajeUpdate1 implements Operation{
    
    /**
     *
     * @author Alvaro Crego
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/lenguaje/form.jsp");
        LenguajeBean oLenguajeBean;
        LenguajeDao oLenguajeDao;
        oLenguajeBean = new LenguajeBean();
        LenguajeParam oLenguajeParam = new LenguajeParam(request);
        oLenguajeBean = oLenguajeParam.loadId(oLenguajeBean);
        oLenguajeDao = new LenguajeDao(oContexto.getEnumTipoConexion());
        try {
            oLenguajeBean = oLenguajeDao.get(oLenguajeBean);
        } catch (Exception e) {
            throw new ServletException("lenguajeController: Update Error: Phase 1: " + e.getMessage());
        }
        oLenguajeBean = oLenguajeParam.load(oLenguajeBean);
        return oLenguajeBean;
    }

}
