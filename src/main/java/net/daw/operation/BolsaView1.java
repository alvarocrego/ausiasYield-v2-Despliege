/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.daw.operation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.BolsaBean;
import net.daw.dao.DocumentoDao;
import net.daw.dao.DocumentoDao;
import net.daw.dao.BolsaDao;
import net.daw.helper.Contexto;
import net.daw.parameter.BolsaParam;

/**
 *
 * @author Jacobo Segovia
 */
public class BolsaView1 implements Operation{
    
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
        DocumentoDao oDocumento1Dao = new DocumentoDao(oContexto.getEnumTipoConexion());
        DocumentoDao oDocumento2Dao = new DocumentoDao(oContexto.getEnumTipoConexion());
        try {
            oBolsaBean = oBolsaDao.get(oBolsaBean);
            oBolsaBean.setDocumento1(oDocumento1Dao.get(oBolsaBean.getDocumento1()));
            oBolsaBean.setDocumento2(oDocumento2Dao.get(oBolsaBean.getDocumento2()));
        } catch (Exception e) {
            throw new ServletException("BolsaController: View Error: Phase 1: " + e.getMessage());
        }
        
        return oBolsaBean;
    }
}
