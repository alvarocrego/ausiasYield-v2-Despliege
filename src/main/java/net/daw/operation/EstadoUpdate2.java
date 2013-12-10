/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.operation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.EstadoBean;
import net.daw.dao.EstadoDao;
import net.daw.helper.Contexto;
import net.daw.parameter.EstadoParam;

/**
 *
 * @author Diana Ortega
 */
public class EstadoUpdate2 implements Operation {
    
     @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/mensaje.jsp");   
        EstadoBean oEstadoBean = new EstadoBean();
        EstadoDao oEstadoDao = new EstadoDao(oContexto.getEnumTipoConexion());
        EstadoParam oEstadoParam = new EstadoParam(request);
        oEstadoBean = oEstadoParam.loadId(oEstadoBean);
        try {
            oEstadoBean = oEstadoParam.load(oEstadoBean);
        } catch (NumberFormatException e) {
            return "Tipo de dato incorrecto en uno de los campos del formulario";
        }         
        try {
            oEstadoDao.set(oEstadoBean);
        } catch (Exception e) {
            throw new ServletException("RepositorioController: Update Error: Phase 2: " + e.getMessage());
        }
        String strMensaje = "Se ha modificado la información de estado con id=" + Integer.toString(oEstadoBean.getId()) + "<br />";
        strMensaje += "<a href=\"Controller?class=estado&method=view&id=" + oEstadoBean.getId() + "\">Ver estado de la modificación</a><br />";
        return strMensaje;
    }
    
}
