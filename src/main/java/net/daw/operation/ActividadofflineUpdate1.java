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


public class ActividadofflineUpdate1 implements Operation{
    
    @Override
     public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
         Contexto oContexto = (Contexto) request.getAttribute("contexto");
         oContexto.setVista("jsp/actividadOffline/form.jsp");
         ActividadofflineBean oActividadOfflineBean;
         ActividadofflineDao oActividadOfflineDao;
         oActividadOfflineBean = new ActividadofflineBean();
         ActividadofflineParam oActividadOfflineParam = new ActividadofflineParam(request);
         oActividadOfflineBean = oActividadOfflineParam.loadId(oActividadOfflineBean);
         oActividadOfflineDao = new ActividadofflineDao(oContexto.getEnumTipoConexion());
         try {
             oActividadOfflineBean = oActividadOfflineDao.get(oActividadOfflineBean);
         } catch (Exception e) {
             throw new ServletException("ActividadOfflineController: Update Error: Phase 1: " + e.getMessage());
         }
         try {
             oActividadOfflineBean = oActividadOfflineParam.load(oActividadOfflineBean);
         } catch (NumberFormatException e) {
             oContexto.setVista("jsp/mensaje.jsp");
             return "Tipo de dato incorrecto en uno de los campos del formulario";
         }
         return oActividadOfflineBean;
     }
    
    
    
    
    
    
    
    
}
