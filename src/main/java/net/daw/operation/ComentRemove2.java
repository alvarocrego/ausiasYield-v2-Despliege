/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.operation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.ComentBean;
import net.daw.dao.ComentDao;
import net.daw.helper.Contexto;
import net.daw.parameter.ComentParam;

/**
 *
 * @author Jordi Eslava Barrera
 */
public class ComentRemove2 implements Operation{
    
    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/mensaje.jsp"); 
        ComentBean oComentBean = new ComentBean();   
        ComentParam oComentParam = new ComentParam(request);
        oComentBean = oComentParam.loadId(oComentBean);
        try {
            ComentDao oComentDAO = new ComentDao(oContexto.getEnumTipoConexion());
            oComentDAO.remove(oComentBean);
        } catch (Exception e) {
            throw new ServletException("ComentController: Remove Error: " + e.getMessage());
        }
        String Mensaje = ("Se ha eliminado la información del comentario con id=" + Integer.toString(oComentBean.getId()));
        return Mensaje;
    }
    
}
