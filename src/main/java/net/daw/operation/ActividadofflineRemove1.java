/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.operation;

/**
 *
 * @author Javier Bonet
 */

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.ActividadofflineBean;
import net.daw.helper.Contexto;
import net.daw.parameter.ActividadofflineParam;


public class ActividadofflineRemove1 implements Operation{
    
    
    

     @Override
     public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
         Contexto oContexto = (Contexto) request.getAttribute("contexto");
         oContexto.setVista("jsp/confirmForm.jsp");
         ActividadofflineBean oActividadOfflineBean = new ActividadofflineBean();   
         ActividadofflineParam oActividadOfflineParam = new ActividadofflineParam(request);
         oActividadOfflineBean = oActividadOfflineParam.loadId(oActividadOfflineBean);
         return "Borrar la actividad offline " + oActividadOfflineBean.getId();
 
     }
    
    
    
    
    
    
    
    
}
