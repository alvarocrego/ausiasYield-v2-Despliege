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



public class ActividadofflineView1 implements Operation{
    
     @Override
     public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
         Contexto oContexto = (Contexto) request.getAttribute("contexto");
         oContexto.setVista("jsp/actividadOffline/form.jsp");        
         ActividadofflineBean oActividadofflineBean;
         ActividadofflineDao oActividadofflineDao;
         oActividadofflineBean = new ActividadofflineBean();
         ActividadofflineParam oActividadofflineParam = new ActividadofflineParam(request);
         oActividadofflineBean = oActividadofflineParam.loadId(oActividadofflineBean);
         oActividadofflineDao = new ActividadofflineDao(oContexto.getEnumTipoConexion());
         try {
             oActividadofflineBean = oActividadofflineDao.get(oActividadofflineBean);
         } catch (Exception e) {
             throw new ServletException("ActividadofflineController: View Error: Phase 1: " + e.getMessage());
         }
         oActividadofflineBean = oActividadofflineParam.load(oActividadofflineBean);
         return oActividadofflineBean;
     }
    

    
}
