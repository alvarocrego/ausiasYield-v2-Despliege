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
 * @author Jordi Eslava Barrera
 */
public class RequerimientoRemove2 implements Operation{
    
    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/mensaje.jsp"); 
        RequerimientoBean oRequerimientoBean = new RequerimientoBean();   
        RequerimientoParam oRequerimientoParam = new RequerimientoParam(request);
        oRequerimientoBean = oRequerimientoParam.loadId(oRequerimientoBean);
        try {
            RequerimientoDao oRequerimientoDAO = new RequerimientoDao(oContexto.getEnumTipoConexion());
            oRequerimientoDAO.remove(oRequerimientoBean);
        } catch (Exception e) {
            throw new ServletException("RequerimientoController: Remove Error: " + e.getMessage());
        }
        String Mensaje = ("Se ha eliminado la información del requerimiento con id=" + Integer.toString(oRequerimientoBean.getId()));
        return Mensaje;
    }
    
}
