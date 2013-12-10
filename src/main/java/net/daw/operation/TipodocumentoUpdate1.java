/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.daw.operation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.TipodocumentoBean;
import net.daw.dao.TipodocumentoDao;
import net.daw.helper.Contexto;
import net.daw.parameter.TipodocumentoParam;

/**
 *
 * @author Jacobo Segovia
 */
public class TipodocumentoUpdate1 implements Operation{
    
        /**
     *
     * @author Jacobo Segovia
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/tipodocumento/form.jsp");
        TipodocumentoBean oTipodocumentoBean;
        TipodocumentoDao oTipodocumentoDao;
        oTipodocumentoBean = new TipodocumentoBean();
        TipodocumentoParam oClienteParam = new TipodocumentoParam(request);
        oTipodocumentoBean = oClienteParam.loadId(oTipodocumentoBean);
        oTipodocumentoDao = new TipodocumentoDao(oContexto.getEnumTipoConexion());
        try {
            oTipodocumentoBean = oTipodocumentoDao.get(oTipodocumentoBean);
        } catch (Exception e) {
            throw new ServletException("TipodocumentoController: Update Error: Phase 1: " + e.getMessage());
        }
        oTipodocumentoBean = oClienteParam.load(oTipodocumentoBean);
        return oTipodocumentoBean;
    }
    
}
