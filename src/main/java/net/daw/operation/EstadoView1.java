/*
 * To change this template, choose Tools | Templates
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
 * @author Diana Ortega
 */
public class EstadoView1 implements Operation {
    
    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/estado/form.jsp");        
        EstadoBean oEstadoBean;
        EstadoDao oEstadoDao;
        oEstadoBean = new EstadoBean();
        EstadoParam oEstadoParam = new EstadoParam(request);
        oEstadoBean = oEstadoParam.loadId(oEstadoBean);
        oEstadoDao = new EstadoDao(oContexto.getEnumTipoConexion());
        try {
            oEstadoBean = oEstadoDao.get(oEstadoBean);
        } catch (Exception e) {
            throw new ServletException("EstadoController: View Error: Phase 1: " + e.getMessage());
        }
        oEstadoBean = oEstadoParam.load(oEstadoBean);
        return oEstadoBean;
    }
}
