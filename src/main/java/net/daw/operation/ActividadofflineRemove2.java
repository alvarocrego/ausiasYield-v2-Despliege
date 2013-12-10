/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.operation;

/**
 *
 * @author Javier Bonet
 */

import javax.servlet.ServletException;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 
 import net.daw.bean.ActividadofflineBean;
 import net.daw.dao.ActividadofflineDao;
 import net.daw.helper.Contexto;
 import net.daw.parameter.ActividadofflineParam;





public class ActividadofflineRemove2 implements Operation{
    
    
     @Override
     public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
         Contexto oContexto = (Contexto) request.getAttribute("contexto");
         oContexto.setVista("jsp/mensaje.jsp");   
         ActividadofflineBean oActividadOfflineBean = new ActividadofflineBean(); 
         ActividadofflineParam oActividadOfflineParam = new ActividadofflineParam(request);
         oActividadOfflineBean = oActividadOfflineParam.loadId(oActividadOfflineBean);
         try {
             ActividadofflineDao oActividadOfflineDao = new ActividadofflineDao(oContexto.getEnumTipoConexion());
             oActividadOfflineDao.remove(oActividadOfflineBean);
         } catch (Exception e) {
             throw new ServletException("ActividadOfflineController: Remove Error: " + e.getMessage());
         }
         String Mensaje = ("Se ha eliminado la información de la actividad offline con id=" + Integer.toString(oActividadOfflineBean.getId()));
         return Mensaje;
     }
    
    
    
    
    
    
    
    
}
