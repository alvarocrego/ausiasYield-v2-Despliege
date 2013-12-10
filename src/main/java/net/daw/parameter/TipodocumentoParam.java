/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.daw.parameter;

import javax.servlet.http.HttpServletRequest;
import net.daw.bean.TipodocumentoBean;

/**
 *
 * @author Jacobo Segovia
 */
public class TipodocumentoParam {
    
      private HttpServletRequest request;

    public TipodocumentoParam(HttpServletRequest request) throws Exception {
        this.request = request;
    }

    public TipodocumentoBean loadId(TipodocumentoBean oTipodocumentoBean) throws NumberFormatException {
        try {
            if (request.getParameter("id") != null) {
                oTipodocumentoBean.setId(Integer.parseInt(request.getParameter("id")));
            } else {
                oTipodocumentoBean.setId(0);
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Controller: Error: Load: Formato de datos en parámetros incorrecto " + e.getMessage());
        }
        return oTipodocumentoBean;
    }

    public TipodocumentoBean load(TipodocumentoBean oTipodocumentoBean) throws NumberFormatException {
        try {
            if ((request.getParameter("descripcion") != null)) {
                oTipodocumentoBean.setDescripcion(request.getParameter("descripcion"));
            }
            
            if ((request.getParameter("privado") != null)) {
                if(request.getParameter("privado").isEmpty()){
                    oTipodocumentoBean.setPrivado(false);
                }else{
                    oTipodocumentoBean.setPrivado(true);
                }
            }
           
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Controller: Error: Load: Formato de datos en parámetros incorrecto " + e.getMessage());
        }
        return oTipodocumentoBean;
    }

    
}
