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
public class TipodocumentoUpdate2 implements Operation {
    
     @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/mensaje.jsp");   
        TipodocumentoBean oTipodocumentoBean = new TipodocumentoBean();
        TipodocumentoDao oTipodocumentoDao = new TipodocumentoDao(oContexto.getEnumTipoConexion());
        TipodocumentoParam oTipodocumentoParam = new TipodocumentoParam(request);
        oTipodocumentoBean = oTipodocumentoParam.loadId(oTipodocumentoBean);
        try {
            oTipodocumentoBean = oTipodocumentoParam.load(oTipodocumentoBean);
        } catch (NumberFormatException e) {
            return "Tipo de dato incorrecto en uno de los campos del formulario";
        }         
        try {
            oTipodocumentoDao.set(oTipodocumentoBean);
        } catch (Exception e) {
            throw new ServletException("TipodocumentoController: Update Error: Phase 2: " + e.getMessage());
        }
        String strMensaje = "Se ha modificado la información de tipodocumento con id=" + Integer.toString(oTipodocumentoBean.getId()) + "<br />";
        strMensaje += "<a href=\"Controller?class=tipodocumento&method=view&id=" + oTipodocumentoBean.getId() + "\">Ver tipodocumento de la modificación</a><br />";
        return strMensaje;
    }
    
}
