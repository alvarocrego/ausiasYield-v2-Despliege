/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.operation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.VotoComentarioBean;
import net.daw.dao.VotoComentarioDao;
import net.daw.helper.Contexto;
import net.daw.parameter.VotoComentarioParam;

/**
 *
 * @author Diana Ortega
 */
public class VotoComentarioRemove2 implements Operation {
    
     @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/mensaje.jsp");   
        VotoComentarioBean oVotoComentarioBean = new VotoComentarioBean(); 
        VotoComentarioParam oVotoComentarioParam = new VotoComentarioParam(request);
        oVotoComentarioBean = oVotoComentarioParam.loadId(oVotoComentarioBean);
        try {
            VotoComentarioDao oVotoComentarioDao = new VotoComentarioDao(oContexto.getEnumTipoConexion());
            oVotoComentarioDao.remove(oVotoComentarioBean);
        } catch (Exception e) {
            throw new ServletException("VotoComentarioController: Remove Error: " + e.getMessage());
        }
        String strMensaje = ("Se ha eliminado la infcompraormación del votoComentario con id=" + Integer.toString(oVotoComentarioBean.getId()));
        strMensaje += "<a href=\"Controller?class=votoComentario&method=list\">Ir al listado de votoComentario</a><br />";
        String Mensaje = strMensaje;
        return Mensaje;
    }
}
