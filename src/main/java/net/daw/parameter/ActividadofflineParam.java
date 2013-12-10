/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.parameter;

/**
 *
 * @author mati
 */

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import net.daw.bean.ActividadofflineBean;

public class ActividadofflineParam {
    
      private HttpServletRequest request;
 
     public ActividadofflineParam(HttpServletRequest request) throws Exception {
         this.request = request;
     }
 
     public ActividadofflineBean loadId(ActividadofflineBean oActividadOffline) throws NumberFormatException {
         try {
             if (request.getParameter("id") != null) {
                oActividadOffline.setId(Integer.parseInt(request.getParameter("id")));
             } else {
                 oActividadOffline.setId(0);
             }
         } catch (NumberFormatException e) {
             throw new NumberFormatException("Controller: Error: Load: Formato de datos en parámetros incorrecto " + e.getMessage());
         }
         return oActividadOffline;
     }
 
     public ActividadofflineBean load(ActividadofflineBean oActividadOffline) throws NumberFormatException, ParseException {
         try {
                         
              if ((request.getParameter("enunciado") != null)) {
                 oActividadOffline.setEnunciado(request.getParameter("enunciado"));
             }
             
              if ((request.getParameter("fecha") != null)) {
                   oActividadOffline.setFecha(new SimpleDateFormat("dd-MM-yyyy").parse(request.getParameter("fecha")));
                 //oActividadOffline.setFecha(Date.valueOf(request.getParameter("fecha")));
             }
              
              
              if ((request.getParameter("calificacion") != null)) {
                 oActividadOffline.setCalificacion(Double.parseDouble(request.getParameter("calificacion")));
             } 
              
               if ((request.getParameter("evaluacion") != null)) {
                 oActividadOffline.setEvaluacion(Integer.parseInt(request.getParameter("evaluacion")));
             }
               
                if ((request.getParameter("activo") != null)) {
                 oActividadOffline.setActivo(Integer.parseInt(request.getParameter("activo")));
             }
             
         } catch (NumberFormatException e) {
             throw new NumberFormatException("Controller: Error: Load: Formato de datos en parámetros incorrecto " + e.getMessage());
         }
         return oActividadOffline;
     }
    
    
 
    
}
