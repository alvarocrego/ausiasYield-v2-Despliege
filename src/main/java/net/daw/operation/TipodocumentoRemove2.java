/*
 * To change this template, choose Tools | Templates
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
public class TipodocumentoRemove2 implements Operation {
    
     @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/mensaje.jsp");   
        TipodocumentoBean oTipodocumentoBean = new TipodocumentoBean(); 
        TipodocumentoParam oTipodocumentoParam = new TipodocumentoParam(request);
        oTipodocumentoBean = oTipodocumentoParam.loadId(oTipodocumentoBean);
        try {
            TipodocumentoDao oTipodocumentoDao = new TipodocumentoDao(oContexto.getEnumTipoConexion());
            oTipodocumentoDao.remove(oTipodocumentoBean);
        } catch (Exception e) {
            throw new ServletException("TipodocumentoController: Remove Error: " + e.getMessage());
        }
        String Mensaje = ("Se ha eliminado la información del tipodocumento con id=" + Integer.toString(oTipodocumentoBean.getId()));
        return Mensaje;
    }
    
}
