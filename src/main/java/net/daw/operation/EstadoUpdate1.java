/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.daw.operation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.EstadoBean;
import net.daw.dao.EstadoDao;
import net.daw.helper.Contexto;
import net.daw.parameter.EstadoParam;

/**
 *
 * @author Diana
 */
public class EstadoUpdate1 implements Operation{
    
        /**
     *
     * @author Diana Ortega
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/estado/form.jsp");
        EstadoBean oEstadoBean;
        EstadoDao oEstadoDao;
        oEstadoBean = new EstadoBean();
        EstadoParam oClienteParam = new EstadoParam(request);
        oEstadoBean = oClienteParam.loadId(oEstadoBean);
        oEstadoDao = new EstadoDao(oContexto.getEnumTipoConexion());
        try {
            oEstadoBean = oEstadoDao.get(oEstadoBean);
        } catch (Exception e) {
            throw new ServletException("ClienteController: Update Error: Phase 1: " + e.getMessage());
        }
        oEstadoBean = oClienteParam.load(oEstadoBean);
        return oEstadoBean;
    }
    
}
