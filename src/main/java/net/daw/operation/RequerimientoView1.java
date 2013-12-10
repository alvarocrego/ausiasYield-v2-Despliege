/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.operation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.RequerimientoBean;
import net.daw.dao.RequerimientoDao;
import net.daw.helper.Contexto;
import net.daw.parameter.RequerimientoParam;

/**
 *
 * @author al037342
 */
public class RequerimientoView1 implements Operation {
    
    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/requerimiento/form.jsp");        
        RequerimientoBean oRequerimientoBean;
        RequerimientoDao oRequerimientoDao;
        oRequerimientoBean = new RequerimientoBean();
        RequerimientoParam oRequerimientoParam = new RequerimientoParam(request);
        oRequerimientoBean = oRequerimientoParam.loadId(oRequerimientoBean);
        oRequerimientoDao = new RequerimientoDao(oContexto.getEnumTipoConexion());
        try {
            oRequerimientoBean = oRequerimientoDao.get(oRequerimientoBean);
        } catch (Exception e) {
            throw new ServletException("RequerimientoController: View Error: Phase 1: " + e.getMessage());
        }
        oRequerimientoBean = oRequerimientoParam.load(oRequerimientoBean);
        return oRequerimientoBean;
    }
}
