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
public class EstadoRemove2 implements Operation {
    
     @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/mensaje.jsp");   
        EstadoBean oEstadoBean = new EstadoBean(); 
        EstadoParam oEstadoParam = new EstadoParam(request);
        oEstadoBean = oEstadoParam.loadId(oEstadoBean);
        try {
            EstadoDao oEstadoDao = new EstadoDao(oContexto.getEnumTipoConexion());
            oEstadoDao.remove(oEstadoBean);
        } catch (Exception e) {
            throw new ServletException("EstadoController: Remove Error: " + e.getMessage());
        }
        String Mensaje = ("Se ha eliminado la información del estado con id=" + Integer.toString(oEstadoBean.getId()));
        return Mensaje;
    }
    
}
