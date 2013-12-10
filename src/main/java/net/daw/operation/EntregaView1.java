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
public class EntregaView1 implements Operation{
    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/entrega/form.jsp");        
        EntregaBean oEntregaBean;
        EntregaDao oEntregaDao;
        oEntregaBean = new EntregaBean();
        EntregaParam oEntregaParam = new EntregaParam(request);
        oEntregaBean = oEntregaParam.loadId(oEntregaBean);
        oEntregaDao = new EntregaDao(oContexto.getEnumTipoConexion());
        try {
            oEntregaBean = oEntregaDao.get(oEntregaBean);
        } catch (Exception e) {
            throw new ServletException("EntregaController: View Error: Phase 1: " + e.getMessage());
        }
        oEntregaBean = oEntregaParam.load(oEntregaBean);
        return oEntregaBean;
    }
}
