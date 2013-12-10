/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.operation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.EntregaBean;
import net.daw.dao.EntregaDao;
import net.daw.helper.Contexto;
import net.daw.parameter.EntregaParam;

/**
 *
 * @author al037431
 */
public class EntregaRemove2 implements Operation{
    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/mensaje.jsp");   
        EntregaBean oEntregaBean = new EntregaBean(); 
        EntregaParam oEntregaParam = new EntregaParam(request);
        oEntregaBean = oEntregaParam.loadId(oEntregaBean);
        try {
            EntregaDao oEntregaDao = new EntregaDao(oContexto.getEnumTipoConexion());
            oEntregaDao.remove(oEntregaBean);
        } catch (Exception e) {
            throw new ServletException("EntregaController: Remove Error: " + e.getMessage());
        }
        String Mensaje = ("Se ha eliminado la información del repositorio con id=" + Integer.toString(oEntregaBean.getId()));
        return Mensaje;
    }
    
}
