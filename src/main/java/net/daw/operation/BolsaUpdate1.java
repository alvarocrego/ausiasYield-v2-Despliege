/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.operation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.BolsaBean;
import net.daw.dao.BolsaDao;
import net.daw.helper.Contexto;
import net.daw.parameter.BolsaParam;

/**
 *
 * @author al037877
 */
public class BolsaUpdate1 implements Operation{
      
    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/bolsa/form.jsp");
        BolsaBean oBolsaBean;
        BolsaDao oBolsaDao;
        oBolsaBean = new BolsaBean();
        BolsaParam oBolsaParam = new BolsaParam(request);
        oBolsaBean = oBolsaParam.loadId(oBolsaBean);
        oBolsaDao = new BolsaDao(oContexto.getEnumTipoConexion());
        try {
            oBolsaBean = oBolsaDao.get(oBolsaBean);
        } catch (Exception e) {
            throw new ServletException("bolsaController: Update Error: Phase 1: " + e.getMessage());
        }
        oBolsaBean = oBolsaParam.load(oBolsaBean);
        return oBolsaBean;
    }
}
